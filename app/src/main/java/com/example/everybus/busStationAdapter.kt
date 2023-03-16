package com.example.everybus

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class busStationAdapter(var context: Context, var data:ArrayList<BusStationVO>):

    RecyclerView.Adapter<busStationAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBusId : TextView
        val tvStopName : TextView
        val tvRoute1 : TextView

        val imgBST_bm : ImageView
        val tvTitle : TextView
        val tvSub : TextView
        val tvTime1 : TextView
        val tvAlive1 : TextView
        val tvPO1 : TextView
        val tvTime2 : TextView
        val tvAlive2 : TextView
        val tvPO2 : TextView
        val imgBST_rb : ImageView
        init {
            tvBusId = view.findViewById(R.id.tvBusId)
            tvStopName = view.findViewById(R.id.tvStopName)
            tvRoute1 = view.findViewById(R.id.tvRoute1)

            imgBST_bm  = view.findViewById(R.id.imgBST_bm)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvSub = view.findViewById(R.id.tvSub)
            tvTime1 = view.findViewById(R.id.tvTime1)
            tvAlive1 = view.findViewById(R.id.tvAlive1)
            tvPO1 = view.findViewById(R.id.tvPO1)
            tvTime2 = view.findViewById(R.id.tvTime2)
            tvAlive2 = view.findViewById(R.id.tvAlive2)
            tvPO2 = view.findViewById(R.id.tvPO2)
            imgBST_rb = view.findViewById(R.id.imgBST_rb)

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
        holder.tvBusId.text = data[position].tvBusId
        holder.tvStopName.text = data[position].tvStopName
        holder.tvRoute1.text = data[position].tvRoute1

        holder.imgBST_bm.setImageResource(data[position].imgBST_bm)
        holder.tvTitle.text = data[position].tvTitle
        holder.tvSub.text = data[position].tvSub
        holder.tvTime1.text = data[position].tvTime1
        holder.tvAlive1.text = data[position].tvAlive1
        holder.tvPO1.text = data[position].tvPO1
        holder.tvTime2.text = data[position].tvTime2
        holder.tvAlive2.text = data[position].tvAlive2
        holder.tvPO2.text = data[position].tvPO2
        holder.imgBST_rb.setImageResource(data[position].imgBST_rb)

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






