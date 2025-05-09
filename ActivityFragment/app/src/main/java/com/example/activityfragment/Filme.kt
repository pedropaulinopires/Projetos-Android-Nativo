package com.example.activityfragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
class Filme(
    var nome: String
) : Parcelable