package com.example.easyfood.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.easyfood.R
import com.example.easyfood.activities.MainActivity
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.databinding.FragmentFavoritesBinding
import com.example.easyfood.viewModel.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    private lateinit var dataBinding: FragmentFavoritesBinding
    private lateinit var homeViewModel:HomeViewModel
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = (activity as MainActivity).homeViewModel
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentFavoritesBinding>(
            inflater, R.layout.fragment_favorites, container, false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.viewModel=homeViewModel
        dataBinding.lifecycleOwner=this
//        homeViewModel.countValueLiveData.observe(viewLifecycleOwner,
//            {
//                dataBinding.currentValue.text= it.toString()
//            })

//        fun incrementValue(){
//            dataBinding.increaseValue.setOnClickListener {
//                homeViewModel.countValueLiveData.value =
//                    homeViewModel.countValueLiveData.value?.plus(1)
//                Log.d("current value plus", "${homeViewModel.countValueLiveData.value}")
//            }
//
//        }
//        fun decrementValue(){
//            dataBinding.decreaseValue.setOnClickListener {
//                homeViewModel.countValueLiveData.value =
//                    homeViewModel.countValueLiveData.value?.minus(1)
//            }
//            Log.d("current value minus", "${homeViewModel.countValueLiveData.value}")
////        homeViewModel.countValueLiveData.value=
//        }

        }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FavoritesFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}