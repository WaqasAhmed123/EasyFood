package com.example.easyfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.viewModel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var layoutBinding: ActivityCategoryMealsBinding
    private lateinit var categoryMealsMvvm: CategoryMealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(layoutBinding.root)
        categoryMealsMvvm=ViewModelProviders.of(this).get(CategoryMealsViewModel::class.java)
//        categoryMealsMvvm.getMealsByCategory()

    }
}