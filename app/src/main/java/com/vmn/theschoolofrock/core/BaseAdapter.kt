package com.vmn.theschoolofrock.core

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<H : BaseViewHolder<M>, M> : RecyclerView.Adapter<H>() {
    /**
     * The Data list.
     */
    var dataList: MutableList<M>? = null
        private set
    private var recyclerView: RecyclerView? = null
    var isLoading: Boolean = true
    var loadmoreListener: OnLoadMoreListener? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        return createHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        bindViewHolderData(holder, position)
    }

    override fun getItemCount(): Int {
        return if (null == dataList) 0 else dataList!!.size
    }

    /**
     * Create holder h.
     *
     * @param parent   the parent
     * @param viewType the view type
     * @return the h
     */
    abstract fun createHolder(parent: ViewGroup, viewType: Int): H

    /**
     * Bind view holder data.
     *
     * @param holder   the holder
     * @param position the position
     */
    protected fun bindViewHolderData(holder: H, position: Int) {
        dataList?.let {
            holder.bindData(it[position])
        }
    }

    /**
     * Change data.
     *
     * @param m the m
     */
    open fun changeData(m: List<M>?) {
        dataList = m?.toMutableList()
        notifyDataSetChanged()
    }

    open fun addData(m: M) {
        if (null == dataList) {
            dataList = ArrayList()
        }
        dataList?.add(m)
        notifyDataSetChanged()
    }

    open fun refreshData() {
        notifyDataSetChanged()
    }

    /**
     * update single item in the list.
     */
    open fun updateData(pos: Int) {
        notifyItemChanged(pos)
    }

    /**
     * Update data.
     *
     * @param m the m
     */
    fun updateData(m: List<M>?) {
        m?.let {
            dataList?.addAll(it) ?: run { dataList = it.toMutableList() }
            notifyDataSetChanged()
        }
    }

    open fun updateDataAfterClear(m: List<M>?) {
        m?.let {
            dataList?.clear()
            dataList?.addAll(it) ?: run { dataList = it.toMutableList() }
            notifyDataSetChanged()
        }
    }

    fun enableLoadMore(listener: OnLoadMoreListener) {
        loadmoreListener = listener
        recyclerView?.addOnScrollListener(scrollListener)
    }

    fun removeLoadMore() {
        recyclerView?.removeOnScrollListener(scrollListener)
    }

    /**
     * Gets item.
     *
     * @param position the position
     * @return the item
     */
    fun getItem(position: Int): M? {
        return if (dataList.isNullOrEmpty()) null else dataList?.get(position)
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val manager = recyclerView.layoutManager as? LinearLayoutManager

            val canNoLongerScroll =
                if (manager?.orientation == LinearLayoutManager.HORIZONTAL) !recyclerView.canScrollHorizontally(
                    1
                )
                else !recyclerView.canScrollVertically(1)

            if (canNoLongerScroll
            ) {
                loadmoreListener?.onLoadMore()
                isLoading = true

            }
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}