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

class QRScannerFragment : Fragment() {

    private val args: QRScannerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qr_scanner, container, false)
        val event = args.Event
        val bottomNavigationBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        val viewModel = QRScannerViewModel()
        viewModel.changeVisibilityBottomNavigationBar(bottomNavigationBar)

        view.back_button_carddescription.setOnClickListener{
            findNavController().popBackStack()
            viewModel.changeVisibilityBottomNavigationBar(bottomNavigationBar)
        }

        viewModel.printTitleEvent(event, view)
        view.scanner.decodeSingle {
            viewModel.printNameOfClient(view,it.result.text)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        scanner.resume()
    }
}
