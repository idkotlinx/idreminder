package com.kodeartisan.kotlintemplate.domain.repository

import com.kodeartisan.kotlintemplate.data.entity.Todo
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by kodeartisan on 26/10/17.
 */
interface ITodoRepository {

    fun getTodos(): Flowable<List<Todo>>
}