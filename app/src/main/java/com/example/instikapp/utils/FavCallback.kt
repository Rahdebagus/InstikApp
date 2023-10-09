//package com.example.instikapp.utils
//
//import androidx.recyclerview.widget.DiffUtil
//import com.example.instikapp.model.Favorite
//
//class FavCallback(private val mOldFavList: List<Favorite>, private val mNewFavList: List<Favorite>):
//    DiffUtil.Callback() {
//        override fun getOldListSize(): Int {
//            return mOldFavList.size
//        }
//
//        override fun getNewListSize(): Int {
//            return mNewFavList.size
//        }
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return mOldFavList[oldItemPosition].id == mNewFavList[newItemPosition].id
//        }
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            val oldFavList = mOldFavList[oldItemPosition]
//            val newFavList = mNewFavList[newItemPosition]
//            return oldFavList.login == newFavList.login && oldFavList.htmlUrl == newFavList.htmlUrl && oldFavList.avatarUrl == newFavList.avatarUrl
//        }
//}