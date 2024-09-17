package com.example.android4homework8mc6.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android4homework8mc6.data.models.AnimeModel
import com.example.android4homework8mc6.databinding.ItemKitsuBinding

class AnimeAdapter(private val onItemClick: (id: String) -> Unit) : PagingDataAdapter<AnimeModel, AnimeAdapter.AnimeViewHolder>(
    DiffUtilCallback()
) {

    inner class AnimeViewHolder(private val binding: ItemKitsuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(animeModel: AnimeModel?) {
            binding.titleTextView.text = animeModel?.attributes?.title?.nameInJapanese
            Glide.with(binding.ivImage).load(animeModel?.attributes?.posterImage?.original)
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            ItemKitsuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {

        class DiffUtilCallback : DiffUtil.ItemCallback<AnimeModel>() {

            override fun areItemsTheSame(oldItem: AnimeModel, newItem: AnimeModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AnimeModel, newItem: AnimeModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
