package com.idkotlin.idreminder.presentation.ui.reminder.fragment.list

import android.util.Log
import com.idkotlin.idreminder.data.entity.Reminder
import com.idkotlin.idreminder.domain.executor.scheduler.BaseSchedulerProvider
import com.idkotlin.idreminder.domain.repository.IReminderRepository
import com.tutorial.learnlinuxpro.presentation.ui.base.RxPresenter
import io.reactivex.Completable
import java.text.FieldPosition
import javax.inject.Inject

/**
 * Created by kodeartisan on 28/11/17.
 */
class ReminderFragmentPresenter @Inject constructor(private val schedulerProvider: BaseSchedulerProvider, private val reminderRepository: IReminderRepository) : RxPresenter<ReminderFragmentContract.View>(),ReminderFragmentContract.Presenter<ReminderFragmentContract.View> {


    val TAG = ReminderFragmentPresenter::class.java.simpleName

    private var updatePosition = 0

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

    override fun delete(reminders: List<Reminder>) {
        Log.d(TAG, reminders.size.toString())
        mView.onDeletedReminders(reminders)
        addSubscribe(reminderRepository.delete(reminders)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({mView.onDeletedReminders(reminders)}, {it.printStackTrace()}))
    }

    override fun update(reminder: Reminder, position: Int) {
        updatePosition = position
        reminder.apply {
            active = !active
        }

        addSubscribe(reminderRepository.update(reminder)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(this::processUpdateReminderItem, {it.printStackTrace()}))
    }

    private fun processUpdateReminderItem(id: Int) {
        mView.onUpdateReminderItem(updatePosition)
    }

    private fun processData(reminders: List<Reminder>){
        mView.onGetReminders(reminders)
    }

    private fun processError(error: Throwable) {
        Log.d(TAG, error.message)
    }


}