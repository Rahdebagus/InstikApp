package com.app.githubuserapplication.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.instikapp.api.RetrofitUser
import com.example.instikapp.model.GitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    private val _listFollower = MutableLiveData<List<GitUser>>()
    val listFollower: LiveData<List<GitUser>> = _listFollower

    private val _Loading = MutableLiveData<Boolean>()
    val Loading: LiveData<Boolean> = _Loading

    companion object {
        private const val TAG = "FollowerViewModel"
    }

    internal fun getFollower(usernamefollowers: String) {
        _Loading.value = true
        val FollowersClient = RetrofitUser.getApiService().getUserFollowers(usernamefollowers)
        FollowersClient.enqueue(object : Callback<List<GitUser>> {
            override fun onResponse(
                call: Call<List<GitUser>>,
                response: Response<List<GitUser>>
            ) {
                _Loading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listFollower.value = response.body()
                        Log.d("UserGit", "Data Follower berhasil diambil: ${_listFollower.value}")
                    }
                    else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<GitUser>>, t: Throwable) {
                _Loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}