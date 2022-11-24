package com.fannybrawijaya.crud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.fannybrawijaya.crud.databinding.ActivityTampilBinding
import org.json.JSONObject


class tampil : AppCompatActivity() {
    private lateinit var binding: ActivityTampilBinding
    val result = ArrayList<model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTampilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTampil.setHasFixedSize(true)
        binding.rvTampil.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        tampil_data()
    }
    fun tampil_data () {
        AndroidNetworking.get("http://192.168.1.8/api/tampil.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        result.clear()
                        if (response.getInt("success") == 1){
                            val jsonArray = response.optJSONArray("data")

                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.optJSONObject(i)
                                result.add(
                                        model(
                                                jsonObject.getString("id_crud"),
                                                jsonObject.getString("nama"),
                                                jsonObject.getString("jenis_kelamin"),
                                                jsonObject.getString("alamat")

                                        )
                                )
                            }
                            val tampil_data = adapter(this@tampil, result)
                            binding.rvTampil.adapter = tampil_data

                        }else{
                            Toast.makeText(this@tampil,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(error: ANError) {
                        Toast.makeText(this@tampil,error.toString(), Toast.LENGTH_SHORT).show()
                    }
                })
    }


}