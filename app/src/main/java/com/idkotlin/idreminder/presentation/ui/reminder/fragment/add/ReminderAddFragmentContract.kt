package com.idkotlin.idreminder.presentation.ui.reminder.fragment.add

import com.idkotlin.idreminder.data.entity.Reminder
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseContract

/**
 * Created by kodeartisan on 28/11/17.
 */
interface ReminderAddFragmentContract {

    interface View : BaseContract.BaseView {
        fun onInsertData(id: Long)
    }

    interface Presenter<T : BaseContract.BaseView>: BaseContract.BasePresenter<T> {
        fun insert(reminder: Reminder)
    }
}