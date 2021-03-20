package com.example.ynov_lyon_bde.domain.viewmodel.scanner


import android.view.View
import com.example.ynov_lyon_bde.data.model.Event
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*


class QRScannerViewModel {

    fun printTitleEvent (event: Event, view:View){
        view.title_event_qrcode.text = event.name
    }

    fun printNameOfClient(view: View,it:String){
        view.name_person.text = it
        view.ticket_person.text = it
    }

    fun changeVisibilityBottomNavigationBar(bottomNavigationBar:BottomNavigationView?){
        if (bottomNavigationBar?.visibility == 0){
            bottomNavigationBar?.visibility = View.GONE
        }else{
            bottomNavigationBar?.visibility = View.VISIBLE
        }
    }
}
