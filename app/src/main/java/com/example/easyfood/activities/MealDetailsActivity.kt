package com.example.easyfood.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.fragments.HomeFragment.Companion.MEAL_ID
import com.example.easyfood.fragments.HomeFragment.Companion.MEAL_NAME
import com.example.easyfood.fragments.HomeFragment.Companion.MEAL_THUMB
import com.example.easyfood.model.Meal
import com.example.easyfood.viewModel.MealDetailsViewModel

class MealDetailsActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMealBinding
    private  lateinit var mealDetailsMvvm: MealDetailsViewModel
    private var mealId = ""
    private var mealName = ""
    private var mealStr = ""
    private var mealThumb = ""
    private var ytUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealDetailsMvvm = ViewModelProviders.of(this)[MealDetailsViewModel::class.java]
        binding=ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingCase()
        getMealInfoFromIntent()
        mealDetailsMvvm.getMealDetail(id = mealId)
        setInformationInViews()
        observeMealDetailsLiveData()


        binding.imgYoutube.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ytUrl)))
        }
    }



    private fun observeMealDetailsLiveData() {
        mealDetailsMvvm.observeRandomMealLiveData().observe(this,object :Observer<Meal>{
            override fun onChanged(value: Meal) {
                onResponseCase()
                binding.tvCategoryInfo.text="category : ${value.strCategory}"
                binding.tvAreaInfo.text="Area : ${value.strArea}"
                binding.tvContent.text="category : ${value.strInstructions}"
                ytUrl=value.strYoutube
            }

        })
    }



//    binding.imgYoutube.setOnClickListener {
//
//        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ytUrl)))
//    }

    private fun setInformationInViews() {
    Glide.with(applicationContext).load(mealThumb).into(binding.imgMealDetail)
        binding.collapsingToolbar.title=mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
    }


    private fun getMealInfoFromIntent() {
        val tempIntent = intent

        this.mealId = tempIntent.getStringExtra(MEAL_ID)!!
        this.mealName = tempIntent.getStringExtra(MEAL_NAME)!!
        this.mealThumb = tempIntent.getStringExtra(MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility= View.VISIBLE
        binding.btnFav.visibility=View.INVISIBLE
        binding.tvInstructions.visibility=View.INVISIBLE
        binding.tvContent.visibility=View.INVISIBLE
        binding.tvCategoryInfo.visibility=View.INVISIBLE
        binding.tvAreaInfo.visibility=View.INVISIBLE
        binding.imgYoutube.visibility=View.INVISIBLE
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnFav.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvContent.visibility = View.VISIBLE
        binding.tvCategoryInfo.visibility = View.VISIBLE
        binding.tvAreaInfo.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}