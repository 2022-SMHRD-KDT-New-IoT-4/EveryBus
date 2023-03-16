package com.example.everybus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class BusStation_d : Fragment() {
    lateinit var queue: RequestQueue
    lateinit var request: StringRequest
    lateinit var adapter: busStationAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 정류장 클릭 했을 때 페이지
        var view = inflater.inflate(R.layout.fragment_bus_station, container, false)
        var tvBusId = view.findViewById<TextView>(R.id.tvBusId)
        var tvStopName = view.findViewById<TextView>(R.id.tvStopName)
        var tvRoute1 = view.findViewById<TextView>(R.id.tvRoute1)

        // Container결정   ->   rcStation
        var rcStation = view.findViewById<RecyclerView>(R.id.rcStation)


        // Template : bus_stationlist.xml
        // Item : ArrayList<BusStationVO>
        var data = ArrayList<BusStationVO>()

        queue = Volley.newRequestQueue(this.context)

        val url ="http://api.gwangju.go.kr/json/arriveInfo?serviceKey=AXQzbuMmvQ3Bo0XkEZT2RYIcSjbGEbBiWHFW7LRA1OZwV8bBrxsSwRKnlDtTOYTmyVkv0RugCI%2FPEhDwECi%2FVQ%3D%3D&BUSSTOP_ID=2312"

        rcStation.setOnClickListener {
            request = StringRequest(
                Request.Method.GET,
                url,
                {response ->
                    // key
                    val response1 = JSONObject(response)
                    Log.d("response1",response1.toString())
                    // value
                    val response2 = response1.getJSONObject("RESULT")
                    Log.d("response2", response2.toString())
                    // 버스 도착정보 busList에 입력
                    val busList = response2.getJSONArray("BUSSTOP_LIST")
                    Log.d("bus", busList.toString())

                    for (i in 0 until busList.length()){
                        val bus = busList[i] as JSONObject
                        // 정류소 ID
                        val CURR_STOP_ID = bus.getString("CURR_STOP_ID")
                        // 정류소 명칭
                        val BUSSTOP_NAME = bus.getString("BUSSTOP_NAME")
                        // 방면

                        // 버스명
                        // 버스 방향
                        // 첫 번째 - 남은 시간
                        val REMAIN_MIN = bus.getString("REMAIN_MIN")
                        // 첫 번째 - 남은 정류소 개수
                        val REMAIN_STOP = bus.getString("REMAIN_STOP")
                        // 첫 번째 - 혼잡 정보

                        // 두 번째 - 남은 시간
                        // 두 번째 - 남은 정류소 개수
                        // 두 번째 - 혼잡 정보



                        // BusStationVO에 데이터 추가
                        data.add(BusStationVO(CURR_STOP_ID,BUSSTOP_NAME, ""
                            , R.drawable.star_on,"","", REMAIN_MIN,REMAIN_STOP, ""
                            ,"","","", R.drawable.ridebell))
                    }

                },
                {error -> }
            )
            request.setShouldCache(false)
            queue.add(request)
        }





        // Adpater 연결
//        adapter = BusStationAdapter(this, data )
//        rcStation.adapter = adapter

//        rcStation.layoutManager = LinearLayoutManager(this.context)


        return view
    }

}