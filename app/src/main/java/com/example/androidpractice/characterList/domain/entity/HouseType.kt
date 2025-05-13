package com.example.androidpractice.characterList.domain.entity

import androidx.annotation.StringRes
import com.example.androidpractice.R

enum class HouseType(@StringRes val stringRes: Int) {
    Gryffindor(R.string.gryffindor),
    Hufflepuff(R.string.hufflepuff),
    Ravenclaw(R.string.ravenclaw),
    Slytherin(R.string.slytherin),
    Other(R.string.other);

    companion object {
        fun getByValue(type: String?) =
            entries.find { it.name.equals(type, ignoreCase = true) } ?: Other
    }
}