//package com.example.instikapp.model
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.instikapp.ui.activity.FavoriteVIewModel
//import com.example.practicefundamental.main.DetailViewModel
//
//class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory(){
//    companion object {
//        @Volatile
//        private var INSTANCE: ViewModelFactory? = null
//
//        @JvmStatic
//        fun getInstance(application: Application): ViewModelFactory {
//            if (INSTANCE == null) {
//                synchronized(ViewModelFactory::class.java) {
//                    INSTANCE = ViewModelFactory(application)
//                }
//            }
//            return INSTANCE as ViewModelFactory
//        }
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
//            return DetailViewModel(mApplication) as T
//        } else if (modelClass.isAssignableFrom(FavoriteVIewModel::class.java)) {
//            return FavoriteVIewModel(mApplication) as T
//        }
//        throw IllegalArgumentException("Uknown ViewModel class: ${modelClass.name}")
//    }
//}