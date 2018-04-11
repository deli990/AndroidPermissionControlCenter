package com.iqilu.runtime_permission_library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class AndroidPermissionControlCenter extends AppCompatActivity {
    private List<String> mCheckPermissions = new ArrayList<>();
    private List<String> mApplyPermissions = new ArrayList<>();
    private List<String> mUserDeniedPermissions = new ArrayList<>();
    private List<String> mUserForbiddenPermissions = new ArrayList<>();

    private String mAppName;
    private boolean isFirst = true;

    public static final int APPLY_PERMISSION_REQUESTCODE_BY_FOREACH = 1001;
    public static final int APPLY_PERMISSION_REQUESTCODE_BY_ITERATOR = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void checkPermissionByForeach(List<String> checkPermissions, String app_name) {
        mCheckPermissions = checkPermissions;
        mAppName = app_name;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkPermissions != null && checkPermissions.size() > 0) {
            //compile sdk>=23在清单配置文件中引用相关权限的基础上，进行动态权限申请
            for (String permission : checkPermissions) {
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, permission)) {
                    mApplyPermissions.add(permission);
                }
            }
            if (mApplyPermissions.size() > 0) {
                ActivityCompat.requestPermissions(this, mApplyPermissions.toArray(new String[0]), APPLY_PERMISSION_REQUESTCODE_BY_FOREACH);
            } else {
                onPermissionGranted();
            }
        } else {
            //只需要在清单配置文件中引用相关权限即可

            onPermissionGranted();
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case APPLY_PERMISSION_REQUESTCODE_BY_FOREACH:
            case APPLY_PERMISSION_REQUESTCODE_BY_ITERATOR:
                //对需要申请的权限进行分析，有哪些用户禁用了申请权限
                if (isFirst) {
                    isFirst = false;
                } else {
                    for (String permission : mCheckPermissions) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            boolean flag = shouldShowRequestPermissionRationale(permission);
                            if (!flag) {
                                mUserForbiddenPermissions.add(permission);
                            }
                        }
                    }
                }
                //对授权结果进行分析，找出没有授权的权限
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            mUserDeniedPermissions.add(mApplyPermissions.get(i));
                        }
                    }
                }
                if (mUserDeniedPermissions.size() > 0 || mUserForbiddenPermissions.size() > 0) {
                    onPermissionGrantedDenied();
                    mUserDeniedPermissions.clear();
                    mUserForbiddenPermissions.clear();
                } else {
                    onPermissionGranted();
                    isFirst = true;
                    mUserDeniedPermissions.clear();
                    mUserForbiddenPermissions.clear();
                }
                isFirst = true;
                break;
        }
    }

    protected abstract void onPermissionGranted();

    private void onPermissionGrantedDenied() {
        showPermissionGrantedDeniedDialog();
    }

    protected void showPermissionGrantedDeniedDialog() {
        //此方法可以自己定制
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.permissions_denied_dialog_title);
        String body = getResources().getString(R.string.permissions_denied_dialog_message_body);
        String messageBody = String.format(body, mAppName, "相机", "存储");
        builder.setMessage(messageBody);
        builder.setPositiveButton(R.string.permissions_denied_dialog_positive_btn_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoDefaultSettings();
                dialog.dismiss();

            }
        });
        builder.setNegativeButton(R.string.permissions_denied_dialog_negative_btn_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /*protected void gotoManageApplications() {
    //进入手机的应用管理界面，需要找到应用才可以授权
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.setClassName("com.android.settings", "com.android.settings.ManageApplications");
        startActivity(intent);
    }*/

    protected void gotoDefaultSettings() {
        //直接进入权限设置界面
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

}
