package com.fannybrawijaya.crud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.fannybrawijaya.crud.databinding.ActivitySimpanBinding
import org.json.JSONObject


class simpan : AppCompatActivity() {
    private lateinit var binding: ActivitySimpanBinding
    var jk= "L"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val alamat = binding.etAlamat.text.toString()
            if (nama.isEmpty()){
                binding.etNama.error = "Nama Harus Di Isi"
                binding.etNama.requestFocus()

            }else if (alamat.isEmpty()){
                binding.etAlamat.error = "Alamat Harus Di Isi"
                binding.etAlamat.requestFocus()
            }else{
                ///simpandata
                AndroidNetworking.post("http://192.168.100.5/api/simpan.php")
                    .addBodyParameter("nama", nama)
                    .addBodyParameter("jenis_kelamin", jk)
                    .addBodyParameter("alamat", alamat)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            if (response.getInt("success") ==1){
                                Toast.makeText(this@simpan,response.getString("pesan"),Toast.LENGTH_SHORT).show()
                                finish()
                            }else{
                                Toast.makeText(this@simpan,response.getString("pesan"),Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onError(error: ANError) {
                            Toast.makeText(this@simpan,error.toString(),Toast.LENGTH_SHORT).show()

                        }
                    })

            }

        }

        binding.rgKelamin.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == binding.rbL.id) {
                jk = "L"
            }else{
                jk = "P"
            }
        }


    }
}