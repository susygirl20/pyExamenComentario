package com.everis.pyexamencomentario.ui.comentarios

import android.util.Log
import androidx.lifecycle.ViewModel
import com.everis.pyexamencomentario.EverisApp
import com.everis.pyexamencomentario.data.Constantes
import com.everis.pyexamencomentario.utils.formatChangeNote
import com.google.firebase.firestore.FirebaseFirestore

import java.time.LocalDateTime


class ComentariosBodyViewModel : ViewModel(){

    lateinit var firestore : FirebaseFirestore

    //Susan Andrea Mendoza Falcón 05/10/2020 - Función que publica en firestore los comentarios -
    fun publish(service : String, calificacion : Float, imagepath: String, descriptionpublish : String ){

        var fullname = EverisApp.prefs!!.getString(Constantes.KEY_FULLNAME)
        var uid = EverisApp.prefs!!.getString(Constantes.KEY_TOKEN)

            val comentario = hashMapOf(
                "calificacion" to calificacion,
                "descripcion" to descriptionpublish,
                "fecha" to LocalDateTime.now().formatChangeNote(),
                "idusuario" to uid,
                "image" to imagepath,
                "nombrecompleto" to fullname,
                "servicio" to service
            )

            firestore = FirebaseFirestore.getInstance()
            firestore.collection("comentario").add(comentario)
                .addOnSuccessListener { Log.d("TAG", "Documentov comentario registrado correctamente") }
                .addOnFailureListener { e -> Log.d("TAG", "Error escribiendo documento comentario", e) }
    }
}