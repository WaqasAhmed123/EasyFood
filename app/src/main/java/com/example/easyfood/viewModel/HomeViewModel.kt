package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.retrofit.RetrofitInstance
import com.example.easyfood.model.Meal
import com.example.easyfood.model.Category
import com.example.easyfood.model.CategoryList
import com.example.easyfood.model.MealList
import com.example.easyfood.model.ParticularCategoryMeal
import com.example.easyfood.model.ParticularCategoryMealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<ParticularCategoryMeal>>()
    private var allCategoriesLiveData = MutableLiveData<List<Category>>()

    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    Log.d("randomMeal", "${response.body()}")
                    randomMealLiveData.value = randomMeal


                } else {
                    return

                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("RandomMeal", t.message.toString())
            }

        })
    }

    fun getPopularItems() {
        RetrofitInstance.api.getPopularItems("Seafood")
            .enqueue(object : Callback<ParticularCategoryMealList> {


                override fun onResponse(
                    call: Call<ParticularCategoryMealList>,
                    response: Response<ParticularCategoryMealList>
                ) {
                    if (response.body() != null) {
                        val popularItems: List<ParticularCategoryMeal> = response.body()!!.meals
                        Log.d("popularItems", "${response.body()}")
                        popularItemsLiveData.value = popularItems


                    } else {
                        return

                    }
                }

                override fun onFailure(call: Call<ParticularCategoryMealList>, t: Throwable) {
                    Log.d("PopularMeal", t.message.toString())

                }

            })
    }

    fun getAllCategories() {
        RetrofitInstance.api.getAllCategories()
            .enqueue(object : Callback<CategoryList> {


                override fun onResponse(
                    call: Call<CategoryList>, response: Response<CategoryList>
                ) {
                    if (response.body() != null) {
                        val allCategoriesMeals: List<Category> = response.body()!!.categories
                        Log.d("AllCategories", "${response.body()}")
                        allCategoriesLiveData.value = allCategoriesMeals


                    } else {
                        return

                    }
                }

                override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    Log.d("PopularMeal", t.message.toString())

                }

            })
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData

    }

    fun observePopularItemsLiveData(): LiveData<List<ParticularCategoryMeal>> {
        return popularItemsLiveData

    }

    fun observeCategoriesLiveData(): LiveData<List<Category>> {
        return allCategoriesLiveData

    }

}