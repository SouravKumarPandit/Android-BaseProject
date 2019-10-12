package com.vrlocal.android.baseproject.ui.widgets.legoset.ui

import com.vrlocal.android.baseproject.di.CoroutineScropeIO
import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.base.IView
import com.vrlocal.android.baseproject.ui.widgets.legoset.data.LegoSetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * The ViewModel for [LegoSetsFragment].
 */
class LegoSetsViewModel @Inject constructor(private val repository: LegoSetRepository,
                                            @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope)
    : BaseViewModel<IView>() {

    var connectivityAvailable: Boolean = false
    var themeId: Int? = null

    val legoSets by lazy {
        repository.observePagedSets(
                connectivityAvailable, themeId, ioCoroutineScope)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }

    val logoutUser by lazy  {
        repository.logoutUser()
    }
}
