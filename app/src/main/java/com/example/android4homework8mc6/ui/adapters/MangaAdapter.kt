package com.example.android4homework8mc6.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android4homework8mc6.data.models.MangaModel
import com.example.android4homework8mc6.databinding.ItemKitsuBinding

class MangaAdapter(private val onItemClick: (id: String) -> Unit) :
    PagingDataAdapter<MangaModel, MangaAdapter.MangaViewHolder>(DiffUtilCallback()) {

    inner class MangaViewHolder(private val binding: ItemKitsuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: MangaModel?) {
            binding.titleTextView.text = item?.attributes?.title?.nameInJapanese
            Glide.with(binding.ivImage).load(item?.attributes?.posterImage?.original)
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        return MangaViewHolder(
            ItemKitsuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        class DiffUtilCallback : DiffUtil.ItemCallback<MangaModel>() {
            override fun areItemsTheSame(oldItem: MangaModel, newItem: MangaModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MangaModel, newItem: MangaModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
