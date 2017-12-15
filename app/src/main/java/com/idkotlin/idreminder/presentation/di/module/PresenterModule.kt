package com.tutorial.learnlinuxpro.presentation.di.module

import android.support.annotation.NonNull
import com.idkotlin.idreminder.domain.executor.scheduler.BaseSchedulerProvider
import com.idkotlin.idreminder.domain.repository.IReminderRepository
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.add.ReminderAddFragmentContract
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.add.ReminderAddFragmentPresenter
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.list.ReminderFragmentContract
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.list.ReminderFragmentPresenter
import com.tutorial.learnlinuxpro.presentation.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by kodeartisan on 12/10/17.
 */
@Module
class PresenterModule {

    @FragmentScope
    @Provides
    fun reminderFragmentPresenter(@NonNull baseSchedulerProvider: BaseSchedulerProvider, @NonNull iReminderRepository: IReminderRepository): ReminderFragmentContract.Presenter<ReminderFragmentContract.View> = ReminderFragmentPresenter(baseSchedulerProvider, iReminderRepository)

    @FragmentScope
    @Provides
    fun reminderAddFragmentPresenter(@NonNull baseSchedulerProvider: BaseSchedulerProvider, @NonNull iReminderRepository: IReminderRepository): ReminderAddFragmentContract.Presenter<ReminderAddFragmentContract.View> = ReminderAddFragmentPresenter(baseSchedulerProvider, iReminderRepository)
}