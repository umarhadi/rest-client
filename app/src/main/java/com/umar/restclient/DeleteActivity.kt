package com.umar.restclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.activity_delete.*
import org.json.JSONObject

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        supportActionBar?.title = "Hapus Data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnDelete.setOnClickListener {
            //bukan API Lokal
            AndroidNetworking.delete("https://api.umarhadi.xyz/index.php/penduduk")
                .addBodyParameter("id", dltID.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {

                        this@DeleteActivity.finish()
                        Toast.makeText(this@DeleteActivity, "Hapus Data Berhasil", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(
                            this@DeleteActivity,
                            "error: " + anError?.errorDetail.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        this@DeleteActivity.finish()
                    }
                })

        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}