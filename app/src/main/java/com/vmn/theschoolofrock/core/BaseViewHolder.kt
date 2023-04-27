package com.vmn.theschoolofrock.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<E>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Bind data.
     *
     * @param t the t
     */
    abstract fun bindData(t: E)
}