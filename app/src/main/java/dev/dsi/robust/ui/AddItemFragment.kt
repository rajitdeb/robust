package dev.dsi.robust.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import dev.dsi.robust.R
import dev.dsi.robust.databinding.FragmentAddItemBinding
import dev.dsi.robust.fridge.Database.FridgeItems
import dev.dsi.robust.fridge.Database.FridgeViewModel
import dev.dsi.robust.utils.Constants
import dev.dsi.robust.utils.Snacker

@Suppress("DEPRECATION")
class AddItemFragment : Fragment() {
    private lateinit var fridgeViewmodel: FridgeViewModel
    private var _binding: FragmentAddItemBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)

        binding.toolbarDashboardFridge.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.fabCheck.setOnClickListener {
            fridgeViewmodel = ViewModelProviders.of(this).get(FridgeViewModel::class.java)


            if(binding.itemNameEditText.text.toString() == ""){
                Snacker(it,"Enter your item name").error()
            }

            else if(binding.itemCountEditText.text.toString() == ""){
                Snacker(it, "Enter the quantity you want to store").error()
            }

            else if(binding.itemExpiryEditText.text.toString() == ""){
                Snacker(it, "Enter the expiry in number of days").error()
            }

            else if(binding.itemCountEditText.text.toString() == ""){
                Snacker(it, "Enter the quantity you want to store").error()
            }

            else if(binding.itemTag.selectedItem.toString() == ""){
                Snacker(it, "Please select a tag").error()
            }

            else {
                val fridge = FridgeItems(
                    itemName = binding.itemNameEditText.text.toString(),
                    itemExpiry = binding.itemExpiryEditText.text.toString().toLong(),
                    itemQuantity = binding.itemCountEditText.text.toString().toLong(),
                    itemTag = binding.itemTag.selectedItem.toString(),
                    bg = Constants.getRandomCardColor()
                )
                fridge.id = System.currentTimeMillis().toInt()
                fridgeViewmodel.insert(fridge)
                findNavController().navigate(R.id.action_addItemFragment_to_fridgeFragment)
            }
        }
        return binding.root
    }


}