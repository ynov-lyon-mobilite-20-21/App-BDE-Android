package com

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgs
import com.example.ynov_lyon_bde.R
import com.journeyapps.barcodescanner.BarcodeView
import kotlinx.android.synthetic.main.activity_scanner.*


//class Scanner : Activity() {
//    private val args:ScannerArgs by navArgs()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_scanner)
//
//        ActivityCompat.requestPermissions(this, arrayOf(CAMERA), 100)
//
//        val scanner = findViewById<BarcodeView>(R.id.scanner)
//        val name = findViewById<TextView>(R.id.name_person)
//        val ticket = findViewById<TextView>(R.id.ticket_person)
//        val nameEvent = findViewById<TextView>(R.id.title_event_qrcode)
//        val button = findViewById<Button>(R.id.back_button_carddescription)
//
//        button.setOnClickListener{ }
//
//        val event = args.Event
//        nameEvent.text = event.name
//
//        scanner.decodeSingle {
//            if (it.result.text != null){
//                name.text = it.result.text
//                ticket.text = it.result.text
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        scanner.resume()
//    }
//
//}

