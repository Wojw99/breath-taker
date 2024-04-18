package com.example.breathtaker.common

import com.example.breathtaker.R

object IconTagMapper {
    fun getDrawableIdFor(iconTag: String): Int {
        if (iconTag == "help") {
            return R.drawable.baseline_help_24
        } else if (iconTag == "breath") {
            return R.drawable.baseline_air_24
        }
        return R.drawable.baseline_article_24
    }
}