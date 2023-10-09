package com.app.githubuserapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instikapp.databinding.ItemRowsBinding
import com.example.instikapp.model.GitUser

class FollowersAdapter(private val listFollower: List<GitUser>) : RecyclerView.Adapter<FollowersAdapter.ViewHolder>(){
    class ViewHolder(var binding: ItemRowsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val follower = listFollower[position]

        with(holder.binding) {
            Glide.with(root.context)
                .load(follower.avatarUrl)
                .circleCrop()
                .into(imgUserAvatar)
            tvName.text = follower.login
            tvUrl.text = follower.htmlUrl
        }
    }

    override fun getItemCount(): Int = listFollower.size
}