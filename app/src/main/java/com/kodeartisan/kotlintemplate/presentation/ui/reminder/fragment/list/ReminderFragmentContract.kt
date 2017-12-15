package com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.list

import com.kodeartisan.kotlintemplate.data.entity.Reminder
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseContract

/**
 * Created by kodeartisan on 28/11/17.
 */
interface ReminderFragmentContract {

    interface View : BaseContract.BaseView {
        fun onGetReminders(reminders: List<Reminder>)
    }

    interface Presenter<T : BaseContract.BaseView>: BaseContract.BasePresenter<T> {
        fun start()
        fun insert(reminder: Reminder)
    }
}