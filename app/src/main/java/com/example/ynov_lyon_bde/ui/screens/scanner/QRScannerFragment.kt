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
import com.example.ynov_lyon_bde.domain.viewmodel.scanner.QRScannerViewModel
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
        view.title_event_qrcode.text = event.name

        view.scanner.decodeContinuous {
            GlobalScope.launch {
                if (it.result.text.isNotEmpty()) {
                    val userId = context?.let { context -> viewModel.validationTicket(it.result.text, context)}
                    activity?.runOnUiThread {
                        viewModel.printNameOfClient(view, userId)
                        viewModel.changeColorBorderCard(userId, view, requireActivity(), context)
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
