package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment: Fragment() {
    private lateinit var binding : WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment,
            container, false)

        // this button will navigate to the Instructions screen
        binding.welcomeButton.setOnClickListener { view ->
            view.findNavController().navigate(WelcomeFragmentDirections.toInstructionScreenAction())
        }



        return binding.root
    }

}