package com.example.everybus

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// bus_route_list.xml 을 위한 어댑터
class BusRouteAdapter(val context : Context, val data : ArrayList<BusRouteVO>)
    :RecyclerView.Adapter<BusRouteAdapter.ViewHolder>(){

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        // 시작노선이미지, 정류장명, 정류장번호, 버스 현위치 이미지
        val imgRoute : ImageView
        val tvStation_subT : TextView
        val tvStation_Num : TextView
        val imgRouteBus : ImageView
        var tvBRBusId : TextView
        var tvBRConfusion : TextView

        init {
            imgRoute = view.findViewById(R.id.imgRoute)
            tvStation_subT = view.findViewById(R.id.tvStation_subT)
            tvStation_Num = view.findViewById(R.id.tvStation_Num)
            imgRouteBus= view.findViewById(R.id.imgRouteBus)
            tvBRBusId = view.findViewById(R.id.tvBRBusId)
            tvBRConfusion = view.findViewById(R.id.tvBRConfusion)


        }
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): BusRouteAdapter.ViewHolder{
        val inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.list_bus_route, parent, false)
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
        // 버스 번호
        holder.tvBRBusId.text = data[position].tvBRBusId
        // 버스 혼잡도
        holder.tvBRConfusion.text = data[position].tvBRConfusion

                                       // 매우 혼잡, 혼잡(노랑) , 보통(파랑), 여유
        val busC:Array<String> = arrayOf("#FF6347","#FFD700","#288cff","#04A80C")

        if(holder.tvBRConfusion.text.equals("여유")){
            holder.tvBRConfusion.setBackgroundColor(Color.parseColor(busC[3]))
            holder.tvBRConfusion.setTextColor(Color.WHITE)
        }else if(holder.tvBRConfusion.text.equals("보통")){
            holder.tvBRConfusion.setBackgroundColor(Color.parseColor(busC[2]))
            holder.tvBRConfusion.setTextColor(Color.WHITE)
        }else if(holder.tvBRConfusion.text.equals("혼잡")){
            holder.tvBRConfusion.setBackgroundColor(Color.parseColor(busC[1]))
            holder.tvBRConfusion.setTextColor(Color.WHITE)
        }else{
            holder.tvBRConfusion.setBackgroundColor(Color.parseColor(busC[0]))
            holder.tvBRConfusion.setTextColor(Color.WHITE)
        }


    }

    override fun getItemCount(): Int {
        return  data.size
    }

}