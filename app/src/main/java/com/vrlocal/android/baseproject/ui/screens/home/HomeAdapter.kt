package com.vrlocal.android.baseproject.ui.screens.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.ui.screens.home.data.HomeOptions

class HomeAdapter(val context: HomeActivity, private val optionList: List<HomeOptions>) :
    RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.adapter_home, parent,false)

//        inflate.cvOptionCard.background = VUtil.getDrawableListState(
//            VUtil.getRoundDrawable(Color.TRANSPARENT, 8f),
//            VUtil.getRoundDrawable(Color.LTGRAY, 8f)
//        )
        return ItemViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemLabel.text = optionList.get(position).label
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemLabel: TextView = itemView.findViewById(R.id.tvItemLabel)
        var cvOptionCard: CardView = itemView.findViewById(R.id.cvOptionCard)

        init {
            cvOptionCard.setOnClickListener { _ ->

                if (optionList[adapterPosition].activityClass == null) {
                    Toast.makeText(
                        context,
                        "Implement class",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    return@setOnClickListener
                }

                val intent = Intent(context, optionList[adapterPosition].activityClass)
                context.startActivity(intent)

            }
        }


    }

}
