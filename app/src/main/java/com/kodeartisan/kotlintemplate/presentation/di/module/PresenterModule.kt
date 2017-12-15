package com.tutorial.learnlinuxpro.presentation.di.module

import android.support.annotation.NonNull
import com.kodeartisan.kotlintemplate.domain.executor.scheduler.BaseSchedulerProvider
import com.kodeartisan.kotlintemplate.domain.repository.IReminderRepository
import com.kodeartisan.kotlintemplate.domain.repository.ITodoRepository
import com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.add.ReminderAddFragment
import com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.add.ReminderAddFragmentContract
import com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.add.ReminderAddFragmentPresenter
import com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.list.ReminderFragmentContract
import com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.list.ReminderFragmentPresenter
import com.kodeartisan.kotlintemplate.presentation.ui.todo.TodoContract
import com.kodeartisan.kotlintemplate.presentation.ui.todo.TodoPresenter
import com.tutorial.learnlinuxpro.presentation.di.scope.ActivityScope
import com.tutorial.learnlinuxpro.presentation.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by kodeartisan on 12/10/17.
 */
@Module
class PresenterModule {

    @ActivityScope
    @Provides
    fun examplePresenter(@NonNull baseSchedulerProvider: BaseSchedulerProvider, @NonNull iExampleRepository: ITodoRepository): TodoContract.Presenter<TodoContract.View> = TodoPresenter(baseSchedulerProvider, iExampleRepository)

    @FragmentScope
    @Provides
    fun reminderFragmentPresenter(@NonNull baseSchedulerProvider: BaseSchedulerProvider, @NonNull iReminderRepository: IReminderRepository): ReminderFragmentContract.Presenter<ReminderFragmentContract.View> = ReminderFragmentPresenter(baseSchedulerProvider, iReminderRepository)

    @FragmentScope
    @Provides
    fun reminderAddFragmentPresenter(@NonNull baseSchedulerProvider: BaseSchedulerProvider, @NonNull iReminderRepository: IReminderRepository): ReminderAddFragmentContract.Presenter<ReminderAddFragmentContract.View> = ReminderAddFragmentPresenter(baseSchedulerProvider, iReminderRepository)
}