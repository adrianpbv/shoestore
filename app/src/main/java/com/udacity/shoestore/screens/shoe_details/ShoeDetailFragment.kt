package com.udacity.shoestore.screens.shoe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment: Fragment() {
    private lateinit var binding : ShoeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_detail_fragment, container, false)

        // this button cancels the action of adding a new shoe and goes to the ShopList
        binding.cancelButton.setOnClickListener { view ->
            view.findNavController().navigate(ShoeDetailFragmentDirections.toShopFragmentAction())
        }

        // this button takes all the new information and sent it to the shopList, after, the user
        // will navigate to the ShopFragment and the list is recreated including the new shoe
        binding.saveButton.setOnClickListener { view ->
            val name = binding.shoeNameEditt.text.toString()
            val company = binding.companyEditt.text.toString()
            val size : String = binding.sizeEditt.text.toString()
            val des = binding.descriptionEditt.text.toString()

            if(name != "" && company != "" && size != "" && des != ""){
                val newShoe = Shoe(name = name, company = company, size = size.toDouble(),
                    description = des)
                view.findNavController().navigate(
                    ShoeDetailFragmentDirections.toShopFragmentAction(newShoe))
            }else{
                // A message is shown if the user forgot to write some information,
                // it could be more flexible or specific
                Toast.makeText(requireContext(),"Missing data to complete",
                    Toast.LENGTH_SHORT).show()
                clean_data()
            }
        }

        return binding.root

    }

    /**
     * Clean all the user input information about the new shoe
     */
    private fun clean_data(){
        val editable_texts : List<EditText> = listOf(binding.shoeNameEditt, binding.companyEditt,
            binding.sizeEditt, binding.sizeEditt)
        for (view in editable_texts){
            view.setText("")
        }
    }
}