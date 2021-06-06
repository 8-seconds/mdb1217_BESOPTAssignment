package org.sopt.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.data.remote.model.response.ResFollower
import org.sopt.databinding.ItemFollowerBinding

class FollowerListAdapter : RecyclerView.Adapter<FollowerListAdapter.FollowerViewHolder>() {
    private val _data = mutableListOf<ResFollower>()
    var data : List<ResFollower> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = _data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding: ItemFollowerBinding = ItemFollowerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    class FollowerViewHolder(private val binding: ItemFollowerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(resFollower: ResFollower) {
            binding.apply {
                this.id = resFollower.login
                this.url = resFollower.avatar_url
            }
        }
    }

}