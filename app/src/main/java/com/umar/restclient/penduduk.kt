package com.umar.restclient

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Penduduk(
        val nama:String?,
        val alamat:String?,
        val tgl_lahir:String?,
        val telp:String?,
        val email:String?,
        val idUser: Int?
        ) : Parcelable