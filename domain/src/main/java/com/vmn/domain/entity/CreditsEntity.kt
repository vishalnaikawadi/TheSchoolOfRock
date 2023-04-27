package com.vmn.domain.entity

import androidx.annotation.Keep

@Keep
data class CreditsEntity(
    val cast: List<Cast>,
    val id: Int
) {
    data class Cast(
        val character: String,
        val gender: Int,
        val id: Int,
        val knownForDepartment: String,
        val name: String,
        val profilePath: String
    )
}
