@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.tutorial.learnlinuxpro.presentation.extension.toast

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by kodeartisan on 14/10/17.
 */
fun Context.toast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
inline fun Context.longToast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
inline fun Context.longToast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
inline fun Fragment.toast(message: Int): Unit = activity.toast(message)
inline fun Fragment.toast(message: CharSequence): Unit = activity.toast(message)
inline fun Fragment.longToast(message: Int): Unit = activity.longToast(message)
inline fun Fragment.longToast(message: CharSequence): Unit = activity.longToast(message)