package com.umar.restclient

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class RVAdapterPenduduk (private val context: Context, private val arrayList: ArrayList<Penduduk>):
    RecyclerView.Adapter<RVAdapterPenduduk.Holder>()
{
    class Holder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.idUser.text = "ID User: "+arrayList?.get(position)?.idUser.toString()
        holder.view.nama.text = arrayList?.get(position)?.nama
        holder.view.alamat.text = "Alamat: "+arrayList?.get(position)?.alamat
        holder.view.tgl_lahir.text = "Tanggal Lahir: "+arrayList?.get(position)?.tgl_lahir
        holder.view.telp.text = "Telepon: "+arrayList?.get(position)?.telp
        holder.view.email.text = "Email: "+arrayList?.get(position)?.email

    }

    override fun getItemCount(): Int = arrayList!!.size
}