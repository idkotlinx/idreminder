package com.tutorial.learnlinuxpro.presentation.di.component

import android.app.AlarmManager
import com.kodeartisan.kotlintemplate.domain.executor.scheduler.BaseSchedulerProvider
import com.kodeartisan.kotlintemplate.domain.repository.IReminderRepository
import com.kodeartisan.kotlintemplate.domain.repository.ITodoRepository
import com.kodeartisan.kotlintemplate.presentation.di.scope.ApplicationScope
import com.kodeartisan.kotlintemplate.presentation.receiver.AlarmReceiver
import com.tutorial.learnlinuxpro.data.source.local.room.AppDatabase
import com.tutorial.learnlinuxpro.presentation.bus.RxBus
import com.tutorial.learnlinuxpro.presentation.di.module.ApplicationModule
import com.tutorial.learnlinuxpro.presentation.di.module.DataModule
import com.tutorial.learnlinuxpro.presentation.di.module.NetworkModule
import com.tutorial.learnlinuxpro.presentation.di.scope.ActivityScope
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kodeartisan on 12/10/17.
 */
@Singleton
@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, DataModule::class))
interface ApplicationComponent {

    fun getSchedulerProvider(): BaseSchedulerProvider
    fun getAppDatabase(): AppDatabase
    fun getIExampleRepository(): ITodoRepository
    fun getIReminderRepository(): IReminderRepository
    fun getRxBus(): RxBus
    fun getAlarmManager(): AlarmManager

    fun inject(target: AlarmReceiver)


}