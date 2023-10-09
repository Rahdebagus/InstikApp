//package com.example.instikapp.model
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface FavoriteDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertFavorite(user: Favorite)
//
//    @Query("DELETE FROM Favorite WHERE Favorite.id = :id")
//    fun removeFavorite(id: Int)
//
//    @Query("SELECT * FROM Favorite ORDER BY login ASC")
//    fun getAllUser(): LiveData<List<Favorite>>
//
//    @Query("SELECT * FROM Favorite WHERE Favorite.id = :id")
//    fun getUserById(id: Int): LiveData<Favorite>
//
//}