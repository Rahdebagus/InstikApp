package com.example.instikapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
    data class UserRes(
        @field:SerializedName("total_count")
        val totalCount: Int,

        @field:SerializedName("items")
        val gitUsers: List<GitUser>
    ):Parcelable

@Parcelize
    data class GitUser(
        @field:SerializedName("avatar_url")
        val avatarUrl: String,

        @field:SerializedName("html_url")
        val htmlUrl: String,

        @field:SerializedName("login")
        val login: String,
    ):Parcelable

