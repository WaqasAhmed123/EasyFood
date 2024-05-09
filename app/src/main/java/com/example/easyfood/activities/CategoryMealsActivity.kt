package com.example.easyfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.R
import com.example.easyfood.adapters.ParticularCategoryMealAdapter
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.model.ParticularCategoryMeal
import com.example.easyfood.viewModel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var layoutBinding: ActivityCategoryMealsBinding
    private lateinit var categoryMealsViewModel: CategoryMealsViewModel
    private lateinit var categoryMealsAdapter: ParticularCategoryMealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(layoutBinding.root)
        categoryMealsViewModel= ViewModelProviders.of(this)[CategoryMealsViewModel::class.java]
        categoryMealsAdapter= ParticularCategoryMealAdapter()
        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)


        categoryMealsViewModel.observeCategoryMealsLiveData().observe(this, Observer {
            categoryMealsAdapter.setMealsByCategoryList(mealsList = it as ArrayList<ParticularCategoryMeal>)
            layoutBinding.tvCategoryCount.text="${intent.getStringExtra(HomeFragment.CATEGORY_NAME)}:${it.size}"

            Log.d("CatMealsList","$it")
        })


        layoutBinding.rvMeals.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter =categoryMealsAdapter
        }
    }


}