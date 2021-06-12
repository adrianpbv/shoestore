package com.udacity.shoestore.screens.shoe_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {
    //LiveData to hold the shoe list
    val lshoe = mutableListOf<Shoe>()
    val _sharedlistShoe = MutableLiveData<List<Shoe>>()
    val sharedListShoe: LiveData<List<Shoe>>
        get() = _sharedlistShoe

    //LiveData that for watching if the user email has changed
    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    init {
        _email.value = "adrian@gmail.com"
    }

    /**
     * Adds a new shoe to the shoe list
     */
    fun add(item: Shoe?) {
        if (item != null) {
            lshoe.add(item)
            _sharedlistShoe.value = lshoe
        }
    }

    /**
     * Sets the user email. This data is hold as long as the user doesn't logout and changes to
     * another email.
     */
    fun setEmail(email: String){
        if (email != _email.value) {
            _email.value = email
            deleteShoeList()
        }
    }

    /**
     * Delete all the shoes in the current list.
     *
     * This happens when the user logged out from the shoe list screen and another different email
     * do the log-in
     */
    private fun deleteShoeList(){
        lshoe.removeAll(lshoe)
        _sharedlistShoe.value = lshoe
    }

    // it is also possible to move some of the logic of the loginViewModel to this sharedViewModel
    // in order to keep the login information if the user logout and later come back as long as the
    // app keeps running.
}