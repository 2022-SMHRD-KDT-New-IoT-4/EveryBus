package com.example.everybus

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.everybus.R

class CongestionAdapter(var context: Context, var data: ArrayList<CongestionVO>) : RecyclerView.Adapter<CongestionAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvCLineName : TextView
        val tvCTime : TextView
        val tvCInfo : TextView
        init {
            tvCLineName = view.findViewById(R.id.tvCLineName)
            tvCTime = view.findViewById(R.id.tvCTime)
            tvCInfo = view.findViewById(R.id.tvCInfo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view
        = LayoutInflater.from(context).inflate(R.layout.congestion_info_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCInfo.setText(data[position].passenger_cnt)
        if(holder.tvCInfo.text.equals("여유")){
            holder.tvCInfo.setBackgroundResource(R.drawable.congestion_info_green)
        }else if(holder.tvCInfo.text.equals("보통")){
            holder.tvCInfo.setBackgroundResource(R.drawable.congestion_info_blue)
        }else if(holder.tvCInfo.text.equals("혼잡")){
            holder.tvCInfo.setBackgroundResource(R.drawable.congestion_info_yellow)
        }else{
            holder.tvCInfo.setBackgroundResource(R.drawable.congestion_info_red)
        }
        holder.tvCLineName.setText(data[position].line_name)
        holder.tvCTime.setText(data[position].reg_date)
    }

}