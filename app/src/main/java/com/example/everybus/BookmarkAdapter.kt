package com.example.everybus

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent

class BookmarkAdapter(val context: Context, val data: ArrayList<BookmarkVO>, private val listener: MyItemClickListener)
    : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    interface MyItemClickListener {
        fun onItemClick(position: Int)
    }




        // 1. ViewHolder 생성
        inner class ViewHolder(view: View, listener:MyItemClickListener):RecyclerView.ViewHolder(view){
            val view = view
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
              img2.setOnClickListener {
                  listener.onItemClick(adapterPosition)
              }
          }





        }

    // 2. implement members
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        // bookmark_list.xml : 즐겨찾기 목록
        val view = inflater.inflate(R.layout.list_bookmark, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    var click = false
    var cnt = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
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
            cnt++
            if(cnt%2==1){
                holder.img2.setImageResource(R.drawable.yellowridebell)
            }else{
                holder.img2.setImageResource(R.drawable.ridebell)
            }
            Log.d("ㅡㅡ","너무 화난다")
            if (context != null) {
                // 다이얼로그를 띄우는 코드
                val builder = AlertDialog.Builder(context)
                builder.setTitle("승차벨을 활성화하시겠습니까?")
                    .setMessage("승차벨 정보를 버스에 알립니다.")
                    .setPositiveButton("확인") { _, _ ->
                        // 확인 버튼을 눌렀을 때 처리하는 코드
                    }
                    .setNegativeButton("취소") { _, _ ->
                        // 취소 버튼을 눌렀을 때 처리하는 코드
                    }
                builder.create().show()
            }else{
                Log.d("ㅡㅡ","너무 화난다")
            }
        }

    }




}