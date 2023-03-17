package com.example.everybus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



// bus_route.xml 을 위한 어댑터
class BusRouteAdapter(val context : Context, val data : ArrayList<BusRouteVO>)
    :RecyclerView.Adapter<BusRouteAdapter.ViewHolder>(){

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        // 시작노선이미지, 정류장명, 정류장번호, 버스 현위치 이미지
        val imgRoute : ImageView
        val tvStation_subT : TextView
        val tvStation_Num : TextView
        val imgRouteBus : ImageView
        init {
            imgRoute = view.findViewById(R.id.imgRoute)
            tvStation_subT = view.findViewById(R.id.tvStation_subT)
            tvStation_Num = view.findViewById(R.id.tvStation_Num)
            imgRouteBus= view.findViewById(R.id.imgRouteBus)

        }
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): BusRouteAdapter.ViewHolder{
        val inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.bus_route, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 시작점 이미지
        holder.imgRoute.setImageResource(data[position].imgRoute)
        // 정류장명
        holder.tvStation_subT.text= data[position].tvStation_subT
        // 정류장 번호
        holder.tvStation_Num.text = data[position].tvStation_Num
        // 버스 현위치 이미지
        holder.imgRouteBus.setImageResource(data[position].imgRouteBus)


    }

    override fun getItemCount(): Int {
        return  data.size
    }

}