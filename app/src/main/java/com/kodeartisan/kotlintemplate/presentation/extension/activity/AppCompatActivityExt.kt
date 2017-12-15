package com.tutorial.learnlinuxpro.presentation.extension.activity

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.tutorial.learnlinuxpro.presentation.extension.fragment.inTransaction

/**
 * Created by kodeartisan on 18/10/17.
 */
fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, nameBackstack: String) {
    supportFragmentManager.inTransaction { replace(frameId, fragment).addToBackStack(nameBackstack) }
}