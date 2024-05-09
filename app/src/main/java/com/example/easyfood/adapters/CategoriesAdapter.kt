package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.CategoryItemBinding
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.model.Category
import com.example.easyfood.model.ParticularCategoryMeal

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoriesList=ArrayList<Category>()
    lateinit var onItemClick:((Category)->Unit)

    fun setCategoryList(categoryList:ArrayList<Category>){
        this.categoriesList= categoryList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return  CategoriesViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
    inner class CategoriesViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return  categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(categoriesList[position].strCategoryThumb).into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text=categoriesList[position].strCategory
        holder.itemView.setOnClickListener{
            onItemClick.invoke(categoriesList[position])

        }
    }

}
