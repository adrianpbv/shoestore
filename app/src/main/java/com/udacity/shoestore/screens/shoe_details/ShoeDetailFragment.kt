package com.udacity.shoestore.screens.shoe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoe_list.SharedViewModel

class ShoeDetailFragment: Fragment() {
    private lateinit var binding : ShoeDetailFragmentBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_detail_fragment, container, false)

        binding.newshoe = Shoe()

        // this button cancels the action of adding a new shoe and goes to the ShopList
        binding.cancelButton.setOnClickListener { view ->
            view.findNavController().navigate(ShoeDetailFragmentDirections.toShopFragmentAction())
        }

        // this button adds a new shoe to the list shoe in the sharedViewModel, after, the user
        // will navigate to the ShopFragment and the list is recreated including the new shoe
        binding.saveButton.setOnClickListener { view ->
            if (notNull()){
                sharedViewModel.add(binding.newshoe)
                view.findNavController().navigate(
                    ShoeDetailFragmentDirections.toShopFragmentAction())
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
     * this function checks if all the fields are not empty
     */
    private fun notNull(): Boolean{
        return binding.newshoe?.getShoeName() != "" &&
                binding.newshoe?.getShoeCompany() != "" &&
                binding.newshoe?.getShoeSize() != 0.0 &&
                binding.newshoe?.getShoeDesc() != ""
    }

    /**
     * Clean all the user input information about the new shoe
     */
    private fun clean_data(){
//        val editable_texts : List<EditText> = listOf(binding.shoeNameEditt, binding.companyEditt,
//            binding.sizeEditt, binding.sizeEditt)
//        for (view in editable_texts){
//            view.setText("")
//        }
        binding.newshoe?. apply {
            setShoeName("")
            setShoeCompany("")
            setShoeSize(0.0)
            setShoeDesc("")
        }
    }
}