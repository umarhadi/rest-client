/*
 *     REST Client
 *     UmarActivity.kt
 *     Created by Umar Hadi Siswanto on 12/1/2021
 *     email    : uhsiswanto@icloud.com
 *     website 	: https://blog.umarhadi.xyz
 *     Copyright © 2021 Umar Hadi Siswanto. All rights reserved.
 */

package com.umar.restclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_umar.*

class UmarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umar)
        supportActionBar?.title = "Umar Hadi Siswanto"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val media = "https://sia.uty.ac.id/fotokecil/5180411012.jpg"
        if (media !== null) {
            Glide.with(this)
                .load(media)
                .into(imgUmar)
        } else {
            imgUmar.setImageResource(R.drawable.ic_launcher_background)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}