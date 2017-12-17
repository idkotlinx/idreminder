package com.idkotlin.idreminder.presentation.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.idkotlin.idreminder.BaseApp
import com.idkotlin.idreminder.R
import com.idkotlin.idreminder.data.entity.Reminder
import com.idkotlin.idreminder.presentation.util.ReminderUtil
import com.tutorial.learnlinuxpro.data.source.local.room.AppDatabase
import com.tutorial.learnlinuxpro.presentation.di.component.ApplicationComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by kodeartisan on 30/11/17.
 */
class AlarmReceiver: BroadcastReceiver() {

    private val TAG = AlarmReceiver::class.java.name

    private lateinit var mPendingIntent: PendingIntent
    private lateinit var mReminderDbDisposable: Disposable



    override fun onReceive(context: Context?, intent: Intent?) {
        val reminderId = intent?.getStringExtra(ReminderUtil.REMINDERID)
        val reminderDb = BaseApp.appComponent.getAppDatabase().reminderDao()
        mReminderDbDisposable = reminderDb.getReminder(reminderId!!.toInt())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ reminder: Reminder? -> createNotification(context!!, reminder!!)  }, {})

    }

    public fun cancelAlarm(context: Context, id: Int) {

    }



    private fun createNotification(context: Context, reminder: Reminder) {
        val notificationReminder = NotificationCompat.Builder(context, "1").apply {
            setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
            setSmallIcon(R.drawable.ic_notifications_active)
            setTicker(reminder.title)
            setContentText(reminder.title)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            setAutoCancel(true)
            setOnlyAlertOnce(true)
        }.build()

        BaseApp.appComponent.getNotificationManager().notify(reminder.id.toInt(), notificationReminder)
        if(!mReminderDbDisposable.isDisposed) mReminderDbDisposable.dispose()
    }
}
