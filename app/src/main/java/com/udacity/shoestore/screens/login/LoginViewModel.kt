package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // liveData to hold the email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    // liveData to hold the password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    // liveData to watch if the login was fine
    private val _loginOk = MutableLiveData<Boolean>()
    val loginOk: LiveData<Boolean>
        get() = _loginOk

    // liveData to be activated is something is wrong when login
    private val _wronglogin = MutableLiveData<Boolean>()
    val wronglogin: LiveData<Boolean>
        get() = _wronglogin


    init {
        _email.value = "adrian@gmail.com" // for testing
        _password.value = "123" // for testing
        _wronglogin.value = false
        _loginOk.value = false
    }

    /**
     * function for creating an account
     */
    fun createAccount(e_mail: String, pass: String) {
        _email.value = e_mail
        _password.value = pass
        // the account is created and the welcome screen comes out.
        _loginOk.value = true
    }

    /**
     * function for log-in the ShoeStore
     */
    fun login(e_mail: String, pass: String) {
        if (email.value == e_mail && password.value == pass) {
            // if the user is already registered the welcome sreen is shown
            _loginOk.value = true
        } else {
            // some login data is wrong and a message will show the error
            _wronglogin.value = true
        }
    }


    /**
     * it's called when the user tries to login and the data doesn't match with the save one
     */
    fun wrongLogin() {
        _wronglogin.value = false
    }

    /**
     * it's called whe the user enters successfully in the app
     */
    fun loginCompleted() {
        _loginOk.value = false
    }
}