package com.example.easyfood.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.projo.Meal
import com.example.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var homeMvvm:HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observeRandomMeal()

        }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(/* owner = */ viewLifecycleOwner,/* observer = */
            object :Observer<Meal>{
                override fun onChanged(value: Meal) {
                                        Glide.with(this@HomeFragment).load(value.strMealThumb)
                        .into(binding.imgRandomMeal)
                }

            })
    }


}