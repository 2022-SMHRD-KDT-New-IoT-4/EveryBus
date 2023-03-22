package com.example.everybus

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BusSearchAdapter(val context: Context, val data: ArrayList<BusSearchVO>)
    : RecyclerView.Adapter<BusSearchAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        // 버스이름, 방향, 즐겨찾기 여부
        val tvLineName : TextView
        val tvLineKind :  TextView

        val imgBookmark : ImageView

        init {
            tvLineName = view.findViewById(R.id.tvName_2)
            tvLineKind = view.findViewById(R.id.tvName_2_sub)
            imgBookmark = view.findViewById(R.id.imgBookmark)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusSearchAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        // bus_search.xml : 버스 검색 목록
        var view = inflater.inflate(R.layout.bus_search_recently, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusSearchAdapter.ViewHolder, position: Int) {
        // 버스&정류장 이름
        holder.tvLineName.text = data[position].Name
        // 방면
//        holder.tvLineKind.text = data[position].LineKind
        holder.tvLineKind.text = data[position].tvName_2_sub
        // 즐겨찾기 유무 아이콘
        holder.imgBookmark.setImageResource(data[position].img)
        val busC:Array<String> = arrayOf("#FF6347","#FFD700","#04A80C","#04A80C","mainColor","#C0C0C0")

        if(holder.tvLineName.text.equals("수완03")) {
            holder.tvLineName.setTextColor(Color.parseColor(busC[0]))
        }
        // Bus_Search2 뷰 클릭 -> Bus_search3 이동
        val data = data[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, BusRouteActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }



}