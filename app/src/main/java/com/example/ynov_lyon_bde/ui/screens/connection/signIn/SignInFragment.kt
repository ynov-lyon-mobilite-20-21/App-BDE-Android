package com.example.ynov_lyon_bde.ui.screens.connection.signIn

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.signIn.SignInViewModel
import kotlinx.android.synthetic.main.fragment_connectuser.*
import kotlinx.android.synthetic.main.fragment_connectuser.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_connectuser, container, false)
        val signInViewModel = SignInViewModel()

        //Show / Hide button
        view.showHideButton2.setOnClickListener {
            signInViewModel.showHideBehaviour(editTextPassword2, showHideButton2)
        }

        view.buttonCreateUser2.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        view.buttonConnect.setOnClickListener {
            // Take informations User
            val contentEditTextMail = editTextMail2.text.toString();
            val email = if(Patterns.EMAIL_ADDRESS.matcher(contentEditTextMail).matches()) {
                contentEditTextMail
            } else null
            val password = editTextPassword2.text.toString()
            var message: String? = null
            if (email != null) {
                GlobalScope.launch(Dispatchers.Main) {
                    val deferred = async(Dispatchers.IO) {
                        //call requests
                        message = context?.let { it1 -> signInViewModel.login(email,password, it1) }
                    }
                    deferred.await()
                    if (message.isNullOrEmpty()) {
                        activity?.finish()
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }else {
                Toast.makeText(context, "Formulaire mal renseigné", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
