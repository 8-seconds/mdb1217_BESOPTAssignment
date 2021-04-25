package org.sopt.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.data.local.entity.ProfileData
import org.sopt.databinding.ItemDetailedProfileBinding

class ProfileListAdapter : RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder>(){
    private val _data = mutableListOf<ProfileData>()
    var data : List<ProfileData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding: ItemDetailedProfileBinding = ItemDetailedProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)

        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    fun setTodoList(list: List<ProfileData>) {
        data = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = _data.size

    class ProfileViewHolder(private val binding: ItemDetailedProfileBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(profileData: ProfileData) {
            binding.profileData = profileData
        }
    }
}