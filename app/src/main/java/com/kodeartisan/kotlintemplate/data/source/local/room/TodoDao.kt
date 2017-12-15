package com.tutorial.learnlinuxpro.data.source.local.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.kodeartisan.kotlintemplate.data.entity.Todo
import io.reactivex.Flowable

/**
 * Created by kodeartisan on 17/10/17.
 */
@Dao interface TodoDao {

    @Query("SELECT * FROM todos")
    fun getTodos(): Flowable<List<Todo>>

}