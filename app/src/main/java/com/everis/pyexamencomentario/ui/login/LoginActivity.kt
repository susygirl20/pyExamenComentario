package com.everis.pyexamencomentario.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.everis.pyexamencomentario.R
import com.everis.pyexamencomentario.data.Constantes
import com.everis.pyexamencomentario.data.Constantes.PREF_FILENAME
import com.everis.pyexamencomentario.ui.comentarios.ComentariosActivity
import com.everis.pyexamencomentario.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtEmail
import kotlinx.android.synthetic.main.activity_login.edtPass

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        //Evento que se dispara para loguear a un usuario
        btnSignIn.setOnClickListener{
            viewModel.login(edtEmail.text.toString(), edtPass.text.toString())
        }

        //Evento que se dispara para el registro del usuario
        textRegister.setOnClickListener{
            goRegister()
        }

        observerViewModel()
    }

    //Funcioón que contiene los distintos Observers
    fun observerViewModel(){
        viewModel.userLoadError.observe(this, Observer {

            if (it) {
                //ERROR
                Toast.makeText(this, "Verifique sus credenciales", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.userServiceResponse.observe(this, Observer {
            if (it) {

                viewModel.loadCurrentUser()

            } else {
                Toast.makeText(this, "Verifique sus credenciales", Toast.LENGTH_LONG).show()
            }
        })
        //Llena los sharedpreferences con los valores del usuario
        viewModel.loginDataResponse.observe(this, Observer {
            if(it!= null){
                val shared: SharedPreferences = getSharedPreferences(
                    PREF_FILENAME,
                    Context.MODE_PRIVATE
                )
                val editor: SharedPreferences.Editor = shared.edit()

                editor.putString(Constantes.KEY_NAME, it.nombres)
                editor.putString(Constantes.KEY_SURNAME, it.apellidos)
                editor.putString(Constantes.KEY_FULLNAME,it.nombres +" " + it.apellidos)
                editor.putString(Constantes.KEY_TOKEN, it.idusuario)
                editor.putBoolean(Constantes.KEY_LOGIN,true)

                editor.commit()
            }
            goHome()
        })

        viewModel.loading.observe(this, Observer {
            if(it){
                progressBarLogin.visibility = View.VISIBLE
            } else{
                progressBarLogin.visibility = View.GONE
            }

        })


    }

    private fun goHome() {
        startActivity(Intent(this, ComentariosActivity::class.java))
        finish()
    }

    private fun goRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}