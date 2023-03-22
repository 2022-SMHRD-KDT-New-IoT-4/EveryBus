package com.example.everybus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookmarkAdapter(val context: Context, val data: ArrayList<BookmarkVO>)
    : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

        // 1. ViewHolder 생성
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val tvName : TextView
            val tvRoute : TextView
            val tvKind : TextView
            val img1 : ImageView
            val img2 : ImageButton
          init {
              tvName = view.findViewById(R.id.tvName)
              tvRoute = view.findViewById(R.id.tvRoute)
              tvKind = view.findViewById(R.id.tvKind)
              img1 = view.findViewById(R.id.img1)
              img2 = view.findViewById(R.id.img2)
          }
        }

    // 2. implement members
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        // bookmark_list.xml : 즐겨찾기 목록
        val view = inflater.inflate(R.layout.list_bookmark, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    var click = false
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 버스&정류장 이름
        holder.tvName.text = data[position].Name
        // 버스 종류, 정류장의 경우 방면
        holder.tvKind.text = data[position].Kind
        // 경로
        holder.tvRoute.text = data[position].Route
        // 버스&정류장 아이콘
        holder.img1.setImageResource(data[position].img_1)
        // 승차벨&햄버거 아이콘
        holder.img2.setImageResource(data[position].img_2)

        holder.img2.setOnClickListener{
            click = true
            if(click){
                holder.img2.setImageResource(R.drawable.redridebell)

            }else{
                holder.img2.setImageResource(R.drawable.ridebell)

            }

        }
    }


}