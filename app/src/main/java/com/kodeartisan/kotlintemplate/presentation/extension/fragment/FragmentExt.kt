package com.tutorial.learnlinuxpro.presentation.extension.fragment

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


/**
 * Created by kodeartisan on 18/10/17.
 */

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}
