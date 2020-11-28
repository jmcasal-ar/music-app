package com.example.musicapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.providers.montoDonacionProvider

class DonateActivity : AppCompatActivity() {
    private lateinit var spMonto: Spinner
    private lateinit var rgMetodo: RadioGroup
    private lateinit var btnDonar: Button
    private lateinit var txtResultado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        setupUI()
    }

    private fun setupUI() {
        spMonto = findViewById(R.id.spMonto)
        rgMetodo = findViewById(R.id.rgMetodo)
        btnDonar = findViewById(R.id.btnDonar)
        txtResultado = findViewById(R.id.txtResultado)


        btnDonar.setOnClickListener { realizarDonacion() }


        setupSpinner()
    }



    private fun setupSpinner(){
        val pesos = montoDonacionProvider.provideMonto()
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            pesos)
        spMonto.adapter = adapter
    }



    private fun realizarDonacion() {
        val monto = spMonto.selectedItem.toString()
        var urlMP = ""
        val selectedOption: Int = rgMetodo.checkedRadioButtonId

        if(monto.isNotEmpty()){

            if (selectedOption == R.id.rbTransfer){
                val builder = AlertDialog.Builder(this)
                builder
                    .setTitle("Transferencias")
                    .setMessage("Por favor, donar el monto de ${monto} pesos al CBU: 00088408008466")
                    .setPositiveButton("Aceptar", { _, _ ->
                        MostrarMensaje("Muchas gracias!")
                    })
                    .setCancelable(false)
                    .show()
            } else  {
                when(monto){
                    "5" -> urlMP = "https://mpago.la/1mf8hK3"
                    "10" -> urlMP = "https://mpago.la/2PA6oE1"
                    "50" -> urlMP = "https://mpago.la/16r1MYS"

                }
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlMP)))

            }

        } else{
            MostrarMensaje("Completar todos los campos")
        }
    }

    private fun MostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}