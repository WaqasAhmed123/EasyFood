package com.example.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.retrofit.RetrofitInstance
import com.example.projo.Meal
import com.example.projo.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsViewModel():ViewModel (){
    private var mealDetailsLiveData= MutableLiveData<Meal>()

    fun getMealDetail(id:String){
        RetrofitInstance.api.getMealDetails(id = id).enqueue(object :Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body()!=null){
                    mealDetailsLiveData.value=response.body()!!.meals[0]
                }
                else
                {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealDetails", t.message.toString())

            }

        })
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return mealDetailsLiveData

    }

}