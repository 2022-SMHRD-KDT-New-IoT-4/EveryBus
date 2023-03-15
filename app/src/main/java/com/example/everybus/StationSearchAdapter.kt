package com.example.everybus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationSearchAdapter(val context: Context, val data: ArrayList<StationSearchVO>) :
    RecyclerView.Adapter<StationSearchAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvTitleSr : TextView
        val tvSubSr : TextView
        val tvNumberSr : TextView
        val imgStarSr : ImageButton

        init {
            tvTitleSr = view.findViewById(R.id.tvTitleSr)
            tvSubSr = view.findViewById(R.id.tvSubSr)
            tvNumberSr = view.findViewById(R.id.tvSubSr)
            imgStarSr= view.findViewById(R.id.imgStarSr)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationSearchAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)

        var view = inflater.inflate(R.layout.stationlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationSearchAdapter.ViewHolder, position: Int) {

        holder.tvTitleSr.text=data[position].title
        holder.tvSubSr.text=data[position].sub
        holder.tvNumberSr.text=data[position].stationNum



    }

    override fun getItemCount(): Int {

        return data.size
    }
}