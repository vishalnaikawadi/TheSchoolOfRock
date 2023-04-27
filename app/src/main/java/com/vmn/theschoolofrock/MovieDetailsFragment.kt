package com.vmn.theschoolofrock

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.vmn.domain.utils.Resource
import com.vmn.theschoolofrock.adapter.MovieCastAdapter
import com.vmn.theschoolofrock.adapter.SimilarMovieAdapter
import com.vmn.theschoolofrock.core.BaseFragment
import com.vmn.theschoolofrock.databinding.FragmentMovieDetailsBinding
import com.vmn.theschoolofrock.utils.Constants.VALID_DEPARTMENT
import com.vmn.theschoolofrock.viewmodel.MovieDetailsViewModel
import org.koin.android.ext.android.inject

class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val movieDetailsViewModel by inject<MovieDetailsViewModel>()
    private val movieCastAdapter = MovieCastAdapter()
    private val similarMovieAdapter = SimilarMovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOps()
        observers()
        listeners()
    }

    private fun listeners() {

        onBackPressed {
            showBottomNavigationView()
            findNavController().navigateUp()
        }

        binding.lytReviews.setOnClickListener {
            val action =
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToReviewsFragment()
            findNavController().navigate(action)
        }

    }

    private fun initOps() {
        hideBottomNavigationView()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move)
        binding.viewModel = movieDetailsViewModel
        movieDetailsViewModel.commonResult.set(args.movieCommon)
        binding.executePendingBindings()

        movieDetailsViewModel.getMovieDetails()
        movieDetailsViewModel.getCredits()
        movieDetailsViewModel.getSimilarMovies()

        binding.rvCast.apply {
            adapter = movieCastAdapter
        }

        binding.rvSimilarMovies.apply {
            adapter = similarMovieAdapter
        }

    }

    private fun observers() {

        movieDetailsViewModel.credits.observe(viewLifecycleOwner) {
            it?.let { res ->

                when (res) {
                    is Resource.Success -> {
                        movieCastAdapter.updateData(res.data?.cast?.filter { cast ->
                            cast.knownForDepartment == VALID_DEPARTMENT
                        })
                    }

                    is Resource.Error -> {
                        showShortMessage(res.errorMessage)
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

        movieDetailsViewModel.similarMovies.observe(viewLifecycleOwner) {
            it?.let { res ->

                when (res) {
                    is Resource.Success -> {
                        similarMovieAdapter.updateData(res.data?.results.orEmpty())
                    }

                    is Resource.Error -> {
                        showShortMessage(res.errorMessage)
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


}