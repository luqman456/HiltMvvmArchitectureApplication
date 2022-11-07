package com.example.hiltmvvmarchitectureapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.hiltmvvmarchitectureapplication.base.BaseFragment
import com.example.hiltmvvmarchitectureapplication.databinding.FragmentTestingBinding
import com.example.hiltmvvmarchitectureapplication.mvvm.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class TestingFragment : BaseFragment<FragmentTestingBinding>(), LifecycleOwner {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }


    override fun OnCreateView(inflater: LayoutInflater?, savedInstanceState: Bundle?) {
        dataBinding.clickHandler = this
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_testing
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {
                movieViewModel.getMoviesList()
            }
        }
    }
}