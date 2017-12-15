package com.kodeartisan.kotlintemplate.data.repository

import android.support.annotation.NonNull
import com.kodeartisan.kotlintemplate.data.entity.Reminder
import com.kodeartisan.kotlintemplate.domain.executor.scheduler.BaseSchedulerProvider
import com.kodeartisan.kotlintemplate.domain.repository.IReminderRepository
import com.tutorial.learnlinuxpro.data.source.local.room.AppDatabase
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by kodeartisan on 27/11/17.
 */
class ReminderImpl @Inject constructor(@NonNull private val schedulerProvider: BaseSchedulerProvider, @NonNull private val appDatabase: AppDatabase): IReminderRepository {

    private val TAG = ReminderImpl::class.java.name

    override fun fetch(): Single<List<Reminder>> = appDatabase.reminderDao().getReminders().subscribeOn(schedulerProvider.multi())

    override fun delete(reminder: Reminder) {

    }

    override fun insert(reminder: Reminder): Observable<Long>{
        return Observable.fromCallable { appDatabase.reminderDao().insert(reminder) }

    }





}