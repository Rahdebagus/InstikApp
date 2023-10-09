package com.example.instikapp.api

import com.app.githubuserapplication.model.DetailRes
import com.example.instikapp.model.GitUser
import com.example.instikapp.model.UserRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun cariUser(
        @Query("q") login: String
    ): Call<UserRes>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ):Call<DetailRes>

    @GET("users/{login}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(
        @Path("login") login: String
    ): Call<List<GitUser>>

    @GET("users/{login}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("login") login: String
    ): Call<List<GitUser>>

}