package com.vrlocal.android.baseproject.ui.widgets.legoset.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.binding.bindImageFromUrl
import com.vrlocal.android.baseproject.databinding.FragmentLegoSetBinding
import com.vrlocal.android.baseproject.di.component.injectViewModel
import com.vrlocal.android.baseproject.ui.base.BaseFragment
import com.vrlocal.android.baseproject.ui.setTitle
import com.vrlocal.android.baseproject.ui.widgets.legoset.data.LegoSet
import com.vrlocal.android.baseproject.util.intentOpenWebsite
import com.vrlocal.android.baseproject.util.intentShareText
import javax.inject.Inject

/**
 * A fragment representing a single LegoSet detail screen.
 */
class LegoSetFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LegoSetViewModel

    private val args: LegoSetFragmentArgs by navArgs()
    private lateinit var set: LegoSet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.id = args.id

        val binding = DataBindingUtil.inflate<FragmentLegoSetBinding>(
            inflater, R.layout.fragment_lego_set, container, false
        ).apply {
            lifecycleOwner = this@LegoSetFragment
            fab.setOnClickListener { _ -> set.url?.let { intentOpenWebsite(activity!!, it) } }
        }

        subscribeUi(binding)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                intentShareText(
                    activity!!,
                    getString(R.string.share_lego_set, set.name, set.url ?: "")
                )
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(binding: FragmentLegoSetBinding) {
        viewModel.legoSet.observe(viewLifecycleOwner, Observer { result ->
            VResultHandler(this, result)

        })/*
        viewModel.legoSet.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                VResult.Status.SUCCESS -> {
                    hideProgressBar()
                    result.data?.let { bindView(binding, it) }

                }
                VResult.Status.LOADING -> showProgressBar()
                VResult.Status.ERROR -> {
                    hideProgressBar()
                    Snackbar.make(binding.coordinatorLayout, result.message!!, Snackbar.LENGTH_LONG)
                        .show()
                }
                VResult.Status.NOT_AUTHENTICATED,
                VResult.Status.AUTHENTICATED -> {
                }
            }
        })*/
    }

    private fun bindView(binding: FragmentLegoSetBinding, legoSet: LegoSet) {
        legoSet.apply {
            setTitle(name)
            bindImageFromUrl(binding.image, imageUrl)
            binding.name.text = name
            binding.year.text = getString(R.string.year, year ?: 0)
            binding.numParts.text = getString(R.string.number_of_parts, numParts ?: 0)
            set = legoSet
        }
    }

}
