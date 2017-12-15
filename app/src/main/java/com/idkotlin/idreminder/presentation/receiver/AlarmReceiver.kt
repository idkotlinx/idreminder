package com.idkotlin.idreminder.presentation.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.idkotlin.idreminder.presentation.util.ReminderUtil
import java.util.*

/**
 * Created by kodeartisan on 30/11/17.
 */
class AlarmReceiver: BroadcastReceiver() {

    private val TAG = AlarmReceiver::class.java.name

    private lateinit var mPendingIntent: PendingIntent


    lateinit var mAlarmManager: AlarmManager

    override fun onReceive(context: Context?, intent: Intent?) {
        val reminderId = intent?.getStringExtra(ReminderUtil.REMINDERID)

        Log.d(TAG, "reminderID: ${reminderId}")
    }

    public fun setAlarm(context: Context, calendar: Calendar, id: Int) {
        mAlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(ReminderUtil.REMINDERID, id.toString())
        }

        Log.d(TAG, "MINUTE: ${calendar.get(Calendar.MINUTE)}")
        Log.d(TAG, "HOUR: ${calendar.get(Calendar.HOUR_OF_DAY)}")
        Log.d(TAG, "DAY: ${calendar.get(Calendar.DAY_OF_MONTH)}")
        Log.d(TAG, "MONTH: ${calendar.get(Calendar.MONTH)}")

        mPendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        mAlarmManager.set(AlarmManager.ELAPSED_REALTIME,
                System.currentTimeMillis(),
                mPendingIntent)

    }

    public fun setRepeatAlarm(context: Context, calendar: Calendar, id: Int, repeatTime: Long) {


        val intent = Intent(context, AlarmReceiver::class.java)
        intent.apply { putExtra(ReminderUtil.REMINDERID, id.toString()) }
        mPendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT)

    }

    public fun cancelAlarm(context: Context, id: Int) {

    }

    private fun getDiffTime(): Long {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis

        return calendar.timeInMillis - currentTime
    }
}
