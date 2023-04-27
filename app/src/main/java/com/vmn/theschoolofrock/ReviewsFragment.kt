package com.vmn.theschoolofrock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmn.theschoolofrock.core.BaseFragment
import com.vmn.theschoolofrock.databinding.FragmentReviewsBinding
import com.vmn.theschoolofrock.viewmodel.ReviewsViewModel
import org.koin.android.ext.android.inject

class ReviewsFragment : BaseFragment() {

    private lateinit var binding: FragmentReviewsBinding

    private val reviewsViewModel by inject<ReviewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOps()
        observers()
    }

    private fun initOps() {
        binding.viewModel = reviewsViewModel
    }

    private fun observers() {

    }
}