package com.idkotlin.idreminder.domain.repository

import com.idkotlin.idreminder.data.entity.Reminder
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by kodeartisan on 27/11/17.
 */
interface IReminderRepository {

    fun fetch(): Single<List<Reminder>>
    fun delete(reminder: Reminder)
    fun insert(reminder: Reminder): Observable<Long>
    fun update(reminder: Reminder): Observable<Int>
}