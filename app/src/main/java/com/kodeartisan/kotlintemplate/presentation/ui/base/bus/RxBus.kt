package com.tutorial.learnlinuxpro.presentation.bus

import android.os.Handler
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.BackpressureStrategy
import io.reactivex.internal.operators.single.SingleInternalHelper.toFlowable
import io.reactivex.Flowable
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import dagger.Provides


/**
 * Created by kodeartisan on 19/10/17.
 */
class RxBus {

    private val bus = PublishRelay.create<Any>().toSerialized()
    @Volatile private var isLocked: Boolean = false
    private val handler = Handler()

    fun send(o: Any) {
        bus.accept(o)
    }

    @JvmOverloads
    fun sendWithLock(event: Any, lockOutTime: Long = 500) {
        if (!isLocked) {
            isLocked = true
            send(event)
            handler.postDelayed({ isLocked = false }, lockOutTime)
        }
    }

    fun asFlowable(): Flowable<Any> {
        return bus.toFlowable(BackpressureStrategy.LATEST)
    }

    fun hasObservers(): Boolean {
        return bus.hasObservers()
    }
}