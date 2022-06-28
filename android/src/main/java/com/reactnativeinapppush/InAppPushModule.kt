package com.reactnativeinapppush

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class InAppPushModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {


  override fun getName(): String {
    return "InAppPush"
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun multiply(a: Int, b: Int, promise: Promise) {
    promise.resolve(a * b)
  }
  @RequiresApi(Build.VERSION_CODES.O)
  @ReactMethod
  fun showNotification(appName : String, title : String, subTitle : String){
    val packageName = reactApplicationContext!!.packageName
    val launch = reactApplicationContext.packageManager?.getLaunchIntentForPackage(packageName)
    launch?.putExtra(title, 12345)
    launch?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    val pendingIntent = PendingIntent.getActivity(reactApplicationContext, System.currentTimeMillis().toInt(), launch, 0)

    var channelId = "2505"

    var builder = NotificationCompat.Builder(reactApplicationContext, channelId)
      .setSmallIcon(R.drawable.redbox_top_border_background)
      .setContentTitle(title)
      .setContentText(subTitle)
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    var notificationChannel = NotificationChannel(channelId, appName, NotificationManager.IMPORTANCE_HIGH)

    val notificationManager: NotificationManager =
      reactApplicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(notificationChannel)
    notificationManager.notify(12345, builder.build())
  }


}
