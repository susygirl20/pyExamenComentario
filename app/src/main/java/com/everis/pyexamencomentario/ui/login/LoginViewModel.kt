package com.everis.pyexamencomentario.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everis.pyexamencomentario.data.Usuario
import com.everis.pyexamencomentario.utils.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginViewModel : ViewModel() {

    //Variables de firestore y firebase
    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore

    //Mutables
    val userServiceResponse = MutableLiveData<Boolean>()
    val loginDataResponse  = MutableLiveData<Usuario>()
    val userLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        if (email.isValidEmail()) {
            loginFireBase(email, password)
        } else {
            userLoadError.value = true
        }
    }

    //Función que realiza el login con firebase
    fun loginFireBase(email: String, password: String) {

        loading.value = true

        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() {
                if (it.isSuccessful) {
                    Log.v("TAG", "isSuccessful")
                    userServiceResponse.value = true
                } else {
                    Log.v("TAG", "error")
                    userServiceResponse.value = false
                }
            }

    }

    //Susan Andrea Mendoza Falcón 05/10/2020 - Función que obtiene el uid (user id) de firebase -
    fun loadCurrentUser() {
        var user = auth.currentUser;
        var uid: String
        lateinit var vusuario : Usuario

        if (user != null) {
            uid = user.uid;
            firestore = FirebaseFirestore.getInstance()
            firestore.collection("usuario")
                .whereEqualTo("idusuario", uid)
                .get()
                .addOnSuccessListener {

                    for (document in it) {
                        val myObj = document.toObject(Usuario::class.java)
                        vusuario = myObj
                        Log.d("EVR_APP", "User create Successfull")
                    }
                    loginDataResponse.value = vusuario
                }
                .addOnFailureListener {
                    Log.d("EVR_APP", "Error")
                }
        }
    }
}