package com.example.ladm_u4_tarea1_bluetoothnfc_jonathanlopez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         btnB.setOnClickListener{
             var bluetoothActivity = Intent(this, BlueTooth::class.java)
             startActivity(bluetoothActivity)
         }
        btnN.setOnClickListener {
            var nfcActivity = Intent(this, NFC::class.java)
            startActivity(nfcActivity)
        }
    }
}
