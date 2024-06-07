package com.jmfragallo.ceep_android.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jmfragallo.ceep_android.R
import com.jmfragallo.ceep_android.model.Nota


class ListaNotaAdapter(private val context: Context, private val todasNotas: List<Nota>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  todasNotas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var nota: Nota = todasNotas.get(position)
        val titulo: TextView = holder.itemView.findViewById(R.id.item_nota_titulo)
        titulo.setText(nota.titulo)
        val descricao: TextView = holder.itemView.findViewById(R.id.item_nota_descricao)
        descricao.setText(nota.descricao)

    }

}


internal class NotaViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
