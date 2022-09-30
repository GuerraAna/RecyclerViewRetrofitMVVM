package com.example.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm.R
import com.example.mvvm.databinding.ResItemLiveBinding
import com.example.mvvm.model.Live

class MainAdapter(
    private val onItemClicked: (Live) -> Unit
) : RecyclerView.Adapter<MainViewModelHolder>() {

    private var lives = mutableListOf<Live>()

    fun setLiveList(lives: List<Live>) {
        this.lives =  lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewModelHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemLiveBinding.inflate(inflater, parent, false)
        return MainViewModelHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewModelHolder, position: Int) {
        val lives = lives[position]
        holder.bind(
            live = lives,
            onItemClicked = onItemClicked
        )
    }

    override fun getItemCount(): Int {
        return lives.size
    }
}

class MainViewModelHolder(val binding: ResItemLiveBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        live: Live,
        onItemClicked: (Live) -> Unit
    ) {
        binding.title.text = live.title
        binding.author.text = live.author

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(live.thumbnailUrl)
            .into(binding.thumbnail)

        setupListeners(onItemClicked, live)

    }

    private fun setupListeners(
        onItemClicked: (Live) -> Unit,
        live: Live
    ) {
        itemView.setOnClickListener {
            onItemClicked(live)
        }
    }
}
