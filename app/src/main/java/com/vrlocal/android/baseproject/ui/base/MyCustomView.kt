package com.vrlocal.android.baseproject.ui.base
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.View
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.vrlocal.android.baseproject.R
//import timber.log.Timber
//
//class MyCustomView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : ConstraintLayout(context, attrs, defStyleAttr),
//    MyViewModelAccessor by MyViewModelInjector(context) {
//
//    init {
//        View.inflate(context, R.layout.view_custom, this)
//        subscribe()
//    }
//
//    private fun subscribe() {
//        viewModel.property.observe(activity, Observer {
//            Timber.d("Property udpated from ViewModel: %s", it)
//        })
//    }
//}