package com.vmn.theschoolofrock.adapter

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.vmn.domain.entity.MoviesNowPlayingEntity
import com.vmn.theschoolofrock.core.BaseAdapter
import com.vmn.theschoolofrock.core.BaseViewHolder
import com.vmn.theschoolofrock.databinding.ItemMoviePlayingNowBinding

class MoviePlayingNowAdapter(
    private val callback: (item: MoviesNowPlayingEntity.Result, poster: View, title: View) -> Unit
) :
    BaseAdapter<BaseViewHolder<MoviesNowPlayingEntity.Result>, MoviesNowPlayingEntity.Result>() {

    private inner class MoviePlayingNowViewHolder(
        private val binding: ItemMoviePlayingNowBinding
    ) : BaseViewHolder<MoviesNowPlayingEntity.Result>(binding.root) {
        override fun bindData(t: MoviesNowPlayingEntity.Result) {
            binding.data = t
            binding.root.setOnClickListener {
                callback(t, binding.imgPoster, binding.tvMovieTitle)
            }
            binding.executePendingBindings()
        }

    }

    fun filterData(input: String, originalList: List<MoviesNowPlayingEntity.Result>) {

        val filteredList = originalList
            .filter { movie ->
                movie.originalTitle
                    .split(" ")
                    .any { word ->
                        word
                            .startsWith(input, ignoreCase = true)
                    }
            }
            .take(5)//only showing top 5 elements, because user rarely scroll down while searching.
            .toList()

        updateDataAfterClear(filteredList)
    }

    override fun createHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MoviesNowPlayingEntity.Result> {

        val binding =
            ItemMoviePlayingNowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviePlayingNowViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return dataList?.get(position)?.id?.toLong() ?: super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return dataList?.get(position)?.id ?: super.getItemViewType(position)
    }
}