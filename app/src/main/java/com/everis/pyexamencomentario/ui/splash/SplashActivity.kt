package com.everis.pyexamencomentario.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.everis.pyexamencomentario.EverisApp
import com.everis.pyexamencomentario.R
import com.everis.pyexamencomentario.data.Constantes
import com.everis.pyexamencomentario.ui.comentarios.ComentariosActivity
import com.everis.pyexamencomentario.ui.login.LoginActivity

class SplashActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Susan Andrea Mendoza Falcón 05/10/2020 - Verifica que si hay usuarios logueados -
        Handler().postDelayed({
            if(EverisApp.prefs!!.getBoolean(Constantes.KEY_LOGIN)){
                startActivity(Intent(this, ComentariosActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }

        },6000)
    }
}