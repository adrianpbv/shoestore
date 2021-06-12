    package com.udacity.shoestore.screens.shoe_list

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import com.udacity.shoestore.models.Shoe

    // factory to create a ShopViewModel with an argument as it is initialized
    class ShopViewModelFactory(private val shoe: Shoe?) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShopViewModel::class.java)) {
                //return ShopViewModel(shoe) as
                return ShopViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

