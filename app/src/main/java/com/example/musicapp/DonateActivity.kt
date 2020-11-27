package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.musicapp.providers.montoDonacionProvider
import kotlinx.android.synthetic.main.activity_donate.*

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
        val selectedOption: Int = rgMetodo.checkedRadioButtonId

        if(monto.isNotEmpty()){

            if (selectedOption == R.id.rbTransfer){
                mostrarCheckboxImpuesto()
            } else  {
                ocultarCheckBoxImpuesto()
            }

            val resultado = when (obtenerRadioButtonSeleccionado()) {
                R.id.rbBlue -> convertirABlue(pesos.toDouble())
                R.id.rbBlue -> convertirABolsa(pesos.toDouble())
                R.id.rbBlue -> convertirANacion(pesos.toDouble())
                else -> convertirABlue(pesos.toDouble())
            }
            txtResultado.text = resultado.toString()
        } else{
            MostrarMensaje("Completar todos los campos")
        }
    }

    private fun MostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}