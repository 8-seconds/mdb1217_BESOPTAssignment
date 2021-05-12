package org.sopt.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.data.local.entity.RepoData
import org.sopt.databinding.ItemRepositoryListBinding

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>(), ItemTouchHelperListener {
    private var starButtonClickListener: ((Long, Boolean)-> Unit) ?= null
    private var dataSwipeListener: ((RepoData)-> Unit) ?= null
    private val _data = mutableListOf<RepoData>()
    var data : List<RepoData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    fun setStarButtonClickListener(listener : (Long, Boolean)-> Unit) {
        this.starButtonClickListener = listener
    }

    fun setDataSwipeListener(listener : (RepoData)-> Unit) {
        this.dataSwipeListener = listener
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

    inner class RepoViewHolder(private val binding: ItemRepositoryListBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(repoData: RepoData) {
            binding.apply {
                this.repoData = repoData
                ibStar.isSelected = repoData.star
                ibStar.setOnClickListener {
                    it.isSelected = !it.isSelected

                    val id = repoData.id
                    if(id != null)
                        starButtonClickListener?.invoke(id, it.isSelected)
                }
            }
        }
    }

    override fun onItemSwiped(position: Int) {
        dataSwipeListener?.invoke(_data[position])
        _data.removeAt(position)
        notifyItemRemoved(position)
    }
}