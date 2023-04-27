package com.vmn.theschoolofrock.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * I've added this function to get the list of genre names from the list and make it single string with provided separator
 * I've followed generic approach here
 */
infix fun <T> List<T>.toString(config: StringConfig): String {

    return try {
        val property = this.firstOrNull()!!::class.java.getDeclaredField(config.property)
        property.isAccessible = true
        this.mapNotNull { property.get(it)?.toString() }.joinToString(config.divider)
    } catch (e: NoSuchFieldException) {
        ""
    }

}

@Parcelize
data class StringConfig(
    val property: String,
    val divider: String
) : Parcelable
