package com.example.mymoviesapp.presentation.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymoviesapp.databinding.FragmentMoviesBinding
import com.example.mymoviesapp.domain.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {


    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVM()
        initObserver()
        configRecycler()
    }

    private fun configRecycler() {
        binding?.recyclerMovies?.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    private fun initObserver() {
        moviesViewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is StateMovies.SuccessMovies -> renderView(state.movies)
                is StateMovies.Error -> showError()
            }
        })
    }

    private fun showError() {}

    private fun renderView(list: List<Movie>) {
        binding?.recyclerMovies?.adapter = MoviesAdapter(requireActivity(), list)
        binding?.progressBar?.visibility = View.INVISIBLE
    }

    private fun initVM() {
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}