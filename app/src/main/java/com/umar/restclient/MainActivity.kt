/*
 *     REST Client
 *     MainActivity.kt
 *     Created by Umar Hadi Siswanto on 12/1/2021
 *     email    : uhsiswanto@icloud.com
 *     website 	: https://blog.umarhadi.xyz
 *     Copyright © 2021 Umar Hadi Siswanto. All rights reserved.
 */

package com.umar.restclient

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    //array untuk menyimpan data dari JSON
    val dataPenduduk = ArrayList<Penduduk>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "API Data Penduduk"

        //inisialisasi recycler view
        recycle_view_1.setHasFixedSize(true)
        recycle_view_1.layoutManager = LinearLayoutManager(this)

        //jalankan function mengambil data
        getDataPenduduk()

        //pindah ke activity create
        fab1.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
                startActivity(Intent(this, EditActivity::class.java))
                return true
            }
            R.id.action_delete -> {
                startActivity(Intent(this, DeleteActivity::class.java))
                return true
            }
            R.id.action_refresh -> {
                getDataPenduduk()
            }
            R.id.action_umar -> {
                startActivity(Intent(this, UmarActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }


    //function mengambil data dijalankan kembali setelah resume
    override fun onResume() {
        super.onResume()
        getDataPenduduk()
    }

    //function ambil data
    fun getDataPenduduk(){
        pbLoading.visibility = View.VISIBLE

        //HTTP request menggunakan Fast Android Networking
        AndroidNetworking.get("https://api.umarhadi.xyz/index.php/penduduk")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                //mengolah data ketika request berhasil dan mengembalikan data JSON
                override fun onResponse(response: JSONObject?) {

                    dataPenduduk.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if (jsonArray?.length() == 0) {
                        Toast.makeText(applicationContext, "Data tidak ada.", Toast.LENGTH_SHORT)
                            .show()
                    }

                    for (i in 0 until jsonArray?.length()!!) {
                        val jsonObject = jsonArray?.optJSONObject(i)

                        dataPenduduk.add(
                            Penduduk(
                                jsonObject.getInt("id"),
                                jsonObject.getString("nama"),
                                jsonObject.getString("alamat"),
                                jsonObject.getString("tgl_lahir"),
                                jsonObject.getString("telp"),
                                jsonObject.getString("email")

                            )
                        )

                        if (jsonArray?.length() - 1 == i) {
                            val adapter = RVAdapterPenduduk(applicationContext, dataPenduduk)
                            adapter.notifyDataSetChanged()
                            recycle_view_1.adapter = adapter
                            pbLoading.visibility = View.GONE

                        }
                    }

                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(
                        this@MainActivity,
                        "error: " + anError?.errorDetail.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

}