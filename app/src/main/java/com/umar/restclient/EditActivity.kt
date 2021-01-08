package com.umar.restclient

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_edit.*
import org.json.JSONObject

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar?.title = "Edit Data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        btnUpdate.setOnClickListener {
            //bukan API Lokal
            AndroidNetworking.put("https://api.umarhadi.xyz/index.php/penduduk")
                .addBodyParameter("id", edtID.text.toString())
                .addBodyParameter("nama", edtNama.text.toString())
                .addBodyParameter("alamat", edtAlamat.text.toString())
                .addBodyParameter("tgl_lahir", edtTgl.text.toString())
                .addBodyParameter("telp", edtTelp.text.toString())
                .addBodyParameter("email", edtEmail.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {

                        this@EditActivity.finish()
                        Toast.makeText(this@EditActivity, "Edit Data Berhasil", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(
                            this@EditActivity,
                            "error: " + anError?.errorDetail.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        this@EditActivity.finish()
                    }
                })
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}