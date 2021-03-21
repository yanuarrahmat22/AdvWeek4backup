package com.example.a160418042_advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160418042_advweek4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue ?= null

    fun refresh() {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778","https://st2.depositphotos.com/1006318/5909/v/600/depositphotos_59095493-stock-illustration-profile-icon-male-avatar.jpg")
//        val student2 = Student("13312","Rich","1994/12/14","3925444073","https://st2.depositphotos.com/1006318/5909/v/600/depositphotos_59095493-stock-illustration-profile-icon-male-avatar.jpg")
//        val student3 = Student("11204","Dinny","1994/10/07","6827808747","https://st2.depositphotos.com/1006318/5909/v/600/depositphotos_59095493-stock-illustration-profile-icon-male-avatar.jpg")
//
//        val studentList:ArrayList<Student> = arrayListOf<Student>(student1, student2, student3)
//        studentsLD.value = studentList
        loadingErrorLD.value = false
        loadingDoneLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object : TypeToken<List<Student>>() { }.type
                    val result = Gson().fromJson<List<Student>>(response, sType)
                    studentsLD.value = result
                    loadingDoneLD.value = false
                    Log.d("showvoley", response.toString())
                },
                {
                    Log.d("showvoley", it.toString())
                    loadingErrorLD.value = true
                    loadingDoneLD.value = false
                })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }


}


