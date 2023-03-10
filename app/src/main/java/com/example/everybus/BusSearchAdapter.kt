package com.example.everybus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BusSearchAdapter(val context: Context, val data: ArrayList<BusSearchVO>)
    : RecyclerView.Adapter<BusSearchAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        // 버스&정류장 이름, 오른쪽 화살표, 즐겨찾기 여부
        val tvName : TextView
        val imgArrow : ImageView
        val imgBookmark : ImageView
        init {
            tvName = view.findViewById(R.id.tvName_2)
            imgArrow = view.findViewById(R.id.imgRightArrow)
            imgBookmark = view.findViewById(R.id.imgBookmark)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusSearchAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        // bus_search_recently.xml : 버스 최근 검색 목록
        var view = inflater.inflate(R.layout.bus_search_recently, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusSearchAdapter.ViewHolder, position: Int) {
        // 버스&정류장 이름
        holder.tvName.text = data[position].Name
        // 오른쪽 화살표
        holder.imgArrow.setImageResource(data[position].img_2)
        // 즐겨찾기 유무 아이콘
        holder.imgBookmark.setImageResource(data[position].img)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}