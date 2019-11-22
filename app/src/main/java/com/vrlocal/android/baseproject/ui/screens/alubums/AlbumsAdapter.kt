package com.vrlocal.android.baseproject.ui.screens.alubums

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.BR
import com.vrlocal.android.baseproject.databinding.AdapterAlbumBinding
import com.vrlocal.android.baseproject.ui.common.setClickListenerBackground
import com.vrlocal.android.baseproject.ui.screens.alubums.data.Album
import com.vrlocal.android.baseproject.ui.screens.alubums.data.Albums

class AlbumsAdapter(
    val context: Context,
    private val optionList: Albums,
    private val clickedListener: (adapterPosition: Int) -> Unit
) :
    RecyclerView.Adapter<AlbumsAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: AdapterAlbumBinding =
            AdapterAlbumBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val homeOption: Album = optionList.get(position)
        holder.bind(homeOption)
    }

    inner class ItemViewHolder(itemView: AdapterAlbumBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var adapterBinding: AdapterAlbumBinding = itemView
        //        private var cvOptionCard: CardView = itemView.root.findViewById(R.id.cvOptionCard)
        fun bind(homeOption: Album) {
            adapterBinding.setVariable(BR.album, homeOption)
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