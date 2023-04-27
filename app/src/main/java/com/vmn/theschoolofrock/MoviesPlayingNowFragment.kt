package com.vmn.theschoolofrock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.vmn.domain.utils.Resource
import com.vmn.theschoolofrock.adapter.MoviePlayingNowAdapter
import com.vmn.theschoolofrock.core.BaseAdapter
import com.vmn.theschoolofrock.core.BaseFragment
import com.vmn.theschoolofrock.databinding.FragmentMoviesPlayingNowBinding
import com.vmn.theschoolofrock.model.ResultCommon
import com.vmn.theschoolofrock.utils.CenterZoomLayoutManger
import com.vmn.theschoolofrock.viewmodel.MoviesPlayingNowViewModel
import org.koin.android.ext.android.inject


class MoviesPlayingNowFragment : BaseFragment(), BaseAdapter.OnLoadMoreListener {

    private lateinit var binding: FragmentMoviesPlayingNowBinding

    private val viewModel by inject<MoviesPlayingNowViewModel>()

    private val moviesPlayingAdapter = MoviePlayingNowAdapter { item, poster, title ->

        val common = ResultCommon(item.id, item.title, item.posterPath)

        val action =
            MoviesPlayingNowFragmentDirections.actionMoviesPlayingNowFragmentToMovieDetailsFragment(
                common
            )

        findNavController()
            .navigate(
                action,
                FragmentNavigator.Extras.Builder()
                    .addSharedElements(
                        mapOf(
                            poster to poster.transitionName,
                            title to title.transitionName,
                        )
                    ).build()
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesPlayingNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOps()
        observers()
        listeners()
    }


    private fun initOps() {
        binding.viewModel = viewModel
        viewModel.getListMoviesNowPlaying()
        binding.rvMovies.apply {
            layoutManager = CenterZoomLayoutManger(context, RecyclerView.HORIZONTAL, false)
            LinearSnapHelper().attachToRecyclerView(this)
            adapter = moviesPlayingAdapter
            moviesPlayingAdapter.enableLoadMore(this@MoviesPlayingNowFragment)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

    private fun observers() {

        viewModel.moviesPlaying.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    is Resource.Success -> {
                        moviesPlayingAdapter.updateData(viewModel.moviePlayingResults)
                    }

                    is Resource.Error -> {
                        showShortMessage(it.errorMessage)
                    }

                    Resource.NoInternet -> {
                        showShortMessage(getString(R.string.no_internet))
                    }

                    Resource.Unknown -> {
                        showShortMessage(getString(R.string.error_unknown))
                    }
                }
            }
        }
    }

    private fun listeners() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    moviesPlayingAdapter.filterData(newText, viewModel.moviePlayingResults)
                } else {
                    moviesPlayingAdapter.updateDataAfterClear(viewModel.moviePlayingResults)
                }

                return true
            }
        })
    }

    override fun onLoadMore() {
        if (viewModel.isNextPageAvailable()) {
            viewModel.getListMoviesNowPlaying()
        }
    }

}