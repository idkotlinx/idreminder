package com.tutorial.learnlinuxpro.presentation.di.component

import android.app.AlarmManager
import android.app.NotificationManager
import com.idkotlin.idreminder.domain.executor.scheduler.BaseSchedulerProvider
import com.idkotlin.idreminder.domain.repository.IReminderRepository
import com.idkotlin.idreminder.presentation.di.scope.ApplicationScope
import com.idkotlin.idreminder.presentation.receiver.AlarmReceiver
import com.tutorial.learnlinuxpro.data.source.local.room.AppDatabase
import com.tutorial.learnlinuxpro.presentation.bus.RxBus
import com.tutorial.learnlinuxpro.presentation.di.module.ApplicationModule
import com.tutorial.learnlinuxpro.presentation.di.module.DataModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kodeartisan on 12/10/17.
 */
@Singleton
@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface ApplicationComponent {

    fun getSchedulerProvider(): BaseSchedulerProvider
    fun getAppDatabase(): AppDatabase
    fun getIReminderRepository(): IReminderRepository
    fun getRxBus(): RxBus
    fun getAlarmManager(): AlarmManager
    fun getNotificationManager(): NotificationManager

    fun inject(target: AlarmReceiver)


}