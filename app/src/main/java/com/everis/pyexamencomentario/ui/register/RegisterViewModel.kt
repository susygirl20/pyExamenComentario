package com.everis.pyexamencomentario.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everis.pyexamencomentario.utils.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {

    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore


    //Mutables
    val userRegisterError = MutableLiveData<Boolean>()
    val userServiceResponse = MutableLiveData<Boolean>()

    //Función register
    fun register(email: String, password: String): Boolean {

        if (email.isValidEmail()) {

            registerFirebase(email, password)
            return true

        } else {
            userRegisterError.value = true
            return false
        }

    }

    //Función que realiza el registro en firebase
    fun registerFirebase(email: String, password: String) {

        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.v("TAG", "isSuccessful")
                    userServiceResponse.value = true

                } else {
                    Log.v("TAG", "error")
                    userRegisterError.value = false
                }
            }
}

    //Susan Andrea Mendoza Falcón 05/10/2020 - Función de creación de usuario

    fun createCliente(nombres: String, apellidos: String) {
        var user = auth.currentUser;
        var name: String?;
        var bemail: String?;
        var uid: String;

        if (user != null) {
            name = user!!.displayName;
            bemail = user!!.email;
            uid = user!!.uid;  // The user's ID, unique to the Firebase project. Do NOT use
            // this value to authenticate with your backend server, if
            // you have one. Use User.getToken() instead.
            val usuario = hashMapOf(
                "idusuario" to uid,
                "nombres" to nombres,
                "apellidos" to apellidos
            )

            firestore = FirebaseFirestore.getInstance()
            firestore.collection("usuario").add(usuario)
                .addOnSuccessListener { Log.d("TAG", "Usuario registrado corectamente!") }
                .addOnFailureListener { e -> Log.d("TAG", "Error", e) }

        }
    }
}