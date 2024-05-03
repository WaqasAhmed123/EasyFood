package com.example.easyfood.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.easyfood.activities.MealDetailsActivity
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.projo.Meal
import com.example.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal

    companion object {
        const val MEAL_ID = "com.example.easyfood.fragments.idMeal"
        const val MEAL_NAME = "com.example.easyfood.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.easyfood.fragments.thumbMeal"
        const val CATEGORY_NAME = " com.example.easyfood.fragments.categoryName"
        const val MEAL_STR = " com.example.easyfood.fragments.strMeal"
        const val MEAL_AREA = " com.example.easyfood.fragments.strArea"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

    }

    private fun onRandomMealClick() {
        binding.imgRandomMeal.setOnClickListener {
            val intent = Intent(activity, MealDetailsActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_STR, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

//    private fun observeRandomMeal() {
//        homeMvvm.observeRandomMealLiveData()
//            .observe(/* owner = */ viewLifecycleOwner,/* observer = */
//                object : Observer<Meal> {
//                    override fun onChanged(value: Meal) {
//                        Glide.with(this@HomeFragment).load(value.strMealThumb)
//                            .into(binding.imgRandomMeal)
//                    }
//
//                })
//    }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData()
            .observe(/* owner = */ viewLifecycleOwner,/* observer = */

                {
                    meal->
                    Glide.with(this).load(meal.strMealThumb).into(binding.imgRandomMeal)
                    this.randomMeal=meal
                }
//                object : Observer<Meal> {
//                    override fun onChanged(value: Meal) {
//                        Glide.with(this@HomeFragment).load(value.strMealThumb)
//                            .into(binding.imgRandomMeal)
//                    }

//                }
            )
    }


}