package com.example.ynov_lyon_bde.ui.screens.scanner

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ynov_lyon_bde.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_qr_scanner.*
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*

class QRScannerFragment : Fragment() {

    private val args: QRScannerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr_scanner, container, false)
        val event = args.Event
        val bottomNavigationBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigationBar?.visibility = View.GONE

        activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.CAMERA), 100) }
//        val scanner = findViewById<BarcodeView>(R.id.scanner)
//        val name = findViewById<TextView>(R.id.name_person)
//        val ticket = findViewById<TextView>(R.id.ticket_person)
//        val nameEvent = findViewById<TextView>(R.id.title_event_qrcode)
//        val button = findViewById<Button>(R.id.back_button_carddescription)

        view.back_button_carddescription.setOnClickListener{
            findNavController().popBackStack()
            bottomNavigationBar?.visibility = View.VISIBLE
        }


        view.title_event_qrcode.text = event.name
        view.scanner.decodeSingle {
            if (it.result.text != null){
                name_person.text = it.result.text
                ticket_person.text = it.result.text
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        scanner.resume()
    }
}
