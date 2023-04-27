package com.vmn.theschoolofrock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vmn.domain.entity.CreditsEntity
import com.vmn.theschoolofrock.core.BaseAdapter
import com.vmn.theschoolofrock.core.BaseViewHolder
import com.vmn.theschoolofrock.databinding.ItemMovieCastBinding

class MovieCastAdapter : BaseAdapter<BaseViewHolder<CreditsEntity.Cast>, CreditsEntity.Cast>() {

    inner class CastViewHolder(
        private val binding: ItemMovieCastBinding
    ) : BaseViewHolder<CreditsEntity.Cast>(binding.root) {
        override fun bindData(t: CreditsEntity.Cast) {
            binding.data = t
            binding.executePendingBindings()
        }

    }

    override fun createHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CreditsEntity.Cast> {
        val binding =
            ItemMovieCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CastViewHolder(binding)
    }
}