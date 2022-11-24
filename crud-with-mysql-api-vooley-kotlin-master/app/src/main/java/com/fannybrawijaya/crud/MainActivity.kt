package com.fannybrawijaya.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.fannybrawijaya.crud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btInput.setOnClickListener{
            val a = Intent(this@MainActivity,simpan::class.java)
            startActivity(a)
        }
        binding.byTampil.setOnClickListener{
            val a = Intent(this@MainActivity,tampil::class.java)
            startActivity(a)
        }
    }
}