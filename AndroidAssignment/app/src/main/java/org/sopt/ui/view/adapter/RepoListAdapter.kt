package org.sopt.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.data.local.entity.RepoData
import org.sopt.databinding.ItemRepositoryListBinding

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>(){
    private val _data = mutableListOf<RepoData>()
    var data : List<RepoData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding: ItemRepositoryListBinding = ItemRepositoryListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)

        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size

    class RepoViewHolder(private val binding: ItemRepositoryListBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(repoData: RepoData) {
            binding.repoData = repoData
        }
    }
}