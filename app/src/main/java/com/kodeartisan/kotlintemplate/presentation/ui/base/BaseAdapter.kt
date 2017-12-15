package com.tutorial.learnlinuxpro.presentation.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

/**
 * Created by kodeartisan on 18/10/17.
 */
abstract class BaseAdapter<T>
constructor(val mContext: Context) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    var mData: MutableList<T> = mutableListOf()

    protected lateinit var mItemClickListener: (View, Int) -> Unit
    protected lateinit var mItemLongClickListener: ItemLongClickListener
    protected val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        holder?.fillView(position = position)
        holder?.itemView?.setOnClickListener{mItemClickListener(holder.itemView, position)}
        holder?.itemView?.setOnLongClickListener{
            mItemLongClickListener.onItemLongClick(position)
            true
        }
    }

    override fun getItemCount(): Int = mData.size

    fun setData(data: List<T>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun addSingleData(data: T) = mData.add(data)

    fun setItemClickListener(itemClick: (View, Int) -> Unit) {
        mItemClickListener = itemClick
    }

    fun setItemLongClickListener(listener: ItemLongClickListener) = {mItemLongClickListener = listener}

    abstract class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        abstract fun fillView(position: Int)
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    interface ItemLongClickListener {
        fun onItemLongClick(position: Int)
    }


}