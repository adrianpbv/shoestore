package com.udacity.shoestore.screens.shoe_list

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShopFragmentBinding

/**
 * This class stand for displaying the list of shoes and show the logout menu
 */
class ShopFragment: Fragment() {
    private lateinit var binding : ShopFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.shop_fragment, container, false)

        setHasOptionsMenu(true) //tells android to put a menu

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu,menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        //if the NavigationUI doesn't handle the selection we call super.onOptionsItemSelected(item)
//        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
//                super.onOptionsItemSelected(item)//it allow your app to directly handle the menu item without navigating
//
//    }
}