package com.everis.pyexamencomentario.ui.comentarios

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everis.pyexamencomentario.data.Comentario
import com.google.firebase.firestore.FirebaseFirestore

class ComentariosViewModel : ViewModel() {

    lateinit var  firestore: FirebaseFirestore
    val comentarioList = MutableLiveData<List<Comentario>>()
    val userServieResponse = MutableLiveData<Boolean>()

    fun loadData(){

        var listComentario = arrayListOf<Comentario>()

        firestore = FirebaseFirestore.getInstance()
        firestore.collection("comentario")
            .get() // Petición de solicitud de data
            .addOnSuccessListener {

                for(document in it){

                    val myObj = document.toObject(Comentario::class.java)
                    listComentario.add(myObj)
                }

                comentarioList.value = listComentario
            }
            .addOnFailureListener{
                Log.v("ERROR_TAG", "error")
                userServieResponse.value = false
            }
    }


}