package com.example.ladm_u4_tarea1_bluetoothnfc_jonathanlopez

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_blue_tooth.*

class BlueTooth : AppCompatActivity() {
    private val REQUEST_CODE_ENABLE_BLUETOOTH: Int = 1
    private val REQUEST_CODE_DISCOVERABLE_BLUETOOTH: Int = 2

    //BluetoothAdapter
    lateinit var blueAdapter: BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue_tooth)

        //Inicializacion del bluetooth adapter
        blueAdapter = BluetoothAdapter.getDefaultAdapter()

        //Cambiar icono dependiendo de del estatus cuando se incia la aplicación
        if (blueAdapter.isEnabled) {
            imgStatus.setImageResource(R.drawable.b_on)
        }
        if (!blueAdapter.isEnabled) {
            imgStatus.setImageResource(R.drawable.b_off)
        }


        //Checar si esta encendido o apagado
        if (blueAdapter == null) {
            status.setText("Bluetooth no está disponible en este dipositivo")
        } else {
            status.setText("Bluetooth está disponible en este dispositivo")
        }

        //Boton Encender bluetooth
        btnOn.setOnClickListener {
            if (blueAdapter.isEnabled) {
                //Si ya está activado
                Toast.makeText(this, "Bluetooth ya se encuentra activado", Toast.LENGTH_LONG).show()
            } else {
                //Encender Bluetooth
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH)
            }
        }
        //Boton apagar bluetooth
        btnOff.setOnClickListener {
            if (!blueAdapter.isEnabled) {
                //Si ya está desactivado
                Toast.makeText(this, "Bluetooth ya se encuentra desactivado", Toast.LENGTH_LONG)
                    .show()
            } else {
                //Encender Bluetooth
                blueAdapter.disable()
                imgStatus.setImageResource(R.drawable.b_off)
                Toast.makeText(this, "Se ha desactivado el bluetooth", Toast.LENGTH_LONG).show()

            }
        }
        //Boton ocultar bluetooth
        btnOcultar.setOnClickListener {
            if (!blueAdapter.isDiscovering) {
                Toast.makeText(this, "Haciendo visible el dispositivo...", Toast.LENGTH_LONG).show()
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BLUETOOTH)

            }
        }
        //Boton dispositivos emparejados
        btnEmparejar.setOnClickListener {
            txtDevices.setText("")
            if (blueAdapter.isEnabled) {
                //lista de sipositivos
                val devices = blueAdapter.bondedDevices
                for (device in devices) {
                    val name = device.name
                    val dirección = device.address

                    txtDevices.append(" \n Dispositivo: ${name} , ${dirección} \n---------------\n")
                }
            } else {
                Toast.makeText(this, "Primero active el bluetooth", Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_ENABLE_BLUETOOTH ->
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Se ha activado el Bluetooth", Toast.LENGTH_LONG).show()
                    imgStatus.setImageResource(R.drawable.b_on)

                } else {
                    //Si el usuario deniaga la confirmación del cuadro de dialogo
                    Toast.makeText(this, "No se pudo activar el bluetooth", Toast.LENGTH_LONG).show()
                    imgStatus.setImageResource(R.drawable.b_off)
                }


        }
        if (blueAdapter.isEnabled) {
            imgStatus.setImageResource(R.drawable.b_on)
        }
        if (!blueAdapter.isEnabled) {
            imgStatus.setImageResource(R.drawable.b_off)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
