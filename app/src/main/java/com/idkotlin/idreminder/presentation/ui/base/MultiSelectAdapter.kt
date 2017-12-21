package com.idkotlin.idreminder.presentation.ui.base

import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ActionMode
import android.view.View
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseAdapter
import android.support.v4.content.ContextCompat
import com.idkotlin.idreminder.R
import com.tutorial.learnlinuxpro.presentation.extension.collection.forEachWithIndex


/**
 * Created by kodeartisan on 18/12/17.
 */
abstract class MultiSelectAdapter<T>(context: Context): BaseAdapter<T>(context) {

    var mIsMultiSelectMode = false
    var mMultiSelect = true
    var mActionMode: ActionMode? = null
    val mSelectedList = HashMap<Int, T>()
    private val mItemList = HashMap<Int, StateType<T>>()
    private lateinit var mActionModeCallback: ActionMode.Callback
    private lateinit var mRecyclerview: RecyclerView

    fun setActionModeCallback(actionModeCallback: ActionMode.Callback) = {mActionModeCallback  = actionModeCallback}

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int, payloads: MutableList<Any>?) {
        processUpdate(position)
        holder as MultiSelectViewHolder

        super.onBindViewHolder(holder, position, payloads)
        holder.setState(checkState(position), position)

    }



    fun multiSelect(key: Int, value: T) {
        mActionMode?.let {
            if(mSelectedList.contains(key)) {mSelectedList.remove(key); Log.d("MultiSelect", "containKey: ${key}")}
            else { mSelectedList.put(key, value);}

            if(mSelectedList.size > 0) (mActionMode as ActionMode).title = "${mSelectedList.size} selected"
            else (mActionMode as ActionMode).title = ""

        }
    }

    fun setActive(@NonNull view: View, state: Boolean) {}

    fun select(position: Int){
        if(mMultiSelect.not()) mMultiSelect = true

        processClick(position)
    }


    private fun processClick(position: Int) {
        Log.d("POSITION", position.toString())
        if(mItemList.containsKey(position)) {
            when(mItemList[position]!!.state) {
                is State.ACTIVE -> {perform(Action.DESELECT, position, true, true)}
                is State.INACTIVE -> {perform(Action.SELECT, position, true, true)}
            }
        }
        val selectedItem = mItemList.filter { it.value.state == State.ACTIVE }
        mActionMode?.let {
            if(selectedItem.isNotEmpty()) (mActionMode as ActionMode).title = "${selectedItem.size} selected"
            else (mActionMode as ActionMode).title = ""
        }
    }

    fun setItemList(itemList: HashMap<Int, StateType<T>>) {
        mItemList.clear()
        mItemList.putAll(itemList)
    }

    fun checkState(position: Int): Boolean = when(mItemList[position]!!.state) {
        is State.ACTIVE -> true
        is State.INACTIVE -> false

    }

    fun getSelectedItemList(): List<T> = getSelectedItemListInternal()

    fun getSelectedItemCount(): Int = getSelectedItemList().size

    fun deleteSelectedItemList() {
        val activeItemList = mItemList.filter { it.value.state == State.ACTIVE }
        val newData = mData.filterIndexed { index, t -> activeItemList.containsKey(index).not()  }
        setData(newData)

        mActionMode?.finish()
        mMultiSelect = false
        refreshItemList()


    }

    fun clearSelectedItemList() {
        mItemList.clear()
    }

    private fun refreshItemList() {
        mItemList.clear()
        mData.forEachWithIndex { i, t -> mItemList.put(i, StateType(State.INACTIVE, t))  }
    }

    private fun getSelectedItemListInternal(): List<T> {
        val selectedList = mutableListOf<T>()
        mItemList.filter { it.value.state == State.ACTIVE  }
                .map { selectedList.add(it.value.entity) }
        return selectedList
    }

    fun perform(action: Action, position: Int, withCallback: Boolean, withVibration: Boolean) {
        when(action) {
            is Action.SELECT -> {
                mItemList.put(position, StateType(State.ACTIVE, mData[position]))
            }
            is Action.DESELECT -> {
                mItemList.put(position, StateType(State.INACTIVE, mData[position]))
            }
        }

        notifyItemChanged(position)

    }


    private fun processUpdate(position: Int) {
        if(mItemList.containsKey(position)) {
            when(mItemList[position]!!.state) {
                is State.ACTIVE -> {}
                is State.INACTIVE -> {}
            }
        } else {
            mItemList.put(position, StateType(State.INACTIVE, mData[position]))
            //processUpdate(view, position)
        }
    }

    private fun processNotifyDataSetChanged(position: Int) {
        mRecyclerview.let { notifyItemChanged(position) }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        mRecyclerview = recyclerView!!


        super.onAttachedToRecyclerView(recyclerView)
    }
    sealed class Action {
        object SELECT: Action()
        object DESELECT: Action()
    }

    sealed class State {
        object ACTIVE: State()
        object INACTIVE: State()
    }

    data class StateType<T>(val state: State, val entity: T)

    abstract class MultiSelectViewHolder(itemView: View?): BaseViewHolder(itemView) {

        private var state: Boolean? = null

        open fun onChangeState(state: Boolean, position: Int){
            if(state) itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            else itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark))
        }

        fun setState(state: Boolean, position: Int) {
            this.state = state
            onChangeState(state, position)
        }

    }

}