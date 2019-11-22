package com.vrlocal.android.baseproject.ui.screens.comments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.BR
import com.vrlocal.android.baseproject.databinding.AdapterCommentsBinding
import com.vrlocal.android.baseproject.ui.common.setClickListenerBackground
import com.vrlocal.android.baseproject.ui.screens.comments.data.Comment
import com.vrlocal.android.baseproject.ui.screens.comments.data.Comments

class CommentsAdapter(
    val context: Context,
    private val optionList: Comments,
    private val clickedListener: (adapterPosition: Int) -> Unit
) :
    RecyclerView.Adapter<CommentsAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: AdapterCommentsBinding =
            AdapterCommentsBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val homeOption: Comment = optionList.get(position)
        holder.bind(homeOption)
    }

    inner class ItemViewHolder(itemView: AdapterCommentsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var adapterBinding: AdapterCommentsBinding = itemView
        //        private var cvOptionCard: CardView = itemView.root.findViewById(R.id.cvOptionCard)
        fun bind(homeOption: Comment) {
            adapterBinding.setVariable(BR.comment, homeOption)
            adapterBinding.executePendingBindings()
        }


        init {
            adapterBinding.postListTile.setClickListenerBackground()
            adapterBinding.postListTile.setOnClickListener {
                clickedListener(adapterPosition)
            }
        }


    }

}