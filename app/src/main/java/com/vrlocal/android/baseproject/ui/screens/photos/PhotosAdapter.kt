package com.vrlocal.android.baseproject.ui.screens.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.databinding.AdapterPhotosBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo


class PhotosAdapter : PagedListAdapter<Photo, PhotosAdapter.ViewHolder>(PhotoDiffCallback()) {

    private lateinit var recyclerView: RecyclerView

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = getItem(position)
        photo?.let {
            holder.apply {
                bind(createOnClickListener(photo.id), photo, isGridLayoutManager())
                itemView.tag = photo
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterPhotosBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        if (recyclerView.context is BaseActivity<*, *>) {
            (recyclerView.context as BaseActivity<*, *>).showProgressBar()

        }
    }


    private fun hideActivityProgressBar(pagedList: PagedList<Photo>) {

        if (pagedList.size != 0 && recyclerView.context is BaseActivity<*, *>) {
            (recyclerView.context as BaseActivity<*, *>).hideProgressBar()
        }

    }


    private fun createOnClickListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            Toast.makeText(it.context, "id $id", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isGridLayoutManager() = recyclerView.layoutManager is GridLayoutManager

    class ViewHolder(private val binding: AdapterPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            listener: View.OnClickListener, item: Photo,
            isGridLayoutManager: Boolean
        ) {
            binding.apply {
                clickListener = listener
                photo = item
                title.visibility = if (isGridLayoutManager) View.GONE else View.VISIBLE
                executePendingBindings()
            }
        }
    }


}

private class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}
/*

public class PhotosAdapter(

) : RecyclerView.Adapter<PhotosAdapter.ItemViewHolder>() {

    lateinit var context: PostActivity
    var optionList: List<Photo> = arrayListOf()
    lateinit var clickedListener: (adapterPosition: Int) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: AdapterPostsBinding =
            AdapterPostsBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val homeOption: Photo = optionList.get(position)
        holder.bind(homeOption)
    }

    inner class ItemViewHolder(itemView: AdapterPostsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var adapterBinding: AdapterPostsBinding = itemView
        //        private var cvOptionCard: CardView = itemView.root.findViewById(R.id.cvOptionCard)
        fun bind(homeOption: Photo) {
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

}*/
