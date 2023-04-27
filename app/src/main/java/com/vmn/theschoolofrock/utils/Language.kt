package com.vmn.theschoolofrock.utils

enum class Language(val code: String, val lang: String) {
    ENGLISH("en", "English"),
    SPANISH("es", "Spanish"),
    FRENCH("fr", "French"),
    GERMAN("de", "German"),
    ITALIAN("it", "Italian");

    companion object {
        fun fromCode(code: String): Language? = values().find { it.code == code }
        fun fromName(name: String): Language? =
            values().find { it.lang.equals(name, ignoreCase = true) }
    }
}