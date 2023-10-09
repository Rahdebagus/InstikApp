//package com.example.instikapp.ui.activity
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.instikapp.databinding.ItemRowsBinding
//import com.example.instikapp.model.Favorite
//import com.example.instikapp.utils.FavCallback
//
//class FavAdapter : RecyclerView.Adapter<FavAdapter.FavoriteUserViewHolder>(){
//    private val listFavorites = ArrayList<Favorite>()
//
//    fun setFavorites(favorites: List<Favorite>) {
//        val diffCallback = FavCallback(this.listFavorites, favorites)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        this.listFavorites.clear()
//        this.listFavorites.addAll(favorites)
//        diffResult.dispatchUpdatesTo(this)
//    }
//
//    class FavoriteUserViewHolder(private val binding: ItemRowsBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(favorites: Favorite) {
//            with(binding) {
//                tvName.text = favorites.login
//                tvUrl.text = favorites.htmlUrl
//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailActivity::class.java)
//                    intent.putExtra(DetailActivity.EXTRA_USER, favorites.login)
//                    itemView.context.startActivity(intent)
//                }
//            }
//            Glide.with(itemView.context)
//                .load(favorites.avatarUrl)
//                .circleCrop()
//                .into(binding.imgUserAvatar)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
//        val itemRowUserBinding = ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return FavoriteUserViewHolder(itemRowUserBinding)
//    }
//
//    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
//        val favorites = listFavorites[position]
//        holder.bind(favorites)
//    }
//
//    override fun getItemCount(): Int = listFavorites.size
//}