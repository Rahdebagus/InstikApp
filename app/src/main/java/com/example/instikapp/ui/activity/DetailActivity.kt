package com.example.instikapp.ui.activity

import Helper
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.app.githubuserapplication.model.DetailRes
import com.bumptech.glide.Glide
import com.example.instikapp.R
import com.example.instikapp.databinding.ActivityDetailBinding
import com.example.instikapp.model.GitUser
import com.example.instikapp.model.UserRes
import com.example.instikapp.ui.adapter.TabsAdapter
import com.example.practicefundamental.main.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FRAGMENT = "extra_fragment"
    }
    private lateinit var DetailActivityBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailActivityBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(DetailActivityBinding.root)


        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        // Observer untuk mengamati perubahan dalam LiveData
        detailViewModel.detail.observe(this) { detail  ->
            detail?.let {
                setDataToView(detail)

            }
        }

        detailViewModel.Loading.observe(this) { isLoading ->
            if (isLoading) {
                // Show a loading indicator (e.g., ProgressBar)
                DetailActivityBinding?.progressBar2?.visibility = View.VISIBLE
            } else {
                // Hide the loading indicator (e.g., ProgressBar)
                DetailActivityBinding?.progressBar2?.visibility = View.GONE
            }
        }
    setTabLayoutView()


    }

    private fun setTabLayoutView() {
        val userIntent = intent.getParcelableExtra<GitUser>(EXTRA_USER) as GitUser
        detailViewModel.getdetail(userIntent.login)
        val login = Bundle()
        login.putString(EXTRA_FRAGMENT, userIntent.login)


        val sectionPagerAdapter = TabsAdapter(this, login)
        val viewPager: ViewPager2 = DetailActivityBinding.viewPager

        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = DetailActivityBinding.tabs
        val tabTitle = resources.getStringArray(R.array.tab_title)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

    }



    private fun setDataToView(detailList: DetailRes) {
        DetailActivityBinding.apply {
            Glide.with(this@DetailActivity)
                .load(detailList.avatarUrl)
                .circleCrop()
                .into(idIvProfile)
            ivName.text = detailList.login ?: "No Name."
            ivUsername.text = detailList.name ?: "No Name."
            tvFollowers.text =  detailList.followers.toString()
            tvFollowing.text = detailList.following.toString()
            tvPhoto.text = detailList.publicRepos.toString()

        }
    }
}