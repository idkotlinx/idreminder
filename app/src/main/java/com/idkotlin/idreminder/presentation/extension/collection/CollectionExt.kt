@file:Suppress("unused", "NOTHING_TO_INLINE")
package com.tutorial.learnlinuxpro.presentation.extension.collection

/**
 * Created by kodeartisan on 14/10/17.
 */
/**
 * Iterate the receiver [List] using an index.
 *
 * @f an action to invoke on each list element.
 */
inline fun <T> List<T>.forEachByIndex(f: (T) -> Unit) {
    val lastIndex = size - 1
    for (i in 0..lastIndex) {
        f(get(i))
    }
}

/**
 * Iterate the receiver [List] using an index.
 *
 * @f an action to invoke on each list element (index, element).
 */
inline fun <T> List<T>.forEachWithIndex(f: (Int, T) -> Unit) {
    val lastIndex = size - 1
    for (i in 0..lastIndex) {
        f(i, get(i))
    }
}

/**
 * Iterate the receiver [List] backwards using an index.
 *
 * @f an action to invoke on each list element.
 */
inline fun <T> List<T>.forEachReversedByIndex(f: (T) -> Unit) {
    var i = size - 1
    while (i >= 0) {
        f(get(i))
        i--
    }
}

/**
 * Iterate the receiver [List] backwards using an index.
 *
 * @f an action to invoke on each list element (index, element).
 */
inline fun <T> List<T>.forEachReversedWithIndex(f: (Int, T) -> Unit) {
    var i = size - 1
    while (i >= 0) {
        f(i, get(i))
        i--
    }
}

/**
 * Convert the Android pair to a Kotlin one.
 *
 * @see [toAndroidPair].
 */
inline fun <F, S> android.util.Pair<F, S>.toKotlinPair(): Pair<F, S> {
    return first to second
}

/**
 * Convert the Kotlin pair to an Android one.
 *
 * @see [toKotlinPair].
 */
inline fun <F, S> Pair<F, S>.toAndroidPair(): android.util.Pair<F, S> {
    return android.util.Pair(first, second)
}