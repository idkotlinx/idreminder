package com.kodeartisan.kotlintemplate.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by kodeartisan on 26/10/17.
 */
@Entity(tableName = "todos")
data class Todo(@ColumnInfo(name = "task") var task: String, @ColumnInfo(name = "completed") var completed: Int = 0 ) {
    @PrimaryKey @ColumnInfo(name = "id") var id:Long = 0
}