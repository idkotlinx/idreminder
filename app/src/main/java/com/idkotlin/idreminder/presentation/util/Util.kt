package com.tutorial.learnlinuxpro.presentation.util

import android.content.Context
import android.support.annotation.NonNull

/**
 * Created by kodeartisan on 23/10/17.
 */
object Util {

    private lateinit var context:Context

    fun init(@NonNull context:Context) {
        this.context = context.applicationContext
    }

    fun getContext(): Context = this.context


}