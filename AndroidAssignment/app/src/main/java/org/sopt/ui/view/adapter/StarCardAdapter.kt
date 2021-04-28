package org.sopt.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.data.local.entity.RepoData
import org.sopt.databinding.ItemStarredCardBinding

class StarCardAdapter: RecyclerView.Adapter<StarCardAdapter.StarViewHolder>() {
    private val _data = mutableListOf<RepoData>()
    var data : List<RepoData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = _data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarViewHolder {
        val binding: ItemStarredCardBinding = ItemStarredCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return StarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StarCardAdapter.StarViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    inner class StarViewHolder(private val binding: ItemStarredCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repoData: RepoData) {
            binding.apply { this.repoData = repoData }
        }
    }

}