package com.tutorial.learnlinuxpro.presentation.di.module

import android.app.AlarmManager
import android.app.NotificationManager
import android.arch.persistence.room.Room
import android.content.Context
import com.idkotlin.idreminder.BaseApp
import com.idkotlin.idreminder.domain.executor.scheduler.BaseSchedulerProvider
import com.idkotlin.idreminder.domain.executor.scheduler.SchedulerProvider
import com.tutorial.learnlinuxpro.data.source.local.room.AppDatabase
import com.tutorial.learnlinuxpro.presentation.bus.RxBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kodeartisan on 12/10/17.
 */
@Module
class ApplicationModule(private val app: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): BaseApp = app

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "data.db")
                .build()
    }

    @Provides
    @Singleton
    fun provideRxBus(): RxBus = RxBus()

    @Provides
    @Singleton
    fun provideAlarmManager(): AlarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @Provides
    @Singleton
    fun provideNotificationManager(): NotificationManager = app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}