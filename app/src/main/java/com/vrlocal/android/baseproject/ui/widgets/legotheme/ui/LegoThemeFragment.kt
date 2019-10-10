package com.vrlocal.android.baseproject.ui.widgets.legotheme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.databinding.FragmentThemesBinding
import com.vrlocal.android.baseproject.di.component.injectViewModel
import com.vrlocal.android.baseproject.ui.VerticalItemDecoration
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.base.BaseFragment
import javax.inject.Inject

class LegoThemeFragment : BaseFragment() {

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
                VResult.Status.SUCCESS -> {
                    (activity as BaseActivity<*, *>).hideProgressBar()
                    result.data?.let { adapter.submitList(it) }
                }
                VResult.Status.LOADING -> {
                    (activity as BaseActivity<*, *>).showProgressBar()
                }
                VResult.Status.ERROR -> {
                    (activity as BaseActivity<*, *>).hideProgressBar()
                    result.message?.let { showError(it) }


                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }

                VResult.Status.NOT_AUTHENTICATED,
                VResult.Status.AUTHENTICATED->{}
            }
        })
    }
}