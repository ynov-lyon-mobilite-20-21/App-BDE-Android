package com.example.ynov_lyon_bde.ui.screens.connection.signIn

import android.annotation.SuppressLint
import android.os.Bundle
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

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_connectuser, container, false)
        val signInViewModel = SignInViewModel()

        view.buttonCreateUserSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        //remove error when editText text changed
        signInViewModel.removeErrAfterTextChanged(view.editTextMailConnect, view.tiEditTextMailConnect)
        signInViewModel.removeErrAfterTextChanged(view.editTextPasswordConnect, view.tiEditTextPasswordConnect)

        view.buttonConnect.setOnClickListener {
            // get text input
            val contentEditTextMail = editTextMailConnect.text.toString();
            val mail = if(Patterns.EMAIL_ADDRESS.matcher(contentEditTextMail).matches()) {
                contentEditTextMail
            } else null
            val password = editTextPasswordConnect.text.toString()
            var message: String? = null

            //display error
            if(!signInViewModel.verifyErrorTextInputLayout(contentEditTextMail,
                    tiEditTextMailConnect, "email vide", true)){
                signInViewModel.verifyErrorTextInputLayout(mail, tiEditTextMailConnect,
                    "email non conforme", true)
            }
            signInViewModel.verifyErrorTextInputLayout(password, tiEditTextPasswordConnect,
                "mot de passe vide", false)

            //call requests
            if (mail != null && password.isNotEmpty()) {
                GlobalScope.launch(Dispatchers.Main) {
                    val deferred = async(Dispatchers.IO) {
                        message = context?.let { it1 -> signInViewModel.login(mail, password, it1) }
                    }
                    deferred.await()
                    if (message.isNullOrEmpty()) {
                        activity?.finish()
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return view
    }

}
