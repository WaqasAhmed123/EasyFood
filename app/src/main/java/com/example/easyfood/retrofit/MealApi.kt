package com.example.easyfood.retrofit

import com.example.easyfood.model.CategoryList
import com.example.easyfood.model.MealList
import com.example.easyfood.model.ParticularCategoryMealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i")id:String):Call<MealList>

    @GET("filter.php?")
//    fun getPopularItems(@Query("c")categoryName:String):Call<List<Meal>>
    fun getPopularItems(@Query("c")categoryName:String):Call<ParticularCategoryMealList>

    @GET("categories.php")
    fun getAllCategories():Call<CategoryList>

}