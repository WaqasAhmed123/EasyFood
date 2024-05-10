package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.retrofit.RetrofitInstance
import com.example.easyfood.model.Meal
import com.example.easyfood.model.MealList
import com.example.easyfood.repository.MealRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsViewModel():ViewModel (){
//    private var mealDetailsLiveData= MutableLiveData<Meal>()
//    private var bottomSheetLiveData= MutableLiveData<Meal>()
    fun getMealDetail(id:String){
        MealRepository.getMealDetail(id)
    }

//    fun getMealDetail(id:String){
//        RetrofitInstance.api.getMealDetails(id = id).enqueue(object :Callback<MealList>{
//            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
//                if(response.body()!=null){
//                    Log.d("Response of Meal in SHeet","${response.body()}")
//                    mealDetailsLiveData.value=response.body()!!.meals[0]
//                }
//                else
//                {
//                    return
//                }
//            }
//
//            override fun onFailure(call: Call<MealList>, t: Throwable) {
//                Log.d("MealDetails", t.message.toString())
//
//            }
//
//        })
//    }

    fun observeMealDetailsLiveData(): LiveData<Meal> {
        return MealRepository.mealDetailsLiveData

    }

}