package com.example.practicefundamental.main

import androidx.lifecycle.ViewModel
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.githubuserapplication.model.DetailRes
import com.example.instikapp.api.RetrofitUser
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class DetailViewModel : ViewModel() {
    private val _detail = MutableLiveData<DetailRes>()
    val detail: LiveData<DetailRes> = _detail

    private val _Loading = MutableLiveData<Boolean>()
    val Loading: LiveData<Boolean> = _Loading

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status


    internal fun getdetail(login: String) {
        _Loading.value = true
        val Detailclient = RetrofitUser.getApiService().getUserDetail(login)
        Detailclient.enqueue(object : Callback<DetailRes> {
            override fun onResponse(
                call: Call<DetailRes>,
                response: Response<DetailRes>
            ) {
                _Loading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detail.value = response.body()
                        Log.d(
                            "NamaAplikasi",
                            "Data berhasil diambil detail response: ${_detail.value}"
                        )
                    }
                } else {
                    Log.d(TAG, "API gagal di panggil ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailRes>, t: Throwable) {
                Log.e(TAG, "API call failed: ${t.message}")
            }
        })
    }
}
