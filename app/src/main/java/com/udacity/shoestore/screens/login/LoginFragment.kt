package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.screens.shoe_list.SharedViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginviewmodel = viewModel //binding the variable layout and the loginViewModel
        binding.lifecycleOwner = this


        // this observer is activated if the user attempts to login and the information doesn't match
        viewModel.wronglogin.observe(viewLifecycleOwner, Observer { wrongData ->
            if (wrongData) {
                Toast.makeText(
                    requireActivity(), "Incorrect email or password",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.wrongLogin()
            }
        })

        // this observer is activated is the login was right or the user creates a new account.
        // then the user will go to the welcome screen
        viewModel.loginOk.observe(viewLifecycleOwner, Observer { login ->
            if (login) {
                findNavController().navigate(LoginFragmentDirections.toWelcomeScreenAction())
                viewModel.loginCompleted()
            }
        })

        //this observer, tells the sharedViewModel that the email has changed
        viewModel.email.observe(viewLifecycleOwner, Observer { email ->
            // if a different email is actually logging, the shoe list will be a new one, as a new
            // user email is logging in the app
            sharedViewModel.setEmail(email)
        })

        // when this button is pressed an account will be created
        binding.createButton.setOnClickListener { view ->
            val email = binding.emailEditt.text.toString()
            val pass = binding.passEditt.text.toString()
            if (checkNoErrors(email, pass)) {
                viewModel.createAccount(
                    email,
                    pass
                )
            }
        }

        // this button checks if the account exists, if it is, the login will pass
        binding.loginButton.setOnClickListener { view ->
            val email = binding.emailEditt.text.toString()
            val pass = binding.passEditt.text.toString()
            if (checkNoErrors(email, pass)){
                viewModel.login(email, pass)
            }
        }

        return binding.root
    }

    /**
     * function to prevent errors from the user. It will return true if there are no empty fields
     * and the email is a valid email, otherwise it will return false
     */
    private fun checkNoErrors(email: String, pass: String): Boolean {
        return !emptyFields(email, pass) && isEmailValid(email)
    }

    /**
     * this function checks if there are some empty or blank fields when the user is logging
     */
    private fun emptyFields(email: String, pass: String): Boolean {
        return if (email.equals("") || pass.equals("")) { //if there is at least one empty field
            Toast.makeText(
                requireActivity(), "Please fill out all the fields",
                Toast.LENGTH_SHORT
            ).show()
            true
        } else
            false
    }

    /**
     * Verify is the email is correct or well written
     */
    private fun isEmailValid(email: CharSequence): Boolean {
        return if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(
                requireActivity(), "Please write a correct email",
                Toast.LENGTH_SHORT
            ).show()
            false
        }else
            true

    }
}