package com.everis.pyexamencomentario.ui.comentarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.everis.pyexamencomentario.data.Comentario

class ComentariosAdapter (private val list: List<Comentario>)
    : RecyclerView.Adapter<ComentariosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentariosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ComentariosViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ComentariosViewHolder, position: Int) {
        val comentario: Comentario = list[position]
        holder.bind(comentario, holder)
    }

    override fun getItemCount(): Int = list.size
}