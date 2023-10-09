package com.example.instikapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instikapp.R
import com.example.instikapp.databinding.ActivityFavoriteBinding
//import com.example.instikapp.model.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteBinding: ActivityFavoriteBinding
    private lateinit var  viewModel: FavoriteVIewModel
//    private lateinit var adapter: FavAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
    setContentView(favoriteBinding.root)
    setContentView(R.layout.activity_favorite)

    supportActionBar?.title = getString(R.string.title_activity_favorite)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}


//        viewModel = obtainViewModel(this@FavoriteActivity)
//        viewModel.getAllFavorites().observe(this, { favoriteList ->
//            if (favoriteList != null) {
//                adapter.setFavorites(favoriteList)
//            }
//        })
//        adapter = FavAdapter()
//        favoriteBinding?.rvFavorites?.layoutManager = LinearLayoutManager(this)
//        favoriteBinding?.rvFavorites?.setHasFixedSize(false)
//        favoriteBinding?.rvFavorites?.adapter = adapter
//    }
//
//    /**
//     * Function to return View Model Factory
//     */
//    private fun obtainViewModel(activity: AppCompatActivity): FavoriteVIewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(FavoriteVIewModel::class.java)
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        favoriteBinding = null
//    }
}