package com.vrlocal.android.baseproject.ui.screens.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.BR
import com.vrlocal.android.baseproject.databinding.AdapterPostsBinding
import com.vrlocal.android.baseproject.ui.common.setClickListenerBackground
import com.vrlocal.android.baseproject.ui.screens.posts.data.Post
import com.vrlocal.uicontrolmodule.ui.VClickUtils

class PostAdapter(
    val context: PostActivity,
    private val optionList: List<Post>,
    private val clickedListener: (adapterPosition: Int) -> Unit
) :
    RecyclerView.Adapter<PostAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: AdapterPostsBinding =
            AdapterPostsBinding.inflate(layoutInflater, parent, false)
        VClickUtils.applyScale(itemBinding.root)

        return ItemViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val homeOption: Post = optionList.get(position)
        holder.bind(homeOption)
    }

    inner class ItemViewHolder(itemView: AdapterPostsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var adapterBinding: AdapterPostsBinding = itemView
        //        private var cvOptionCard: CardView = itemView.root.findViewById(R.id.cvOptionCard)
        fun bind(homeOption: Post) {
            adapterBinding.setVariable(BR.post, homeOption)
            adapterBinding.executePendingBindings()
        }


        init {
            adapterBinding.postListTile.setClickListenerBackground()
            adapterBinding.postListTile.setOnClickListener {
                Toast.makeText(context, "MESSAGE", Toast.LENGTH_SHORT).show()
            }
        }


    }

}