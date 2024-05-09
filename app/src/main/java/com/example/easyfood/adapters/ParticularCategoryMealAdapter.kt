package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.MealItemBinding
import com.example.easyfood.model.ParticularCategoryMeal

class ParticularCategoryMealAdapter :
    RecyclerView.Adapter<ParticularCategoryMealAdapter.ParticularCategoryMealViewHolder>() {
    private var mealsByCategoryList = mutableListOf<ParticularCategoryMeal>()

    //private var mealsByCategoryList = ArrayList<ParticularCategoryMeal>()
    fun setMealsByCategoryList(mealsList: ArrayList<ParticularCategoryMeal>) {
        this.mealsByCategoryList = mealsList
        notifyDataSetChanged()

    }

    inner class ParticularCategoryMealViewHolder(var binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ParticularCategoryMealViewHolder {
        return ParticularCategoryMealViewHolder(MealItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return mealsByCategoryList.size
    }

    override fun onBindViewHolder(holder: ParticularCategoryMealViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(mealsByCategoryList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text =mealsByCategoryList[position].strMeal
    }

}