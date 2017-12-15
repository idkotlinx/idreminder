package com.kodeartisan.kotlintemplate.data.source.local.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kodeartisan.kotlintemplate.data.entity.Reminder
import com.kodeartisan.kotlintemplate.data.entity.Todo
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by kodeartisan on 19/11/17.
 */
@Dao
interface ReminderDao{

    @Query("SELECT * FROM reminders")
    fun getReminders(): Single<List<Reminder>>

    @Insert
    fun insert(reminder: Reminder): Long

}