package com.vrlocal.android.baseproject.ui.widgets.legoset.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.databinding.FragmentLegosetsBinding
import com.vrlocal.android.baseproject.di.component.injectViewModel
import com.vrlocal.android.baseproject.ui.GridSpacingItemDecoration
import com.vrlocal.android.baseproject.ui.VerticalItemDecoration
import com.vrlocal.android.baseproject.ui.base.BaseFragment
import com.vrlocal.android.baseproject.ui.setTitle
import com.vrlocal.android.baseproject.ui.widgets.login.LoginActivity
import com.vrlocal.android.baseproject.ui.widgets.login.data.UserDao
import com.vrlocal.android.baseproject.util.ConnectivityUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class LegoSetsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LegoSetsViewModel
    @Inject
    lateinit var userDao: UserDao;
    private val args: LegoSetsFragmentArgs by navArgs()

    private lateinit var binding: FragmentLegosetsBinding
    private val adapter: LegoSetAdapter by lazy { LegoSetAdapter() }
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var gridLayoutManager: GridLayoutManager
    private val linearDecoration: RecyclerView.ItemDecoration by lazy {
        VerticalItemDecoration(
            resources.getDimension(R.dimen.margin_normal).toInt()
        )
    }
    private val gridDecoration: RecyclerView.ItemDecoration by lazy {
        GridSpacingItemDecoration(
            SPAN_COUNT, resources.getDimension(R.dimen.margin_grid).toInt()
        )
    }

    private var isLinearLayoutManager: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected()
        viewModel.themeId = if (args.themeId == -1) null else args.themeId

        binding = FragmentLegosetsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        linearLayoutManager = LinearLayoutManager(activity)
        gridLayoutManager = GridLayoutManager(activity, SPAN_COUNT)
        setLayoutManager()
        binding.recyclerView.adapter = adapter

        args.themeName?.let { setTitle(it) }
        subscribeUi(adapter)
        showProgressBar()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_representation, menu)
        setDataRepresentationIcon(menu.findItem(R.id.list))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list -> {
                isLinearLayoutManager = !isLinearLayoutManager
                setDataRepresentationIcon(item)
                setLayoutManager()
                true
            }
            R.id.logout->{
                viewModel.logoutUser.removeObservers(viewLifecycleOwner)
                viewModel.logoutUser.observe(viewLifecycleOwner){
                    if (it.status==VResult.Status.SUCCESS){
                        GlobalScope.async {
                            userDao.removeUser(it.data!!)
                        }
                        navigateLoginScreen()

                    }
                }

                return  true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateLoginScreen() {

        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
//        finishAffinity()

    }

    private fun subscribeUi(adapter: LegoSetAdapter) {
        viewModel.legoSets.observe(viewLifecycleOwner) {
            //            val bindingBaseActivity = activity as BaseActivity

            hideProgressBar()
            adapter.submitList(it)
        }
    }

    private fun setLayoutManager() {
        val recyclerView = binding.recyclerView

        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        if (isLinearLayoutManager) {
            recyclerView.removeItemDecoration(gridDecoration)
            recyclerView.addItemDecoration(linearDecoration)
            recyclerView.layoutManager = linearLayoutManager
        } else {
            recyclerView.removeItemDecoration(linearDecoration)
            recyclerView.addItemDecoration(gridDecoration)
            recyclerView.layoutManager = gridLayoutManager
        }

        recyclerView.scrollToPosition(scrollPosition)
    }

    private fun setDataRepresentationIcon(item: MenuItem) {
        item.setIcon(
            if (isLinearLayoutManager)
                R.drawable.ic_grid_list_24dp else R.drawable.ic_list_white_24dp
        )
    }

    companion object {
        const val SPAN_COUNT = 3
    }

}
