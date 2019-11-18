

package com.vrlocal.android.baseproject.ui.screens.legotheme.ui

import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.base.IView
import com.vrlocal.android.baseproject.ui.screens.legotheme.data.LegoThemeRepository
import javax.inject.Inject

/**
 * The ViewModel for [LegoThemeFragment].
 */
class LegoThemeViewModel @Inject constructor(repository: LegoThemeRepository) : BaseViewModel<IView>() {

    val legoThemes= repository.themes
}
