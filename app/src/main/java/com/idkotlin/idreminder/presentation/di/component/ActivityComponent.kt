package com.tutorial.learnlinuxpro.presentation.di.component

import com.idkotlin.idreminder.presentation.ui.reminder.ReminderActivity
import com.tutorial.learnlinuxpro.presentation.di.module.ActivityModule
import com.tutorial.learnlinuxpro.presentation.di.module.PresenterModule
import com.tutorial.learnlinuxpro.presentation.di.scope.ActivityScope
import dagger.Component

/**
 * Created by kodeartisan on 12/10/17.
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class) ,modules = arrayOf(ActivityModule::class, PresenterModule::class))
interface ActivityComponent {

    fun inject(target: ReminderActivity)
}