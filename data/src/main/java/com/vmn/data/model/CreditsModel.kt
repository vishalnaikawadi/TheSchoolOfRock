package com.vmn.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CreditsModel(
    val cast: List<Cast>?,
    val id: Int?
) {
    data class Cast(
        val character: String?,
        val gender: Int?,
        val id: Int?,
        @SerializedName("known_for_department")
        val knownForDepartment: String?,
        val name: String?,
        @SerializedName("profile_path")
        val profilePath: String?
    )
}