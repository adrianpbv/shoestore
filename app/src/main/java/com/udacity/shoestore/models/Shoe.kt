package com.udacity.shoestore.models

import androidx.databinding.*
import androidx.databinding.library.baseAdapters.BR


data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
) : BaseObservable() {
    constructor() : this("", 0.0, "", "")

    @Bindable
    fun getShoeName(): String {
        return name
    }

    fun setShoeName(newName: String) {
        if (name != newName) {
            name = newName

            notifyPropertyChanged(BR.shoeName)
        }
    }

    @Bindable
    fun getShoeSize(): Double {
        return size
    }

    fun setShoeSize(newSize: Double) {
        if(newSize != size) {
            size = newSize

            notifyPropertyChanged(BR.shoeSize)
        }
    }

    @Bindable
    fun getShoeCompany(): String {
        return company
    }

    fun setShoeCompany(newCompany: String) {
        if (company != newCompany) {
            company = newCompany

            notifyPropertyChanged(BR.shoeCompany)
        }
    }

    @Bindable
    fun getShoeDesc(): String {
        return description
    }

    fun setShoeDesc(newDesc: String) {
        if (description != newDesc) {
            description = newDesc

            notifyPropertyChanged(BR.shoeDesc)
        }
    }

}

object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic fun doubleToString(size: Double): String{
        return size.toString()
    }

    @JvmStatic fun stringToDouble(size: String): Double{
        if (!size.isEmpty())
            return size.toDouble()
        else
            return 0.0
    }
}
