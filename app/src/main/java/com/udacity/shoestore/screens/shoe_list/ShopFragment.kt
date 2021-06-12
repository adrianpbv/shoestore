package com.udacity.shoestore.screens.shoe_list

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShopFragmentBinding
import com.udacity.shoestore.models.Shoe


/**
 * This class stand for displaying the list of shoes and show the logout menu
 */
class ShopFragment : Fragment() {
    private lateinit var binding: ShopFragmentBinding
    private lateinit var viewModel: ShopViewModel
    //private lateinit var viewModelFactory: ShopViewModelFactory
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater, R.layout.shop_fragment, container, false
        )

        viewModel = ViewModelProvider(this).get(ShopViewModel::class.java)

        binding.lifecycleOwner = this

        sharedViewModel.sharedListShoe.observe(viewLifecycleOwner, { shoeList ->
            viewModel.newShoeList(shoeList)
        })

        // if the shoe list changes the changes are recreated on the UI, thus when the user adds
        // another shoe from the shoe's details screen and come back, this shoe list is refreshed
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            if (!shoeList.isEmpty()){
                binding.firstShoeText.visibility = View.GONE
                for (shoe in shoeList) {
                    // here there are two way of accomplish the target, 'i prefer the first one'
                    binding.listshopLinear.addView(getShoe(inflater,container, shoe))
                    //binding.listshopLinear.addView(createView(shoe))
                }
            }
        })

        setHasOptionsMenu(true) //tells android to put a menu

        // the floating button will lead the user to the shoe's details screen,
        // and there add one shoe
        binding.floatingActionButton2.setOnClickListener { view ->
            view.findNavController().navigate(ShopFragmentDirections.toShoeDetailScreenAction())
        }

        return binding.root
    }
    // to create a menu in the toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //this take the user to the login screen
        if(item.itemId == R.id.menu_item_logout){
            requireView().findNavController().navigate(
                ShopFragmentDirections.toLoginFragmentAction())
        }
        return super.onOptionsItemSelected(item)

    }

    /**
     * this function return the shoe view to add it to the list in the ShopFragment.
     * the view is a pre-defined layout so it will be inflated with the new shoe information
     */
    private fun getShoe(inflater: LayoutInflater, container: ViewGroup?, shoe: Shoe): View {
        val shoe_binding : ShoeItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_item, container, false)

        shoe_binding.shoeItem = shoe
        return shoe_binding.root
    }

    /**
     * this function return a single textView with all the formatted shoe's information
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun createView(shoe: Shoe): View{
        val textView = TextView(this.context)
        //setting up the params related to the parent of the textView
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        // adding some style to the information
        val styledText: Spanned = Html.fromHtml(
            getString(
                R.string.shoe_item_details, shoe.name, shoe.size.toString(),
                shoe.company, shoe.description),
            Html.FROM_HTML_MODE_LEGACY)

        textView.text = styledText
        textView.textSize = 20F

        layoutParams.topMargin = 4
        // let the textView knows about some params of his parent
        textView.layoutParams = layoutParams

        return textView
    }
}