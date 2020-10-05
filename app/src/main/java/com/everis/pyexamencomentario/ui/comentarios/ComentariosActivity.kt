package com.everis.pyexamencomentario.ui.comentarios

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.pyexamencomentario.R
import com.everis.pyexamencomentario.data.Constantes
import com.everis.pyexamencomentario.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.toolbar_logout.*


class ComentariosActivity : AppCompatActivity(){

    lateinit var viewModel: ComentariosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        viewModel = ViewModelProviders.of(this).get(ComentariosViewModel::class.java)
        viewModel.loadData()


        //Evento de logout de usuario
        imgLogout.setOnClickListener {
            val shared: SharedPreferences = getSharedPreferences(
                Constantes.PREF_FILENAME,
                Context.MODE_PRIVATE
            )
            val editor: SharedPreferences.Editor = shared.edit()

            //Reinicia los sharedpreferences
            editor.putString(Constantes.KEY_EMAIL, "")
            editor.putString(Constantes.KEY_NAME, "")
            editor.putString(Constantes.KEY_SURNAME, "")
            editor.putString(Constantes.KEY_EMAIL, "")
            editor.putString(Constantes.KEY_FULLNAME, "")
            editor.putString(Constantes.KEY_TOKEN, "")
            editor.putBoolean(Constantes.KEY_LOGIN, false)

            editor.commit()
            goLogin()
        }

        //Evento de agregar un nuevo comentario (invoca a ComentariosBodyActivity)
        floatingAdd.setOnClickListener{
            startActivity(Intent(this, ComentariosBodyActivity::class.java))
            finish()
        }
        observableViewModel()
    }

     fun observableViewModel(){

         //Llena el recycler de comentarios
        viewModel.comentarioList.observe(this, Observer {
            if (it.isNotEmpty()) {
                recyclerReview.apply {
                    layoutManager = LinearLayoutManager(this@ComentariosActivity)
                    adapter = ComentariosAdapter(it)
                }
            }

        })
    }

    //Función que redirige al Splash
    private fun goLogin() {
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }
}