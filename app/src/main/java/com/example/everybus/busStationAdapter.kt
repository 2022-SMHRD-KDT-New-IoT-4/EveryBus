package com.example.everybus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class busStationAdapter(var context: Context, var data:ArrayList<BusStationVO>):

    RecyclerView.Adapter<busStationAdapter.ViewHolder>(){



    inner  class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvTitle : TextView
        val tvSub : TextView
        val tvTime : TextView
        val tvTime2 : TextView
        val tvAlive3 : TextView
        val tvAlive4 : TextView
        val tvPO : TextView
        val tvPO2: TextView
        val imgLike: ImageButton

        init {
            tvTitle =view.findViewById(R.id.tvTitle)
            tvSub = view.findViewById(R.id.tvSub)
            tvTime = view.findViewById(R.id.tvTime)
            tvTime2 = view.findViewById(R.id.tvTime2)
            tvAlive3= view.findViewById(R.id.tvAlive3)
            tvAlive4 = view. findViewById(R.id.tvAlive4)
            tvPO= view.findViewById(R.id.tvPO)
            tvPO2= view.findViewById(R.id.tvPO2)
            imgLike = view.findViewById(R.id.imgLike)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.bus_stationlist,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return  data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text=data[position].title
        holder.tvSub.text=data[position].sub
        holder.tvTime.text=data[position].time
        holder.tvTime2.text=data[position].time2
        holder.tvAlive3.text=data[position].alive
        holder.tvAlive4.text=data[position].alive2
        holder.tvPO.text=data[position].po
        holder.tvPO2.text=data[position].po2

        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val key = "favorite_" + holder.tvTitle.text.toString()

        var isFavorite = sharedPreferences.getBoolean(key, false)

        // 즐겨찾기 버튼 클릭 이벤트 처리

        holder.imgLike.setOnClickListener {

            isFavorite = !isFavorite
            val editor = sharedPreferences.edit()

            editor.putBoolean(key, !isFavorite)
            editor.apply()

            // 버튼 상태 변경

            if (isFavorite) {
                holder.imgLike.setImageResource(R.drawable.star_on)
            } else {
                holder.imgLike.setImageResource(R.drawable.star_off)
            }
        }
        //기존엔 저장되어 있는 즐겨찾기 초기하
        if (isFavorite) {
            holder.imgLike.setImageResource(R.drawable.star_on)
        } else {
            holder.imgLike.setImageResource(R.drawable.star_off)

        }



    }

}






