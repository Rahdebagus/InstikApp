package com.example.instikapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.githubuserapplication.adapter.FollowersAdapter
import com.bumptech.glide.Glide
import com.example.instikapp.databinding.ItemRowsBinding
import com.example.instikapp.model.GitUser

class FollowingAdapter (private val listFollower: List<GitUser>) : RecyclerView.Adapter<FollowersAdapter.ViewHolder>(){
    class ViewHolder(var binding: ItemRowsBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersAdapter.ViewHolder {
        val binding = ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = listFollower.size

    override fun onBindViewHolder(holder: FollowersAdapter.ViewHolder, position: Int) {
        val following = listFollower[position]
        with(holder.binding) {
            com.bumptech.glide.Glide.with(root.context)
                .load(following.avatarUrl)
                .circleCrop()
                .into(imgUserAvatar)
            tvName.text = following.login
            tvUrl.text = following.htmlUrl
        }
    }
}