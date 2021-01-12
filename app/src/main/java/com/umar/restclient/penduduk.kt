/*
 *     REST Client
 *     penduduk.kt
 *     Created by Umar Hadi Siswanto on 12/1/2021
 *     email    : uhsiswanto@icloud.com
 *     website 	: https://blog.umarhadi.xyz
 *     Copyright © 2021 Umar Hadi Siswanto. All rights reserved.
 */

package com.umar.restclient

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Penduduk(
        val idUser: Int,
        val nama:String?,
        val alamat:String?,
        val tgl_lahir:String?,
        val telp:String?,
        val email:String?
        ) : Parcelable