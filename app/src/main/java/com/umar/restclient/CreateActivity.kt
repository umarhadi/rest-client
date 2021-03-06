/*
 *     REST Client
 *     CreateActivity.kt
 *     Created by Umar Hadi Siswanto on 12/1/2021
 *     email    : uhsiswanto@icloud.com
 *     website 	: https://blog.umarhadi.xyz
 *     Copyright © 2021 Umar Hadi Siswanto. All rights reserved.
 */

package com.umar.restclient

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_create.*
import org.json.JSONObject


class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        supportActionBar?.title = "Tambah Data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tmb_tambah.setOnClickListener {
            //bukan API Lokal
            AndroidNetworking.post("https://api.umarhadi.xyz/index.php/penduduk")
                .addBodyParameter("nama", inp_nama.text.toString())
                .addBodyParameter("alamat", inp_alamat.text.toString())
                .addBodyParameter("tgl_lahir", inp_tgl.text.toString())
                .addBodyParameter("telp", inp_telepon.text.toString())
                .addBodyParameter("email", inp_email.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {

                        this@CreateActivity.finish()
                        Toast.makeText(this@CreateActivity, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(
                            this@CreateActivity,
                            "error: " + anError?.errorDetail.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        this@CreateActivity.finish()
                    }
                })
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}