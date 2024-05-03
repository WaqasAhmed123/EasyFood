package com.example.easyfood.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.projo.CategoryMeals

class MostPopularAdapter() : RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    private var mealsList = ArrayList<CategoryMeals>()

    fun setMeals(mealsList: ArrayList<CategoryMeals>) {
        this.mealsList = mealsList
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(
        holder: PopularMealViewHolder, position: Int
    ) {
        Glide.with(holder.itemView.context)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)
    }

    class PopularMealViewHolder(var binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)
}
