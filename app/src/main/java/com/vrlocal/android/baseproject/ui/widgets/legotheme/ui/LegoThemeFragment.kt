package com.vrlocal.android.baseproject.ui.widgets.legotheme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.data.Result
import com.vrlocal.android.baseproject.databinding.FragmentThemesBinding
import com.vrlocal.android.baseproject.di.injectViewModel
import com.vrlocal.android.baseproject.ui.VerticalItemDecoration
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LegoThemeFragment : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LegoThemeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        val binding = FragmentThemesBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = LegoThemeAdapter()
        binding.recyclerView.addItemDecoration(
                VerticalItemDecoration(resources.getDimension(R.dimen.margin_normal).toInt(), true) )
        binding.recyclerView.adapter = adapter

        subscribeUi(binding, adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentThemesBinding, adapter: LegoThemeAdapter) {
        viewModel.legoThemes.removeObservers(viewLifecycleOwner)
        viewModel.legoThemes.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    (activity as BaseActivity<*, *>).hideProgressBar()
                    result.data?.let { adapter.submitList(it) }
                }
                Result.Status.LOADING -> {
                    (activity as BaseActivity<*, *>).showProgressBar()
                }
                Result.Status.ERROR -> {
                    (activity as BaseActivity<*, *>).hideProgressBar()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}