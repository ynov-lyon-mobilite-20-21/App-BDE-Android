package com.example.ynov_lyon_bde.ui.screens.connection.signUp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.signUp.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_connectuser.*
import kotlinx.android.synthetic.main.fragment_connectuser.view.*
import kotlinx.android.synthetic.main.fragment_createuser.*
import kotlinx.android.synthetic.main.fragment_createuser.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SignUpFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_createuser, container, false)

        val signUpViewModel = SignUpViewModel()

        //List for spinner
        val promotion = arrayOf("Bachelor 1", "Bachelor 2", "Bachelor 3", "Mastère 1", "Mastère 2")
        val formation = arrayOf("Animation 3D", "Audiovisuel", "Création & Design",
            "Marketing Communication", "Informatique")

        //Adapter for spinner
        val adapterFormation = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, formation)
        val adapterPromotion = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, promotion)
        view.spinnerFormation.setAdapter(adapterFormation)
        view.spinnerPromotion.setAdapter(adapterPromotion)

        //remove error when editText text changed
        signUpViewModel.removeErrAfterTextChanged(view.editTextMailCreate, view.tiEditTextMailCreate)
        signUpViewModel.removeErrAfterTextChanged(view.editTextFirstName, view.tiEditTextFirstName)
        signUpViewModel.removeErrAfterTextChanged(view.editTextLastName, view.tiEditTextLastName)
        signUpViewModel.removeErrAfterTextChanged(view.editTextPasswordCreate, view.tiEditTextPasswordCreate)
        signUpViewModel.removeErrAfterTextChanged(view.spinnerPromotion, view.tiSpinnerPromotion)
        signUpViewModel.removeErrAfterTextChanged(view.spinnerFormation, view.tiSpinnerFormation)

        view.buttonCreateUserSignUp.setOnClickListener {
            // get text input
            val contentEditTextMail = editTextMailCreate.text.toString()
            val mail = if(Patterns.EMAIL_ADDRESS.matcher(contentEditTextMail).matches()) {
                contentEditTextMail
            } else null

            val firstName = editTextFirstName.text.toString()
            val lastName = editTextLastName.text.toString()
            val password = editTextPasswordCreate.text.toString()
            val promotion = spinnerPromotion.text.toString()
            val formation = spinnerFormation.text.toString()

            //display error
            if(!signUpViewModel.verifyErrorTextInputLayout(contentEditTextMail,
                    tiEditTextMailCreate, "email vide", true)){
                signUpViewModel.verifyErrorTextInputLayout(mail, tiEditTextMailCreate,
                    "email non conforme", true)
            }
            signUpViewModel.verifyErrorTextInputLayout(firstName, tiEditTextFirstName,
                "prénom vide", false)
            signUpViewModel.verifyErrorTextInputLayout(lastName, tiEditTextLastName,
                "nom vide", false)
            signUpViewModel.verifyErrorTextInputLayout(password, tiEditTextPasswordCreate,
                "mot de passe vide", false)
            signUpViewModel.verifyErrorTextInputLayout(promotion, tiSpinnerPromotion,
                "promotion vide", false)
            signUpViewModel.verifyErrorTextInputLayout(formation, tiSpinnerFormation,
                "formation vide", false)

            //call requests
            if (firstName.isEmpty() && lastName.isEmpty() && mail != null && password != "" && promotion.isEmpty() && formation.isEmpty()) {
                var message: String? = null
                GlobalScope.launch(Dispatchers.Main) {
                    val deferred = async(Dispatchers.IO) {
                        //call requests
                        message = context?.let { it1 -> signUpViewModel.create(firstName,lastName,mail,password,promotion,formation,it1) }
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




