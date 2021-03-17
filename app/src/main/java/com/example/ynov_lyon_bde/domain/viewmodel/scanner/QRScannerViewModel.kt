package com.example.ynov_lyon_bde.domain.viewmodel.scanner


import android.view.View
import com.example.ynov_lyon_bde.data.model.Event
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*


class QRScannerViewModel {

    fun printTitle (event: Event, view:View){
        view.title_event_qrcode.text = event.name
    }

    fun bottomGone(bottomNavigationBar:BottomNavigationView?){
        bottomNavigationBar?.visibility = View.GONE
    }

    fun printInfo(view: View,it:String){
        if (it != null){
            view.name_person.text = it
            view.ticket_person.text = it
        }
    }

    fun bottomVisible(bottomNavigationBar:BottomNavigationView?){
        bottomNavigationBar?.visibility = View.GONE
    }

}
