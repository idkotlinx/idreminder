package com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.add

import android.util.Log
import com.kodeartisan.kotlintemplate.data.entity.Reminder
import com.kodeartisan.kotlintemplate.domain.executor.scheduler.BaseSchedulerProvider
import com.kodeartisan.kotlintemplate.domain.repository.IReminderRepository
import com.tutorial.learnlinuxpro.presentation.ui.base.RxPresenter
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by kodeartisan on 28/11/17.
 */
class ReminderAddFragmentPresenter @Inject constructor(private val schedulerProvider: BaseSchedulerProvider, private val reminderRepository: IReminderRepository) : RxPresenter<ReminderAddFragmentContract.View>(), ReminderAddFragmentContract.Presenter<ReminderAddFragmentContract.View> {

    val TAG = ReminderAddFragmentPresenter::class.java.simpleName

    override fun insert(reminder: Reminder) {

       reminderRepository.insert(reminder)
               .subscribeOn(schedulerProvider.io())
               .observeOn(schedulerProvider.ui())
               .subscribe(this::processInsert, this::processErrorInsert)
    }

    private fun processInsert(id: Long) {
        mView.onInsertData(id)
    }

    private fun processErrorInsert(throwable: Throwable) {
        Log.d(TAG, "error: ${throwable.printStackTrace()}")
    }

}