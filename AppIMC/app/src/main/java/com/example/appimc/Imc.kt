package com.example.appimc

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Imc (
    var peso:Double,
    var altura: Double,
    var resultado: String,
) : Parcelable