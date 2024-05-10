package com.example.easyfood.fragments.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.FragmentMealBottomSheetBinding
import com.example.easyfood.fragments.HomeFragment.Companion.MEAL_ID
import com.example.easyfood.repository.MealRepository
import com.example.easyfood.viewModel.MealDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


private const val MEAL_ID = "param1"

class MealBottomSheetFragment : BottomSheetDialogFragment() {
//    val mealDetailsViewModel: MealDetailsViewModel by lazy {
//        ViewModelProvider(this).get(MealDetailsViewModel::class.java)
//
//    }
    private lateinit var layoutBinding: FragmentMealBottomSheetBinding
    private var mealId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId=it.getString(MEAL_ID)
        }
        Log.d("CheckId", "$mealId")
        MealRepository.getMealDetail(mealId!!)
//        observeBottomSheetMeal()
    }

     fun observeBottomSheetMeal() {
        MealRepository.observeMealDetailsLiveData().observe(viewLifecycleOwner) {
            Log.d("BSData","${it}")
            Glide.with(this).load(it.strMealThumb).into(layoutBinding.imgBottomSheet)
            layoutBinding.tvLocationMealBottomSheet.text = it.strArea
            layoutBinding.tvCategoryMealBottomSheet.text = it.strCategory
            layoutBinding.tvMealNameBottomSheet.text = it.strMeal

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        layoutBinding = FragmentMealBottomSheetBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return (layoutBinding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BSData","view created")

        observeBottomSheetMeal()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String): MealBottomSheetFragment {
            return MealBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    Log.d("CheckIdInFunc", "${param1}")
                    putString(MEAL_ID, param1)
                }
            }

        }
    }
}