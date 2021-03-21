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

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?= null
    fun fetch(studentid:String) {
        //val student1 = Student("16055","Nonie","1998/03/28","5718444778","https://st2.depositphotos.com/1006318/5909/v/600/depositphotos_59095493-stock-illustration-profile-icon-male-avatar.jpg")
        //studentLD.value = student1
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=" + studentid

        val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val sType = object : TypeToken<Student>() { }.type
                    val result = Gson().fromJson<Student>(response, sType)
                    studentLD.value = result
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
