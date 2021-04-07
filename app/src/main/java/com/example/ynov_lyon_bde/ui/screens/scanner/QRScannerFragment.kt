package com.example.ynov_lyon_bde.ui.screens.scanner

import android.Manifest
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.scanner.QRScannerViewModel
import com.example.ynov_lyon_bde.domain.viewmodel.scanner.TicketViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_qr_scanner.*
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class QRScannerFragment : Fragment() {

    private val args: QRScannerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr_scanner, container, false)
        val event = args.Event
        val bottomNavigationBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.CAMERA), 100)}

        val viewModel = QRScannerViewModel()
        bottomNavigationBar?.visibility = View.GONE

        view.back_button_carddescription.setOnClickListener{findNavController().popBackStack()}

        viewModel.printTitleEvent(event, view)
        view.title_event_qrcode.text = event.name

        view.scanner.decodeContinuous {
            GlobalScope.launch {
                if (it.result.text.isNotEmpty()){
                    val ticketViewModel = TicketViewModel()
                    var identityClient = context?.let { it1 -> ticketViewModel.validationTicket(it.result.text, it1)}
                    activity?.runOnUiThread {
                        viewModel.printNameOfClient(view, identityClient)
                        viewModel.changeColorBorderCard(identityClient,view, requireActivity(),context)
                    }
                }
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        scanner.resume()
    }
}
