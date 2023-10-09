package com.app.githubuserapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.githubuserapplication.adapter.FollowersAdapter
import com.app.githubuserapplication.ui.viewmodels.FollowersViewModel
import com.example.instikapp.databinding.FragmentFollowersBinding
import com.example.instikapp.model.GitUser
import com.example.instikapp.ui.activity.DetailActivity


class FollowersFragment : Fragment() {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerViewModel: FollowersViewModel
//    private val helper = Helper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followerViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        followerViewModel.isLoading.observe(viewLifecycleOwner, {
//            helper.showLoading(it, binding.progressBar3)
//        })

        followerViewModel.listFollower.observe(viewLifecycleOwner, { listFollower ->
            setDataToFragment(listFollower)
        })

        followerViewModel.Loading.observe(viewLifecycleOwner, { isLoading->
            if (isLoading) {
                // Show a loading indicator (e.g., ProgressBar)
                _binding?.progressBarFollowers?.visibility = View.VISIBLE
            } else {
                // Hide the loading indicator (e.g., ProgressBar)
                _binding?.progressBarFollowers?.visibility = View.GONE
            }
        })




        followerViewModel.getFollower(arguments?.getString(DetailActivity.EXTRA_FRAGMENT).toString())
    }

    private fun setDataToFragment(listFollower: List<GitUser>) {
        val listUser = ArrayList<GitUser>()
        with(binding) {
            for (user in listFollower) {
                listUser.clear()
                listUser.addAll(listFollower)
            }
            rvFollower.layoutManager = LinearLayoutManager(context)
            val adapter = FollowersAdapter(listFollower)
            rvFollower.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}