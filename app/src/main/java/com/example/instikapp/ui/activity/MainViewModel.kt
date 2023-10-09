package com.example.instikapp.ui.activity

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instikapp.api.Api
import com.example.instikapp.api.RetrofitUser
import com.example.instikapp.model.GitUser
import com.example.instikapp.model.UserRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel()  {

    private val TAG = "MainViewModel"


    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _listGitUser = MutableLiveData<List<GitUser>>()
    val listGitUser: LiveData<List<GitUser>> = _listGitUser

    private val _Loading = MutableLiveData<Boolean>()
    val Loading: LiveData<Boolean> = _Loading

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status


    private val _totalCount = MutableLiveData<Int>()
    val totalCount: LiveData<Int> = _totalCount



    internal fun cariUser(query: String) {
        _Loading.value = true
        val UserClient = RetrofitUser.getApiService().cariUser(query)
        UserClient.enqueue(object : Callback<UserRes> {
            override fun onResponse(
                call: Call<UserRes>,
                response: Response<UserRes>
            ) {
                _Loading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listGitUser.value = response.body()?.gitUsers
                        Log.d("UserGit", "Data berhasil diambil: ${_listGitUser.value}")
                        _totalCount.value = response.body()?.totalCount
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Log.d(TAG, "API Gagal ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UserRes>, t: Throwable) {
                _Loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                _status.value = "Data failed to load, please try again."
            }
        })
    }

//    fun getThemeSettings(): LiveData<Boolean> {
//        return pref.getThemeSetting().asLiveData()
//    }




}