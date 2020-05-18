package com.example.ladm_u4_tarea1_bluetoothnfc_jonathanlopez

import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_n_f_c.*

class NFC : AppCompatActivity() {
    lateinit var nfcAdapter : NfcAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n_f_c)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        btnCompatibilidad.setOnClickListener {
            var res = ""
            if (nfcAdapter!=null){
                res="El dispositivo es compatible con NFC"
            }else{
                res="El dispositivo no es compatible con NFC"
            }
            AlertDialog.Builder(this).setMessage(res).show()
        }
        btn_Status.setOnClickListener {
            var res = ""
            if (nfcAdapter.isEnabled){
                res="NFC activado"
            }
            if(!nfcAdapter.isEnabled){
                res="NFC desactivado"
            }
            AlertDialog.Builder(this).setMessage(res).show()
        }
    }
}
