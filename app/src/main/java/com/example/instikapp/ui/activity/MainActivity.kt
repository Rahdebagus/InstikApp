package com.example.instikapp.ui.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instikapp.databinding.ActivityMainBinding
import com.example.instikapp.ui.activity.MainViewModel
import androidx.lifecycle.ViewModel
import com.app.githubuserapplication.view.main.SearchAdapter
import com.example.instikapp.R
import com.example.instikapp.model.GitUser


class MainActivity : AppCompatActivity() {
    private  var MainBinding: ActivityMainBinding? =null
    private val binding get() = MainBinding
    private lateinit var mainViewModel: MainViewModel
    private var listGitUser = ArrayList<GitUser>()
    private lateinit var adapter: SearchAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainBinding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Live data observe
        mainViewModel.listGitUser.observe(this) { gitUserList ->
            listGitUser.clear()
            listGitUser.addAll(gitUserList)
            adapter.notifyDataSetChanged()
        }
        mainViewModel.Loading.observe(this) { isLoading ->
            if (isLoading) {
                binding?.progressBar?.visibility = View.VISIBLE
            } else {
                binding?.progressBar?.visibility = View.GONE
            }
        }
        mainViewModel.totalCount.observe(this) { totalCount ->
            showText(totalCount)
        }
        //End  Live data observe

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding?.searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchUsername()

        // Set recyclerview
        val layoutManager = LinearLayoutManager(this@MainActivity)
        binding?.rvUser?.layoutManager = layoutManager
        adapter = SearchAdapter(listGitUser)
        binding?.rvUser?.adapter = adapter
        showText(listGitUser.size)
        adapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: GitUser) {
            showSelectedUser(data)
            }
        })
        // End Set recyclerview
    }

    //fun cari nama
    private fun searchUsername() {
        MainBinding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    binding?.rvUser?.visibility = View.VISIBLE
                    mainViewModel.cariUser(it)
                }
                hideKeyboard()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    //Sembunyikan Keyboard
        private fun hideKeyboard() {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(MainBinding?.rvUser?.windowToken, 0)
        }

    //Select user membawa data
    private fun showSelectedUser(data: GitUser) {
    val moveWithParcelableIntent = Intent(this@MainActivity, DetailActivity::class.java)
    moveWithParcelableIntent.putExtra(DetailActivity.EXTRA_USER, data)
    startActivity(moveWithParcelableIntent)
    }

    //show Log/text ketika data 0 tampilkan text
    private fun showText(totalCount: Int) {
        binding?.apply {
            if (totalCount == 0) {
                tvLog.visibility = View.VISIBLE
                tvLog.text = resources.getString(R.string.user_not_found)
            } else {
                tvLog.visibility = View.INVISIBLE
                Toast.makeText(this@MainActivity, "User Ditemukan ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    }
