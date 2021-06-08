package com.udacity.shoestore.screens.shoe_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe


class ShopViewModel(shoe_sent: Shoe?): ViewModel() {

    // list of shoes
    private val shoes = mutableListOf<Shoe>()

    // LiveData to hold the information related to all the shoes
    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    init {
        //adding some shoes as an example
        shoes.run {
            add(
                Shoe(
                    name = "Nike Air Max 270", size = 43.0, company = "Nike",
                    description = "Nike has always been at the leading edge of innovation, technology " +
                            "development, and cutting-edge marketing campaigns that help it surpass " +
                            "other brands in terms of popularity and sales"
                )
            )
            add(
                Shoe(
                    name = "Superstars shoes", size = 40.0, company = "Adidas",
                    description = "With its roots in Germany, adidas has become one of the top shoe " +
                            "brands in the world. The company produces more than 900 million sports and " +
                            "lifestyle products with independent manufacturing partners around the world. "
                )
            )
        }

        //Verify if the argument passed is null
        // if null there is no new show to add
        if (shoe_sent != null) {
            addShoe(shoe_sent)
        } else{
            // keep on the default list of shoes
            _shoeList.value = shoes
        }
    }

    /**
     * this function adds a shoe to the predefined shoe list
     */
    fun addShoe(shoe: Shoe) {
        shoes.add(shoe)
        _shoeList.value = shoes
    }

}