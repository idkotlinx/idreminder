package com.tutorial.learnlinuxpro.presentation.di.component

import com.idkotlin.idreminder.presentation.ui.reminder.fragment.add.ReminderAddFragment
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.list.ReminderFragment
import com.tutorial.learnlinuxpro.presentation.di.module.FragmentModule
import com.tutorial.learnlinuxpro.presentation.di.module.PresenterModule
import com.tutorial.learnlinuxpro.presentation.di.scope.FragmentScope
import dagger.Component

/**
 * Created by kodeartisan on 18/10/17.
 */
@FragmentScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class, PresenterModule::class))
interface FragmentComponent {

    fun inject(target: ReminderFragment)
    fun inject(target: ReminderAddFragment)
}