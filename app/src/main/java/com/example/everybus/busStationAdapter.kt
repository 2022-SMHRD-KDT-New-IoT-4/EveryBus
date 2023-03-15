package com.example.everybus

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class busStationAdapter(var context: Context, var data:ArrayList<BusStationVO>):

    RecyclerView.Adapter<busStationAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvSub: TextView
        val tvTime: TextView
        val tvTime2: TextView
        val tvAlive3: TextView
        val tvAlive4: TextView
        val tvPO: TextView
        val tvPO2: TextView
        val imgStar: ImageButton

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            tvSub = view.findViewById(R.id.tvSub)
            tvTime = view.findViewById(R.id.tvTime)
            tvTime2 = view.findViewById(R.id.tvTime2)
            tvAlive3 = view.findViewById(R.id.tvAlive3)
            tvAlive4 = view.findViewById(R.id.tvAlive4)
            tvPO = view.findViewById(R.id.tvPO)
            tvPO2 = view.findViewById(R.id.tvPO2)
            imgStar = view.findViewById(R.id.imgStar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.bus_stationlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = data[position].title
        holder.tvSub.text = data[position].sub
        holder.tvTime.text = data[position].time
        holder.tvTime2.text = data[position].time2
        holder.tvAlive3.text = data[position].alive
        holder.tvAlive4.text = data[position].alive2
        holder.tvPO.text = data[position].po
        holder.tvPO2.text = data[position].po2

        //MyPrefs 라는 이름의 SharedPreferences 인스턴스
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val key = "favorite_" + holder.tvTitle.text.toString()
        val info = "businfo" + holder.tvTitle.text.toString() + holder.tvSub.text.toString()
        var isFavorite = sharedPreferences.getBoolean(key, false)

        // 즐겨찾기 버튼 클릭 이벤트 처리

        holder.imgStar.setOnClickListener {

            isFavorite = !isFavorite
            val editor = sharedPreferences.edit()
            //holder.tvSub.text.toString()) 를 호출하여 현재 아이템의 정보를 담은 BusStationVO 객체를 생성
            val busInfo = busLike(holder.tvTitle.text.toString(), holder.tvSub.text.toString())
            val gson = Gson()
            val json = gson.toJson(busInfo)

            val busInfoList = mutableListOf<BusStationVO>()
            for (key in sharedPreferences.all.keys) {
                if (key.startsWith("businfo")) {
                    val savedJson = sharedPreferences.getString(key, null)
                    if (savedJson != null) {
                        val gson = Gson()
                        val busInfo = gson.fromJson(savedJson, BusStationVO::class.java)
                        busInfoList.add(busInfo)
                    }
                }
            }

            editor.putBoolean(key, !isFavorite)
            //즐겨찾기가 되어 있는 상태에서 한번더 누르면 삭제 코드 인데 안됨

            if (isFavorite) {
                editor.putString(info, json)
            } else {
                editor.remove(info)
            }
            //SharedPreferences 에 info,json 저장
            editor.putString(info, json)
            //변경사항 저장
            editor.apply()
            Log.d("List", gson.toJson(busInfoList))

            // 버튼 상태 변경

            if (isFavorite) {
                holder.imgStar.setImageResource(R.drawable.star_bus_on)
            } else {
                holder.imgStar.setImageResource(R.drawable.star_bus)
            }

            val savedJson = sharedPreferences.getString(info, null)
            if (savedJson != null) {
                val gson = Gson()
                val busInfo = gson.fromJson(savedJson, busInfo::class.java)
                holder.tvTitle.text = busInfo.title
                holder.tvSub.text = busInfo.sub
            }


        }

    }
}






