package com.idkotlin.idreminder.data.source.local.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.idkotlin.idreminder.data.entity.Reminder
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