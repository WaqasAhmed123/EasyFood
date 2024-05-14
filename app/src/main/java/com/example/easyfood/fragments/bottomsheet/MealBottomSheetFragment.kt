package com.example.easyfood.fragments.bottomsheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.activities.CategoryMealsActivity
import com.example.easyfood.activities.MealDetailsActivity
import com.example.easyfood.databinding.FragmentMealBottomSheetBinding
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.fragments.HomeFragment.Companion.MEAL_ID
import com.example.easyfood.repository.MealRepository
import com.example.easyfood.viewModel.MealDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.logging.Handler


private var MEAL_ID = "param1"

class MealBottomSheetFragment : BottomSheetDialogFragment() {
    private var mealId: String? = null
    private var mealName = ""
    private var mealStr = ""
    private var mealThumb = ""
    private var ytUrl = ""
    private lateinit var layoutBinding: FragmentMealBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(com.example.easyfood.fragments.bottomsheet.MEAL_ID)
        }
        Log.d("CheckId", "$mealId")
        MealRepository.getMealDetail(mealId!!)
//        observeBottomSheetMeal()
    }

    fun observeBottomSheetMeal() {
        MealRepository.observeMealDetailsLiveData().observe(viewLifecycleOwner) {

            Glide.with(this).load(it.strMealThumb).into(layoutBinding.imgBottomSheet)
            layoutBinding.tvLocationMealBottomSheet.text = it.strArea
            layoutBinding.tvCategoryMealBottomSheet.text = it.strCategory
            layoutBinding.tvMealNameBottomSheet.text = it.strMeal

            //set values
            mealName = it.strMeal
            mealThumb = it.strMealThumb

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        layoutBinding = FragmentMealBottomSheetBinding.inflate(inflater, container, false)
        return (layoutBinding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BSData", "view created")
        layoutBinding.mainLayout.visibility = View.INVISIBLE
        layoutBinding.skeltonFragmentBottomSheet.root.visibility = View.VISIBLE
        layoutBinding.skeltonFragmentBottomSheet.skeltonFragmentBottomSheet.startShimmer()
        android.os.Handler().postDelayed({
            layoutBinding.mainLayout.visibility = View.VISIBLE
            layoutBinding.skeltonFragmentBottomSheet.skeltonFragmentBottomSheet.stopShimmer()
            layoutBinding.skeltonFragmentBottomSheet.root.visibility = View.INVISIBLE

        }, 3000)
        observeBottomSheetMeal()
        layoutBinding.tvReadMoreBottomSheet.setOnClickListener {
            val intent = Intent(activity, MealDetailsActivity::class.java)
            intent.putExtra(MEAL_ID, mealId)
            intent.putExtra(HomeFragment.MEAL_NAME, mealName)
            intent.putExtra(HomeFragment.MEAL_THUMB, mealThumb)
            startActivity(intent)
        }


    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String): MealBottomSheetFragment {
            return MealBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    Log.d("CheckIdInFunc", "${param1}")
                    putString(com.example.easyfood.fragments.bottomsheet.MEAL_ID, param1)
                }
            }

        }
    }
}