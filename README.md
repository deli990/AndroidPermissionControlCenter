# AndroidPermissionControlCenter

********************************---##  源码是最好的文档 ##---******************************************


项目说明：

本项目核心是，

帮助android开发者解决andorid 6.0运行时动态权限申请的专用库,简单易用，适配国内几乎所有手机。

如果在使用过程中有出现适配问题，请及时提issue问题反馈报告。

使用后如果你感觉本项目可以解决你的问题，请star、fork支持下。

使用后有更好的实现方式，请添加qq2630151368共同探讨。

********************************************分割线 *********************************************************

android手机权限使用指南：

android sdk 版本<23 需要在清单配置文件中添加相关权限即可。 

<uses-permission android:name="android.permission.CAMERA"/>

android sdk 版本>=23 不仅仅需要在清单配置文件中引用相关权限，还需要在运行时动态申请权限，本库可以完美解决这个动态申请权限的问题。

******************************************* 分割线 **********************************************************

gradle项目使用指南：

第一步，在项目的build.gradle中添加远程仓库。

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

第二步，在app的huild.gradle中添加依赖。

dependencies {
	        compile 'com.github.deli990:AndroidPermissionControlCenter:v1.0.0'
	}



********************************---## 源码是最好的文档 ##---******************************************

android权限备忘：

危险权限：

联系人权限组：
group:android.permission-group.CONTACTS
  permission:android.permission.WRITE_CONTACTS
  permission:android.permission.GET_ACCOUNTS
  permission:android.permission.READ_CONTACTS
电话权限组：
group:android.permission-group.PHONE
  permission:android.permission.READ_CALL_LOG
  permission:android.permission.READ_PHONE_STATE
  permission:android.permission.CALL_PHONE
  permission:android.permission.WRITE_CALL_LOG
  permission:android.permission.USE_SIP
  permission:android.permission.PROCESS_OUTGOING_CALLS
  permission:com.android.voicemail.permission.ADD_VOICEMAIL
日历权限组：
group:android.permission-group.CALENDAR
  permission:android.permission.READ_CALENDAR
  permission:android.permission.WRITE_CALENDAR
相机权限组：
group:android.permission-group.CAMERA
  permission:android.permission.CAMERA
传感器权限组：
group:android.permission-group.SENSORS
  permission:android.permission.BODY_SENSORS
定位权限组：
group:android.permission-group.LOCATION
  permission:android.permission.ACCESS_FINE_LOCATION
  permission:android.permission.ACCESS_COARSE_LOCATION
存储权限组：
group:android.permission-group.STORAGE
  permission:android.permission.READ_EXTERNAL_STORAGE
  permission:android.permission.WRITE_EXTERNAL_STORAGE
麦克风权限组：
group:android.permission-group.MICROPHONE
  permission:android.permission.RECORD_AUDIO
短信权限组：
group:android.permission-group.SMS
  permission:android.permission.READ_SMS
  permission:android.permission.RECEIVE_WAP_PUSH
  permission:android.permission.RECEIVE_MMS
  permission:android.permission.RECEIVE_SMS
  permission:android.permission.SEND_SMS
  permission:android.permission.READ_CELL_BROADCASTS

普通权限：
ACCESS_LOCATION_EXTRA_COMMANDS
ACCESS_NETWORK_STATE
ACCESS_NOTIFICATION_POLICY
ACCESS_WIFI_STATE
BLUETOOTH
BLUETOOTH_ADMIN
BROADCAST_STICKY
CHANGE_NETWORK_STATE
CHANGE_WIFI_MULTICAST_STATE
CHANGE_WIFI_STATE
DISABLE_KEYGUARD
EXPAND_STATUS_BAR
GET_PACKAGE_SIZE
INSTALL_SHORTCUT
INTERNET
KILL_BACKGROUND_PROCESSES
MODIFY_AUDIO_SETTINGS
NFC
READ_SYNC_SETTINGS
READ_SYNC_STATS
RECEIVE_BOOT_COMPLETED
REORDER_TASKS
REQUEST_INSTALL_PACKAGES
SET_ALARM
SET_TIME_ZONE
SET_WALLPAPER
SET_WALLPAPER_HINTS
TRANSMIT_IR
UNINSTALL_SHORTCUT
USE_FINGERPRINT
VIBRATE
WAKE_LOCK
WRITE_SYNC_SETTINGS


********************************************* 分割线 ************************************************************