package com.example.ynov_lyon_bde.ui.screens.connection.signIn

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.services.RedirectConnectService
import com.example.ynov_lyon_bde.domain.viewmodel.signIn.SignInViewModel
import com.example.ynov_lyon_bde.ui.screens.connection.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
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
        val navController = activity?.log_navigation_graph?.findNavController()
        if (navController != null) {
            navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("checkMail")
                ?.observe(viewLifecycleOwner) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Validez votre compte")
                    builder.setMessage("Un email a été envoyé pour valider votre compte")

                    builder.setPositiveButton("Ok") { dialog, which ->
                    }
                    builder.show()
                    //Toast.makeText(context, "Email de validation envoyé", Toast.LENGTH_LONG).show()
                }
        }
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
            if(!signInViewModel.emptyErrorTextInputLayout(contentEditTextMail,
                    tiEditTextMailConnect, "email vide", true)){
                signInViewModel.emptyErrorTextInputLayout(mail, tiEditTextMailConnect,
                    "email non conforme", true)
            }
            signInViewModel.emptyErrorTextInputLayout(password, tiEditTextPasswordConnect,
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
                        if(message == "Email incorrect"){
                            signInViewModel.setErrorTextInputLayout(tiEditTextMailConnect,
                                "Email incorrect", true)
                        }else{
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            signInViewModel.setErrorTextInputLayout(tiEditTextMailConnect,
                                "", true)
                            signInViewModel.setErrorTextInputLayout(tiEditTextPasswordConnect,
                                "", false)
                        }
                    }
                }
            }
        }
        return view
    }
}
