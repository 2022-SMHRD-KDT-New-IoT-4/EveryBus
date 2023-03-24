package com.example.everybus

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
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
        // 즐겨찾기 이미지
        val imgBS_bm : ImageButton
        //  버스 노선, 버스명 , 버스 방향
        val BSLineKind  : TextView
        val BSNextBusStop : TextView
        // 곧 도착 버스 시간정보, 몇 번째 정거장, 혼잡정보
        val BSRemainMin1 : TextView
        val BSRemainStop1 : TextView
        val BSConfusion1  : TextView
        // 곧 도착 버스 승차벨 이미지
        val imgBS_rb : ImageButton

        init {
            // 즐겨찾기 이미지
            imgBS_bm = view.findViewById(R.id.imgBS_bm)
            //  버스명 , 버스 방향
            BSLineKind = view.findViewById(R.id.tvBSLineKind)
            BSNextBusStop = view.findViewById(R.id.tvBSNextBusStop)
            // 곧 도착 버스 시간정보, 몇 번째 정거장, 혼잡정보
            BSRemainMin1 = view.findViewById(R.id.tvBSRemainMin1)
            BSRemainStop1 = view.findViewById(R.id.tvBSRemainStop1)
            BSConfusion1  = view.findViewById(R.id.tvBSConfusion1)
            // 곧 도착 버스 승차벨 이미지
            imgBS_rb = view.findViewById(R.id.imgBS_rb)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.list_bus_station, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 즐겨찾기 이미지
        holder.imgBS_bm.setImageResource(data[position].imgBS_bm)
        //  버스명 , 버스 방향
        holder.BSLineKind.setText(data[position].bsLineName)
        Log.d("버스 종류", holder.BSLineKind.text.toString())

        holder.BSNextBusStop.setText(data[position].BSNextBusStop)

        // 곧 도착 버스 시간정보, 몇 번째 정거장, 혼잡정보
        holder.BSRemainMin1.setText("약 "+data[position].BSRemainMin1+"분")
        holder.BSRemainStop1.setText(data[position].BSRemainStop1+"번째 전")
        holder.BSConfusion1.setText(data[position].BSConfusion1)


        // 곧 도착 버스 승차벨 이미지
        holder.imgBS_rb.setImageResource(data[position].imgBS_rb)

        val busC:Array<String> = arrayOf("#FF6347","#FFD700","#04A80C","#04A80C","mainColor","#C0C0C0")
        val Con:Array<String> = arrayOf("보통","여유","혼잡","매우 혼잡")
        var colorPosition = 0
        Log.d("버스 종류 리스트", data[position].bsLineKind)
        holder.BSLineKind.setTextColor(Color.parseColor(busC[data[position].bsLineKind.toInt()-1]))
        holder.BSConfusion1.setText((Con[data[position].bsLineKind.toInt()-1]))
        if(holder.BSConfusion1.text.equals("여유")){
            holder.BSConfusion1.setBackgroundResource(R.drawable.congestion_info_green)
        }else if(holder.BSConfusion1.text.equals("보통")){
            holder.BSConfusion1.setBackgroundResource(R.drawable.congestion_info_blue)
        }else if(holder.BSConfusion1.text.equals("혼잡")){
            holder.BSConfusion1.setBackgroundResource(R.drawable.congestion_info_yellow)
        }else{
            holder.BSConfusion1.setBackgroundResource(R.drawable.congestion_info_red)
        }

//        //MyPrefs 라는 이름의 SharedPreferences 인스턴스
        val sharedPreferences = context.getSharedPreferences("SPF", Context.MODE_PRIVATE)

        /////////////////////////////////////////////////////////////////////////////////////
        val key = "favorite_" + holder.BSLineKind.text.toString()
        val info = "businfo" + holder.BSLineKind.text.toString() + holder.BSNextBusStop.text.toString()
        var isFavorite = sharedPreferences.getBoolean(key, false)

        // 즐겨찾기 버튼 클릭 이벤트 처리

        holder.imgBS_bm.setOnClickListener {

            isFavorite = !isFavorite
            val editor = sharedPreferences.edit()
            //holder.tvSub.text.toString()) 를 호출하여 현재 아이템의 정보를 담은 BusStationVO 객체를 생성
            val busInfo = busLike(holder.BSLineKind.text.toString(), holder.BSNextBusStop.text.toString())
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
                holder.imgBS_bm.setImageResource(R.drawable.star_bus_on)
            } else {
                holder.imgBS_bm.setImageResource(R.drawable.star_bus)
            }

            val savedJson = sharedPreferences.getString(info, null)
            if (savedJson != null) {
                val gson = Gson()
                val busInfo = gson.fromJson(savedJson, busInfo::class.java)
                holder.BSLineKind.text = busInfo.title
                holder.BSNextBusStop.text = busInfo.sub
            }
        }
        var cnt = 0
        holder.imgBS_rb.setOnClickListener {
            cnt++
            if (cnt % 2 == 1) {
                Log.d("ㅡㅡ", "너무 화난다")
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
                } else {
                    Log.d("ㅡㅡ", "너무 화난다")
                }
                holder.imgBS_rb.setImageResource(R.drawable.yellowridebell)
            } else {
                holder.imgBS_rb.setImageResource(R.drawable.ridebell)
            }

        }

//        var istrue : Boolean
//        holder.imgBS_rb.setOnClickListener {
//            if (istrue) {
//                holder.imgBS_bm.setImageResource(R.drawable.)
//            } else {
//                holder.imgBS_bm.setImageResource(R.drawable.star_bus)
//            }
//        }
    }
}






