package com.udacity.shoestore.models

import android.os.Parcelable
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.*
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize
import java.lang.Exception
import java.util.*

//@Parcelize
data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
) : BaseObservable() {
    constructor() : this("", 0.0, "", "")

    //private val propertyChangeRegistry = PropertyChangeRegistry()

    @Bindable
    fun getShoeName(): String {
        return name
    }

    fun setShoeName(newName: String) {
        if (name != newName) {
            name = newName
            //propertyChangeRegistry.notifyChange(this, BR.name)
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
            //propertyChangeRegistry.notifyChange(this, BR.size)
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
            //propertyChangeRegistry.notifyChange(this, BR.name)
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
            //propertyChangeRegistry.notifyChange(this, BR.name)
            notifyPropertyChanged(BR.shoeDesc)
        }
    }
//    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
//        propertyChangeRegistry.add(callback)
//    }
//    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
//        propertyChangeRegistry.remove(callback)
//    }
}

//object SizeEdittBindingAdapter{
//    @BindingAdapter("sizeEditt")
//    @JvmStatic fun setSizeEditt(view: EditText, value: Double){
//        view.setText(value.toString())
//    }
//
//    @InverseBindingAdapter(attribute = "sizeEditt")
//    @JvmStatic fun getSizeEditt(view: EditText): String{
//        return view.text.toString()
//    }
//}


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
