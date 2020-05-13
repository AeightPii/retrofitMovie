package com.example.retrofitmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.retrofitmovies.R
import com.example.retrofitmovies.ui.adapter.MoviesAdapter
import com.example.retrofitmovies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private lateinit var movieAdapter: MoviesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = GridLayoutManager(context,2)
        movieAdapter = MoviesAdapter()
        recMovie.apply {
            adapter = movieAdapter
            layoutManager = viewManager
        }
        observeViewModel()
    }
    private fun observeViewModel(){
        movieViewModel= ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getResult().observe(viewLifecycleOwner, Observer {
                movieAdapter.updateList(it)
            }
        )
    }
    override fun onResume() {
        super.onResume()
        movieViewModel.loadResult()
    }
}
