package com.everis.pyexamencomentario.ui.comentarios

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.everis.pyexamencomentario.EverisApp
import com.everis.pyexamencomentario.R
import com.everis.pyexamencomentario.data.Constantes
import kotlinx.android.synthetic.main.body_review.*
import kotlinx.android.synthetic.main.item_review.*
import java.io.IOException

class ComentariosBodyActivity : AppCompatActivity (){

    lateinit var viewModel: ComentariosBodyViewModel

    private var filePath: Uri? = null
    val PICK_IMAGE_REQUEST = 71
    var imageView : ImageView? = null

    //Susan Andrea Mendoza Falcón 05/10/2020

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.body_review)
        viewModel= ViewModelProviders.of(this).get(ComentariosBodyViewModel::class.java)
        imageView = findViewById(R.id.imgFotoBody)

        //Se almacena en el textView textClient el nombre del usuario
        textClient?.text = EverisApp.prefs!!.getString(Constantes.KEY_FULLNAME)

        //Susan Andrea Mendoza Falcón 05/10/2020 - Evento de selección de imagen -
        btnSelectImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action=Intent.ACTION_OPEN_DOCUMENT
            startActivityForResult(Intent.createChooser(intent,"Seleccione una imagen"), PICK_IMAGE_REQUEST)
        }

        //Evento de publicación de de comentario
        btnPublish.setOnClickListener {
            viewModel.publish(edtService.text.toString(), ratingbarbody.rating,EverisApp.prefs!!.getString(Constantes.KEY_IMAGE), edtDescription.text.toString() )

            Toast.makeText(this, "Comentario publicado correctamente", Toast.LENGTH_LONG).show()
            goListaComentario()
        }

        btnCancel.setOnClickListener{
            goListaComentario()
        }

    }

    //Obtiene el path de la imagen y la almacena en sharepreference KEY_IMAGE y la setea a al imageView
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK &&
                data !== null && data.data != null){
            filePath = data.data;

            val shared: SharedPreferences = getSharedPreferences(
                Constantes.PREF_FILENAME,
                Context.MODE_PRIVATE
            )
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putString(Constantes.KEY_IMAGE, "")
            editor.commit()
            editor.putString(Constantes.KEY_IMAGE, filePath.toString())
            editor.commit()

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView?.setImageBitmap(bitmap)
            }catch(e:IOException){

                e.printStackTrace()
            }
        }
    }

    //Función de retorno a la actividad principal
    private fun goListaComentario() {
        startActivity(Intent(this, ComentariosActivity::class.java))
        finish()
    }
}