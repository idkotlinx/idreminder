package com.tutorial.learnlinuxpro.data.source.local.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kodeartisan.kotlintemplate.data.entity.Reminder
import com.kodeartisan.kotlintemplate.data.entity.Todo
import com.kodeartisan.kotlintemplate.data.source.local.room.ReminderDao

/**
 * Created by kodeartisan on 16/10/17.
 */
@Database(entities = arrayOf(Todo::class, Reminder::class), version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun exampleDao(): TodoDao
    abstract fun reminderDao(): ReminderDao


}