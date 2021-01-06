package com.umar.restclient

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    val EXTRA_URL = "imageUrl"
    val EXTRA_CREATOR = "creatorName"
    val EXTRA_LIKES = "likeCount"

    //array untuk menyimpan data dari JSON
    val dataPenduduk = ArrayList<Penduduk>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    //function mengambil data dijalankan kembali setelah resume
    override fun onResume() {
        super.onResume()
        getDataPenduduk()
    }

    //function ambil data
    private fun getDataPenduduk(){

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
                        Toast.makeText(applicationContext, "Data tidak ada.", Toast.LENGTH_SHORT).show()
                    }

                    for (i in 0 until jsonArray?.length()!!) {
                        val jsonObject = jsonArray?.optJSONObject(i)

                        dataPenduduk.add(
                                Penduduk(
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    //FAB

}