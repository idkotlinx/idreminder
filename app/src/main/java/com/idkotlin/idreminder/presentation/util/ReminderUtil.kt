package com.idkotlin.idreminder.presentation.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.idkotlin.idreminder.BaseApp
import com.idkotlin.idreminder.presentation.receiver.AlarmReceiver
import java.util.*

/**
 * Created by kodeartisan on 27/11/17.
 */
object ReminderUtil {

    val TAG = ReminderUtil::class.java.name

    val milMinute = 60000L
    val milHour = 3600000L
    val milDay = 86400000L
    val milWeek = 604800000L
    val milMonth = 2592000000L

    val REMINDERID = "reminder_id"

    public fun setAlarm(context: Context, calendar: Calendar, id: Int) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(ReminderUtil.REMINDERID, id.toString())
        }

        Log.d(TAG, "MINUTE: ${calendar.get(Calendar.MINUTE)}")
        Log.d(TAG, "HOUR: ${calendar.get(Calendar.HOUR_OF_DAY)}")
        Log.d(TAG, "DAY: ${calendar.get(Calendar.DAY_OF_MONTH)}")
        Log.d(TAG, "MONTH: ${calendar.get(Calendar.MONTH)}")

        val pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        BaseApp.appComponent.getAlarmManager().set(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + getDiffTime(calendar),
                pendingIntent)

        // Restart alarm if device is rebooted
        /* val receiver = ComponentName(context, BootReceiver::class.java)
         val pm = context.packageManager
         pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)*/

    }


    public fun setRepeatAlarm(context: Context, calendar: Calendar, id: Int, repeatTime: Long) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.apply { putExtra(ReminderUtil.REMINDERID, id.toString()) }
        val mPendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        Log.d(TAG, "MINUTE: ${calendar.get(Calendar.MINUTE)}")
        Log.d(TAG, "HOUR: ${calendar.get(Calendar.HOUR_OF_DAY)}")
        Log.d(TAG, "DAY: ${calendar.get(Calendar.DAY_OF_MONTH)}")
        Log.d(TAG, "MONTH: ${calendar.get(Calendar.MONTH)}")

        BaseApp.appComponent.getAlarmManager().setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + getDiffTime(calendar),
                repeatTime, mPendingIntent)
    }

    private fun getDiffTime(calendar: Calendar): Long {
        val c = Calendar.getInstance()
        val currentTime = c.timeInMillis

        return calendar.timeInMillis - currentTime
    }


}