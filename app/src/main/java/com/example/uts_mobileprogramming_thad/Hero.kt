package com.example.uts_mobileprogramming_naufal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hero(
    val name: String,
    val description: String,
    val photo: Int,
    val keterangan: String // Tambahkan ini
) : Parcelable