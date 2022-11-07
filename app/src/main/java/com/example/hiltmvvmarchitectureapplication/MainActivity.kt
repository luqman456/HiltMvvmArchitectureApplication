package com.example.hiltmvvmarchitectureapplication

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hiltmvvmarchitectureapplication.base.BaseActivity
import com.example.hiltmvvmarchitectureapplication.databinding.ActivityMainBinding
import com.example.hiltmvvmarchitectureapplication.dialog.FragmentDeleteDialog
import com.example.hiltmvvmarchitectureapplication.mvvm.viewmodel.MovieViewModel
import com.example.hiltmvvmarchitectureapplication.others.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), FragmentDeleteDialog.DeleteListener {

    private lateinit var movieViewModel: MovieViewModel
    private var deleteDialog: FragmentDeleteDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.clickHandler = this
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        observeViewMovies()

    }


    private fun observeViewMovies() {

        movieViewModel.getMovies.observe(this, Observer {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        dataBinding.progressBar.visibility = View.GONE
                        if (result.data?.movies?.isEmpty() != true) {
                            show("Data Loaded Successfully")
                        }
                    }
                    Status.ERROR -> {
                        dataBinding.progressBar.visibility = View.GONE
                        show("Error")
                    }
                    Status.LOADING -> {
                        dataBinding.progressBar.visibility = View.VISIBLE
                    }

                }
            }
        })

    }

    private fun showDeleteDialogFragment() {
        deleteDialog = FragmentDeleteDialog()
        deleteDialog!!.show(supportFragmentManager, "DeleteFragment")
        deleteDialog!!.setListener(this)
    }

    private fun closeDeleteDialogFragment() {
        if (deleteDialog != null) {
            deleteDialog!!.dismiss()
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {
                movieViewModel.getMoviesList()
            }
            R.id.button2 -> {
                showDeleteDialogFragment()
            }
        }
    }

    override fun deleteDismissDialog(isCallApi: Boolean) {
        closeDeleteDialogFragment()
    }
}