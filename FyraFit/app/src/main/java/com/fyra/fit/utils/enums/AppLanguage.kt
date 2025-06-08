package com.fyra.fit.utils.enums

enum class AppLanguage(val code: String, val displayName: String) {

    ENGLISH("en", "English"),
    PORTUGUESE("pt", "Português");

    companion object {

        fun fromCode(code: String): AppLanguage {
            return AppLanguage.entries.find { it.code == code } ?: ENGLISH
        }
    }
}
