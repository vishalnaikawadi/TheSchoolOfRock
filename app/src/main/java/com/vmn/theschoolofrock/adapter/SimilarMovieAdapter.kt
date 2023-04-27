package com.vmn.theschoolofrock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vmn.domain.entity.SimilarMoviesEntity
import com.vmn.theschoolofrock.core.BaseAdapter
import com.vmn.theschoolofrock.core.BaseViewHolder
import com.vmn.theschoolofrock.databinding.ItemSimilarMovieBinding

class SimilarMovieAdapter :
    BaseAdapter<BaseViewHolder<SimilarMoviesEntity.Result>, SimilarMoviesEntity.Result>() {

    inner class SimilarMovieViewHolder(
        private val binding: ItemSimilarMovieBinding
    ) : BaseViewHolder<SimilarMoviesEntity.Result>(binding.root) {
        override fun bindData(t: SimilarMoviesEntity.Result) {
            binding.data = t
            binding.executePendingBindings()
        }

    }

    override fun createHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SimilarMoviesEntity.Result> {
        val binding =
            ItemSimilarMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SimilarMovieViewHolder(binding)
    }
}