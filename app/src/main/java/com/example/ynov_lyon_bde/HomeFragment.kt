package com.example.ynov_lyon_bde

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.reflect.Array

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }
}



// Inflate the layout for this fragment
//        val scrollView = ScrollView(this)
//
//        val layoutParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT)
//        scrollView.layoutParams = layoutParams
//
//        val linearLayout = LinearLayout(this)
//        val linearParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT)
//        linearLayout.orientation = LinearLayout.VERTICAL
//        linearLayout.layoutParams = linearParams
//
//        scrollView.addView(linearLayout)
//
//        val imageView1 = ImageView(this)
//        val params1 =
//            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//        //setting margin
//        params1.setMargins(0, 30, 0, 30)
//        //aligning the layout to center of the screen
//        params1.gravity = Gravity.CENTER
//        imageView1.setLayoutParams(params1)
//        //setting our own downloaded/custom image to the imageView
//        imageView1.setImageResource(R.drawable.logo)
//        linearLayout.addView(imageView1)
//
//        val imageView2 = ImageView(this)
//        val params2 =
//            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//        params2.setMargins(0, 0, 0, 30)
//        params2.gravity = Gravity.CENTER
//        imageView2.setLayoutParams(params2)
//        imageView2.setImageResource(R.drawable.logo)
//        linearLayout.addView(imageView2)
