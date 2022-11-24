package com.fannybrawijaya.crud

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.fannybrawijaya.crud.databinding.CostumTampilBinding

class adapter(private val context: Context, private val results: ArrayList<model>) : RecyclerView.Adapter<adapter.MyHolder>() {
    private var Items = ArrayList<model>()

    init {
        this.Items = results

    }

    inner class MyHolder(val binding: CostumTampilBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return  MyHolder(
                CostumTampilBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val result = Items[position]
        with(holder){
            binding.tvNama.text = result.nama
            binding.tvAlamat.text = result.alamat
            if (result.jenis_kelamin == "L"){
                binding.tvJk.text == "Laki-Laki"
            }else{
                binding.tvJk.text == "Perempuan"
            }
            binding.root.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Pilih Pengaturan")
                val pilihan = arrayOf("Edit", "Delete", "Cancel")
                builder.setItems(pilihan) { dialog, which ->
                    when (which) {
                        0 -> {
                            val a = Intent(context, edit::class.java)
                            a.putExtra("id", result.id_crud)
                            context.startActivity(a)
                        }

                        1 -> {

                        }

                        2 -> {
                            dialog.dismiss()

                        }
                    }
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }


}