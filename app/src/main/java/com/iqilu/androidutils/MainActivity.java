package com.iqilu.androidutils;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.iqilu.runtime_permission_library.AndroidPermissionControlCenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AndroidPermissionControlCenter implements View.OnClickListener {

    private List<String> checkPermissions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_permission).setOnClickListener(this);
    }

    @Override
    protected void onPermissionGranted() {
        //进行获取权限后的操作
        Toast.makeText(this, "已经拥有相关权限，可以直接进行操作", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_permission:
                checkPermissions.add(Manifest.permission.CAMERA);
                checkPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                checkPermissionByForeach(checkPermissions, getResources().getString(R.string.app_name));
                break;
        }
    }
}
