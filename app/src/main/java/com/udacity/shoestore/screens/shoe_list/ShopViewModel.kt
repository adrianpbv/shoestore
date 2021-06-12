package com.udacity.shoestore.screens.shoe_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe


class ShopViewModel(): ViewModel() {

    // list of shoes
    private val shoes = mutableListOf<Shoe>()

    // LiveData to hold the information related to all the shoes
    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    /**
     * this function updates the shoe list from the sharedViewModel
     */
    fun newShoeList(shoes: List<Shoe>?){
//        if (!equalsLists(shoes)){
//            _shoeList.value = shoes
//        }
        _shoeList.value = shoes
    }

    /**
     * This function checks if the list of shoes has changed or are different, if it is,
     * the function will returns true otherwise returns false.
     *
     * this is useful as the lists are being checked continuously as them are observables objects,
     * they need only be updated by each other when new changes have showed up.
     *
     * If a new shoe is added, the sizes of the lists will be different and thus one of the lists
     * is updated so them can be equals.
     */
    fun equalsLists(newShoes: List<Shoe>?): Boolean {
        val oldShoes: List<Shoe>? = _shoeList.value
        if (oldShoes != null && newShoes != null) {
            return oldShoes.size == newShoes.size
        }
        return false
    }
}