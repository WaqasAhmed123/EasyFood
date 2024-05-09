package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.model.Category
import com.example.easyfood.model.ParticularCategoryMealList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel : ViewModel() {

    private val _mealsByCategoryLiveData = MutableLiveData<ParticularCategoryMealList>()


    fun getMealsByCategory(category: String) {
        RetrofitInstance.api.getMealsByCategory(categoryName = category)
            .enqueue(object : Callback<ParticularCategoryMealList> {
                override fun onResponse(
                    call: Call<ParticularCategoryMealList>,
                    response: Response<ParticularCategoryMealList>
                ) {
                    response?.body().let { _mealsByCategoryLiveData.postValue(it) }
                }

                override fun onFailure(call: Call<ParticularCategoryMealList>, t: Throwable) {
                    Log.d("Error retreving categ meals", t.message.toString())

                }

            })
    }
}