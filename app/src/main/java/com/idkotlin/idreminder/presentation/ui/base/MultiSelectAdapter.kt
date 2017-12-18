package com.idkotlin.idreminder.presentation.ui.base

import android.content.Context
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseAdapter

/**
 * Created by kodeartisan on 18/12/17.
 */
abstract class MultiSelectAdapter<T>(context: Context): BaseAdapter<T>(context) {

    var mIsMultiSelect = false
    var mActionMode: ActionMode? = null
    val mSelectedList = HashMap<Int, T>()
    private lateinit var mActionModeCallback: ActionMode.Callback

    public fun setActionModeCallback(actionModeCallback: ActionMode.Callback) = {mActionModeCallback  = actionModeCallback}

    public fun multiSelect(key: Int, value: T) {

        mActionMode?.let {
            if(mSelectedList.contains(key)) {mSelectedList.remove(key); Log.d("MultiSelect", "containKey: ${key}")}
            else { mSelectedList.put(key, value);}

            if(mSelectedList.size > 0) (mActionMode as ActionMode).title = "${mSelectedList.size} selected"
            else (mActionMode as ActionMode).title = ""

        }
    }

}