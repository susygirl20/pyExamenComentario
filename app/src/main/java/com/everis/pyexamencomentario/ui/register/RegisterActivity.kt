package com.everis.pyexamencomentario.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.everis.pyexamencomentario.R
import com.everis.pyexamencomentario.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar_back.*

class RegisterActivity  : AppCompatActivity(){

    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        //Función que dispara el registro del usuario
        btnRegister.setOnClickListener {
            viewModel.register(edtEmail.text.toString(), edtPass.text.toString())


            Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_LONG).show()
        }

        //Función que dispara la pantalla login
        imgBack.setOnClickListener{
            goHome()
        }
        observerViewModel()
    }

    //Observers
    fun observerViewModel(){


        viewModel.userRegisterError.observe(this,   Observer {

            if(it){
                //ERROR
                Toast.makeText(this, "Verifique sus credenciales", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.userServiceResponse.observe(this, Observer {
            if(it){
                viewModel.createCliente(edtNames.text.toString(), edtSurnames.text.toString())
                goHome()
            }else {
                Toast.makeText(this, "Verifique sus credenciales", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun goHome(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}