package com.vmn.theschoolofrock.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class ResultCommon(
    var id: Int = 0,
    var movieName: String = "",
    var moviePoster: String = ""
) : Parcelable
