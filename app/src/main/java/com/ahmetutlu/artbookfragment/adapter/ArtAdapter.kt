package com.ahmetutlu.artbookfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmetutlu.artbookfragment.MainFragmentDirections
import com.ahmetutlu.artbookfragment.databinding.RecyclerRowBinding
import com.ahmetutlu.artbookfragment.model.Art

class ArtAdapter(val artList: List<Art>): RecyclerView.Adapter<ArtAdapter.ArtHolder>() {

    class ArtHolder(val recyclerRowBinding: RecyclerRowBinding):RecyclerView.ViewHolder(recyclerRowBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.recyclerRowBinding.recyclerViewTextView.text=artList.get(position).artName
        holder.itemView.setOnClickListener {
            val action=MainFragmentDirections.actionMainFragmentToArtFragment2()
            Navigation.findNavController(it).navigate(action)


        }

    }

    override fun getItemCount(): Int {
        return artList.size
    }
}
