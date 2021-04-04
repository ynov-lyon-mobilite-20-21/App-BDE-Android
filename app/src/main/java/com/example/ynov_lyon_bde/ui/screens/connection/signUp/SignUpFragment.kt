package com.example.ynov_lyon_bde.ui.screens.connection.signUp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.domain.viewmodel.signUp.SignUpViewModel
import kotlinx.android.synthetic.main.activity_login.*
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
            if(!signUpViewModel.emptyErrorTextInputLayout(contentEditTextMail,
                    tiEditTextMailCreate, "email vide", true)){
                signUpViewModel.emptyErrorTextInputLayout(mail, tiEditTextMailCreate,
                    "email non conforme", true)
            }
            signUpViewModel.emptyErrorTextInputLayout(firstName, tiEditTextFirstName,
                "prénom vide", false)
            signUpViewModel.emptyErrorTextInputLayout(lastName, tiEditTextLastName,
                "nom vide", false)
            signUpViewModel.emptyErrorTextInputLayout(password, tiEditTextPasswordCreate,
                "mot de passe vide", false)
            signUpViewModel.emptyErrorTextInputLayout(promotion, tiSpinnerPromotion,
                "promotion vide", false)
            signUpViewModel.emptyErrorTextInputLayout(formation, tiSpinnerFormation,
                "formation vide", false)

            //call requests
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && !mail.isNullOrEmpty() && password.isNotEmpty() && promotion.isNotEmpty() && formation.isNotEmpty()) {
                var message: String? = null
                GlobalScope.launch(Dispatchers.Main) {
                    val deferred = async(Dispatchers.IO) {
                        //call requests
                        message = context?.let { it1 -> signUpViewModel.create(firstName,lastName,mail,password,promotion,formation,it1) }
                    }
                    deferred.await()
                    if (message.isNullOrEmpty()) {
                        val navController = activity?.log_navigation_graph?.findNavController()
                        if (navController != null) {
                            navController.previousBackStackEntry?.savedStateHandle?.set("checkMail", "")
                        }
                        navController?.popBackStack()
                        //activity?.base_nav_host?.findNavController()?.popBackStack()
                        //activity?.finish()
                    } else {
                        if(message!!.contains("non renseigné", ignoreCase = true)){
                            when(message){
                                "Email non renseigné" -> signUpViewModel.setErrorTextInputLayout(tiEditTextMailCreate,
                                    "Email non renseigné", true)
                                "Mot de passe non renseigné" -> signUpViewModel.setErrorTextInputLayout(tiEditTextPasswordCreate,
                                    "Mot de passe non renseigné", true)
                                "Prénom non renseigné" -> signUpViewModel.setErrorTextInputLayout(tiEditTextFirstName,
                                    "Prénom non renseigné", true)
                                "Nom non renseigné" -> signUpViewModel.setErrorTextInputLayout(tiEditTextFirstName,
                                    "Prénom non renseigné", true)
                                "Promotion non renseignée" ->  signUpViewModel.setErrorTextInputLayout(tiSpinnerPromotion,
                                    "Promotion non renseignée", true)
                                "Formation non renseignée" -> signUpViewModel.setErrorTextInputLayout(tiSpinnerFormation,
                                    "Formation non renseignée", true)
                            }
                        } else{
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            signUpViewModel.setErrorTextInputLayout(tiEditTextMailCreate,
                                "", true)
                            signUpViewModel.setErrorTextInputLayout(tiEditTextPasswordCreate,
                                "", false)
                            signUpViewModel.setErrorTextInputLayout(tiEditTextFirstName,
                                "", false)
                            signUpViewModel.setErrorTextInputLayout(tiEditTextLastName,
                                "", false)
                            signUpViewModel.setErrorTextInputLayout(tiSpinnerPromotion,
                                "", false)
                            signUpViewModel.setErrorTextInputLayout(tiSpinnerFormation,
                                "", false)
                        }
                    }
                }
            }
        }
        return view

    }
}




