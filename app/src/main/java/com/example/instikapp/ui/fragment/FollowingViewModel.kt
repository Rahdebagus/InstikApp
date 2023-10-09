package com.example.instikapp.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instikapp.api.RetrofitUser
import com.example.instikapp.model.GitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag

class FollowingViewModel :ViewModel() {
    private val _listFollowing = MutableLiveData<List<GitUser>>()
    val listFollowing : LiveData<List<GitUser>> = _listFollowing


    private val _Loading = MutableLiveData<Boolean>()
    val Loading : LiveData<Boolean> = _Loading

    companion object {
        private const val TAG = "FollowingFragment"
    }

    internal fun getFollowing(userFollowing : String){
        _Loading.value = true
        val FollwoingClient = RetrofitUser.getApiService().getUserFollowing(userFollowing)
        FollwoingClient.enqueue(object : Callback<List<GitUser>>{
            override fun onResponse(call: Call<List<GitUser>>, response: Response<List<GitUser>>) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody !=null){
                        _Loading.value = false
                        _listFollowing.value = response.body()
                        Log.d(TAG, "Data Following berhasil di ambil : ${_listFollowing}" )
                    }
                    else{
                        Log.d(TAG, "Data Following gagal : ${_listFollowing}" )
                    }
                }
            }

            override fun onFailure(call: Call<List<GitUser>>, t: Throwable) {
                Log.d(TAG, "Api gagal : ${_listFollowing}" )
            }

        })
    }




}