package com.example.mymoviesapp.presentation.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviesapp.R
import com.example.mymoviesapp.data.room.UserEntity
import com.example.mymoviesapp.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) { backPressed() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVM()
        initObserver()
        configRecycler()
        binding?.btnGoMovies?.setOnClickListener {
            goToMovies()
        }
    }

    private fun goToMovies() {
        findNavController().navigate(R.id.moviesFragment)
    }

    private fun backPressed() {
        requireActivity().finish()
    }

    private fun configRecycler() {
        binding?.recyclerUser?.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun initObserver() {
        userViewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is StateUser.Success -> renderView(it.users)
                is StateUser.Error -> showError()
            }
        })
    }

    private fun showError() {

    }

    private fun renderView(users: List<UserEntity>) {
        binding?.recyclerUser?.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )
        binding?.recyclerUser?.adapter = UserAdapter(requireContext(), users)
    }

    private fun initVM() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }


}