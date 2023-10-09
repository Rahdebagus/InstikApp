package com.example.instikapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.githubuserapplication.adapter.FollowersAdapter

import com.example.instikapp.R
import com.example.instikapp.databinding.FragmentFollowingBinding
import com.example.instikapp.model.GitUser
import com.example.instikapp.ui.activity.DetailActivity
import java.security.Provider


class FollowingFragment : Fragment() {

    private var _bindingFollowing : FragmentFollowingBinding? = null
    private val bindingFollowing get()=_bindingFollowing!!
    private lateinit var followingViewModel: FollowingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        followingViewModel  = ViewModelProvider(this , ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingFollowing = FragmentFollowingBinding.inflate(inflater,container,false)
        return  bindingFollowing.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        followerViewModel.isLoading.observe(viewLifecycleOwner, {
//            helper.showLoading(it, binding.progressBar3)
//        })

        followingViewModel.listFollowing.observe(viewLifecycleOwner, { listFollower ->
            setDataToFragment(listFollower)
        })

        followingViewModel.Loading.observe(viewLifecycleOwner, { isLoading->
            if (isLoading) {
                // Show a loading indicator (e.g., ProgressBar)
                _bindingFollowing?.progressFollowing?.visibility = View.VISIBLE
            } else {
                // Hide the loading indicator (e.g., ProgressBar)
                _bindingFollowing?.progressFollowing?.visibility = View.GONE
            }
        })




        followingViewModel.getFollowing(arguments?.getString(DetailActivity.EXTRA_FRAGMENT).toString())
    }

    private fun setDataToFragment(listFollowing: List<GitUser>) {
        val listUser = ArrayList<GitUser>()
        with(bindingFollowing) {
            for (user in listFollowing) {
                listUser.clear()
                listUser.addAll(listFollowing)
            }
            rvFollower.layoutManager = LinearLayoutManager(context)
            val adapter = FollowersAdapter(listFollowing)
            rvFollower.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindingFollowing = null
    }

}

