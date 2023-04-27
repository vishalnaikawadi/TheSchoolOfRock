package com.vmn.theschoolofrock.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.vmn.domain.entity.MoviesNowPlayingEntity
import com.vmn.domain.entity.SimilarMoviesEntity
import com.vmn.theschoolofrock.R


const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(IMAGE_BASE_URL + imageUrl)
            .into(this)
    }
}

@BindingAdapter("setCastProfile")
fun ImageView.setCastProfile(
    imageUrl: String?
) {

    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(IMAGE_BASE_URL + imageUrl)
            .fitCenter()
            .circleCrop()
            .error(R.drawable.ic_broken_image_24)
            .into(this)
    }
}


@BindingAdapter("isVisible")
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("ratings")
fun TextView.setRatings(ratings: Float) {
    text = String.format("%.1f", ratings)
}

@BindingAdapter("similarMovieResult")
fun TextView.showAdditionalInfoForSimilarMovie(result: SimilarMoviesEntity.Result) {
    text = "❤ ${result.voteCount}  \uD83D\uDD25 ${result.voteAverage}"
}

@BindingAdapter("movieExtras")
fun TextView.showExtras(result: MoviesNowPlayingEntity.Result) {

    val language = Language.fromCode(result.originalLanguage) ?: Language.ENGLISH

    text = "${result.voteAverage} ◦ ${language.lang} ◦ ${result.voteCount}"
}