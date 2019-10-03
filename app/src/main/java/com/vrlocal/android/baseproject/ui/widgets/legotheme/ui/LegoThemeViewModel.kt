

package com.vrlocal.android.baseproject.ui.widgets.legotheme.ui

import androidx.lifecycle.ViewModel
import com.vrlocal.android.baseproject.ui.widgets.legotheme.data.LegoThemeRepository
import javax.inject.Inject

/**
 * The ViewModel for [LegoThemeFragment].
 */
class LegoThemeViewModel @Inject constructor(repository: LegoThemeRepository) : ViewModel() {

    val legoThemes= repository.themes
}
