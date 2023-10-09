package com.app.githubuserapplication.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.instikapp.databinding.ItemRowsBinding
import com.example.instikapp.model.GitUser

class SearchAdapter(private val listUser: List<GitUser>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .circleCrop()
            .into(this)
    }

    class ViewHolder(var Rowbinding: ItemRowsBinding) : RecyclerView.ViewHolder(Rowbinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]

        with(holder.Rowbinding) {
            imgUserAvatar.loadImage(user.avatarUrl)
            tvName.text = user.login
            tvUrl.text = user.htmlUrl
            root.setOnClickListener { onItemClickCallback.onItemClicked(listUser[position]) }
        }
    }

    override fun getItemCount(): Int = listUser.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GitUser)
    }
}