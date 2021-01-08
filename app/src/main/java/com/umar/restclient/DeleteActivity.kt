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

        btnDelete.setOnClickListener {
            AndroidNetworking.delete("https://api.umarhadi.xyz/index.php/penduduk")
                .addBodyParameter("id", dltID.text.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        Toast.makeText(
                            applicationContext,
                            response?.getString("status"),
                            Toast.LENGTH_SHORT
                        ).show()

                        if (response?.getString("status")?.contains("successfully")!!) {
                            this@DeleteActivity.finish()
                        }

                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT)
                            .show()
                    }


                })

        }
    }
}