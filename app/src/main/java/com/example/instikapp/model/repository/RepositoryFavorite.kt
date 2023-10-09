//package com.example.instikapp.model.repository
//
//import androidx.lifecycle.LiveData
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//import android.app.Application
//import androidx.room.Database
//import com.example.instikapp.model.Favorite
//import com.example.instikapp.model.FavoriteDao
//import com.example.instikapp.model.FavoriteRoomDatabase
//
//@Database(entities = [Favorite::class], version = 5)
//class RepositoryFavorite(application: Application) {
//    private val mFavoriteDao: FavoriteDao
//    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
//
//    init {
//        val db = FavoriteRoomDatabase.getDatabase(application)
//        mFavoriteDao = db.favoriteDao()
//    }
//
//    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteDao.getAllUser()
//
//    fun insert(user: Favorite) {
//        executorService.execute { mFavoriteDao.insertFavorite(user) }
//    }
//
//    fun delete(id: Int) {
//        executorService.execute { mFavoriteDao.removeFavorite(id) }
//    }
//}
