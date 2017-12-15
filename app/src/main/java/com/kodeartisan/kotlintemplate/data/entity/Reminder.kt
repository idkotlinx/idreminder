package com.kodeartisan.kotlintemplate.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by kodeartisan on 19/11/17.
 */
@Entity(tableName = "reminders")
data class Reminder(@ColumnInfo(name = "title") var title: String,
                    @ColumnInfo(name = "date") var date: String,
                    @ColumnInfo(name = "time") var time: String,
                    @ColumnInfo(name = "repeat") var repeat: Boolean,
                    @ColumnInfo(name = "repeat_no") var repeatNo: Int,
                    @ColumnInfo(name = "repeat_type") var repeatType: String,
                    @ColumnInfo(name = "active") var active: Boolean)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id:Long = 0
}
