package com.idkotlin.idreminder.data.mapper

/**
 * Created by kodeartisan on 19/11/17.
 */
abstract class Mapper<To, From> {

    abstract fun map(from: From): To
    abstract fun reverse(to: To): From

    fun map(froms: List<From>): List<To>? {
        if(froms.isNotEmpty()) {
            val results = ArrayList<To>(froms.size)
            froms.mapTo(results) { map(it) }

            return  results
        }

        return null
    }

    fun reverse(tos: List<To>): List<From>? {
        if(tos.isNotEmpty()) {
            val results = ArrayList<From>(tos.size)
            tos.mapTo(results) { reverse(it) }

            return  results
        }

        return null
    }

}