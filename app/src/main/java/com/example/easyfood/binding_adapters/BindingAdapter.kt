package com.example.easyfood.binding_adapters

//class BindingAdapter {}
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@BindingAdapter("liveDataIntText")
fun bindLiveDataIntText(textView: TextView, liveData: MutableLiveData<Int>?) {
    Log.d("func adap", "${liveData!!.value}")
    liveData?.value?.let {
        textView.text = it.toString()
    }
}
