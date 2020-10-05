package com.everis.pyexamencomentario.ui.comentarios

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everis.pyexamencomentario.R
import com.everis.pyexamencomentario.data.Comentario

class ComentariosViewHolder (inflater: LayoutInflater, parent:ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_review, parent, false)){

    private var imageView: ImageView? =null
    private var textService: TextView? = null
    private var textDate: TextView? = null
    private var ratingBar: RatingBar? = null
    private var textDescription: TextView? = null
    private var textClient: TextView? = null


    init {
        imageView = itemView.findViewById(R.id.imgComentario)
        textService = itemView.findViewById(R.id.textService)
        textDate = itemView.findViewById(R.id.textDate)
        ratingBar = itemView.findViewById(R.id.ratingbar)
        textDescription = itemView.findViewById(R.id.textDescription)
        textClient = itemView.findViewById(R.id.textClient)
    }

    fun bind(comentario: Comentario, holder: ComentariosViewHolder) {

        textService?.text = comentario.servicio
        textDate?.text = comentario.fecha
        ratingBar?.rating = comentario.calificacion
        textDescription?.text = comentario.descripcion
        textClient?.text = comentario.nombrecompleto

        imageView?.let {
            Glide.with(it)
                .load(comentario.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }

}