package com.example.easyfood.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easyfood.activities.CategoryMealsActivity
import com.example.easyfood.activities.MainActivity
import com.example.easyfood.activities.MealDetailsActivity
import com.example.easyfood.adapters.CategoriesAdapter
import com.example.easyfood.adapters.MostPopularAdapter
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.fragments.bottomsheet.MealBottomSheetFragment
import com.example.easyfood.model.Category
import com.example.easyfood.model.ParticularCategoryMeal
import com.example.easyfood.model.Meal
import com.example.easyfood.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        const val MEAL_ID = "com.example.easyfood.fragments.idMeal"
        const val MEAL_NAME = "com.example.easyfood.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.easyfood.fragments.thumbMeal"
        const val   CATEGORY_NAME = "com.example.easyfood.fragments.categoryName"
        const val MEAL_STR = " com.example.easyfood.fragments.strMeal"
        const val MEAL_AREA = " com.example.easyfood.fragments.strArea"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel=(activity as MainActivity).homeViewModel
        popularItemsAdapter = MostPopularAdapter()
        categoriesAdapter = CategoriesAdapter()

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
        preparePopularItemsRecyclerView()
        prepareCategoriesItemsRecyclerView()

        homeViewModel.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

        homeViewModel.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()
        onPopularItemLongClick()

        homeViewModel.getAllCategories()
        observeCategoriesLiveData()
        onCategoryClick()

    }


    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun prepareCategoriesItemsRecyclerView() {
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observePopularItemsLiveData() {
        homeViewModel.observePopularItemsLiveData().observe(/* owner = */ viewLifecycleOwner

        ) /* observer = */

        {
            Log.d("popularItems", "${it}")
            popularItemsAdapter.setMeals(mealsList = it as ArrayList<ParticularCategoryMeal>)
        }
    }

    private fun observeCategoriesLiveData() {
        homeViewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) {
            Log.d("CategoriesObs", "${it}")

            categoriesAdapter.setCategoryList(categoryList = it as ArrayList<Category>)

        }

    }

    private fun onRandomMealClick() {
        binding.imgRandomMeal.setOnClickListener {
            val intent = Intent(activity, MealDetailsActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        homeViewModel.observeRandomMealLiveData()
            .observe(/* owner = */ viewLifecycleOwner,/* observer = */

                { meal ->
                    Glide.with(this).load(meal.strMealThumb).into(binding.imgRandomMeal)
                    this.randomMeal = meal
                })
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealDetailsActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun onPopularItemLongClick() {
        popularItemsAdapter.onLongItemClick = { meal ->
            Log.d("CheckIdInHome", "${meal.idMeal}")

        val mealBottomSheet = MealBottomSheetFragment.newInstance(param1 = meal.idMeal)
            mealBottomSheet.show(childFragmentManager,"Mealsheet")
        }
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
//            intent.putExtra(MEAL_NAME, meal.strMeal)
//            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }




}