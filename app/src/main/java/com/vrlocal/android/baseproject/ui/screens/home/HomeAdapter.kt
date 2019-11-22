package com.vrlocal.android.baseproject.ui.screens.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.BR
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.databinding.AdapterHomeBinding
import com.vrlocal.android.baseproject.ui.common.setClickListenerBackground
import com.vrlocal.android.baseproject.ui.screens.home.data.HomeOption
import com.vrlocal.uicontrolmodule.common.VUtil
import kotlinx.android.synthetic.main.adapter_home.view.*


class HomeAdapter(
    val context: HomeActivity,
    private val optionList: List<HomeOption>,
    private val clickedListener: (adapterPosition: Int) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: AdapterHomeBinding =
            AdapterHomeBinding.inflate(layoutInflater, parent, false)
        itemBinding.root.cvOptionCard.setClickListenerBackground()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            itemBinding.root.iconService.setTextColor(
                VUtil.getColorStateListDrawable(
                    context.resources.getColor(
                        R.color.colorAccent,
                        context.theme
                    ),
                    context.resources.getColor(
                        R.color.colorPrimary,
                        context.theme
                    )
                )

            )
        }
        return ItemViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val homeOption: HomeOption = optionList[position]
        holder.bind(homeOption)
    }

    inner class ItemViewHolder(itemView: AdapterHomeBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var adapterBinding: AdapterHomeBinding = itemView
        private var cvOptionCard: CardView = itemView.root.findViewById(R.id.cvOptionCard)
        fun bind(homeOption: HomeOption) {
            adapterBinding.setVariable(BR.homeOption, homeOption)
            adapterBinding.executePendingBindings()
        }


        init {
            cvOptionCard.setOnClickListener { _ ->
                clickedListener(adapterPosition)
            }
        }


    }

}
