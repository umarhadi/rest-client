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
        holder.view.nama.text = arrayList?.get(position)?.nama
        holder.view.alamat.text = arrayList?.get(position)?.alamat
        holder.view.tgl_lahir.text = arrayList?.get(position)?.tgl_lahir
        holder.view.telp.text = arrayList?.get(position)?.telp
        holder.view.email.text = arrayList?.get(position)?.email
        holder.view.idUser.text = arrayList?.get(position)?.idUser.toString()
    }

    override fun getItemCount(): Int = arrayList!!.size
}