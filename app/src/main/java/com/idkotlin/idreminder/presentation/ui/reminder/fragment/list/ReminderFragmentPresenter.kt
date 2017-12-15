package com.idkotlin.idreminder.presentation.ui.reminder.fragment.list

import android.util.Log
import com.idkotlin.idreminder.data.entity.Reminder
import com.idkotlin.idreminder.domain.executor.scheduler.BaseSchedulerProvider
import com.idkotlin.idreminder.domain.repository.IReminderRepository
import com.tutorial.learnlinuxpro.presentation.ui.base.RxPresenter
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by kodeartisan on 28/11/17.
 */
class ReminderFragmentPresenter @Inject constructor(private val schedulerProvider: BaseSchedulerProvider, private val reminderRepository: IReminderRepository) : RxPresenter<ReminderFragmentContract.View>(),ReminderFragmentContract.Presenter<ReminderFragmentContract.View> {

    val TAG = ReminderFragmentPresenter::class.java.simpleName

    override fun start() {
        val addSubscribe = addSubscribe(
                reminderRepository.fetch()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(this::processData, this::processError)
        )
    }

    override fun insert(reminder: Reminder) {
       if(reminder != null) {
           val addSubscribe = addSubscribe(Completable.fromCallable({
               reminderRepository.insert(reminder)
           }).subscribeOn(schedulerProvider.io()).subscribe())
       }
    }

    private fun processData(reminders: List<Reminder>){
        mView.onGetReminders(reminders)
    }

    private fun processError(error: Throwable) {
        Log.d(TAG, error.message)
    }


}