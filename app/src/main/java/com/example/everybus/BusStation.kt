package com.example.everybus

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import kotlin.concurrent.thread


class BusStation: AppCompatActivity() {

    lateinit var queue: RequestQueue
    lateinit var request: StringRequest
    lateinit var adapter: busStationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bus_station)


        // container 결정
        var rcStation = findViewById<RecyclerView>(R.id.rcbusStation)

        var tvBusId = findViewById<TextView>(R.id.tvBusId)
        var tvStopName = findViewById<TextView>(R.id.tvStopName)
        var tvRoute1 = findViewById<TextView>(R.id.tvRoute1)


        val data = ArrayList<BusStationVO>()


        // Template : bus_stationlist.xml
        // Item : ArrayList<BusStationVO>


        queue = Volley.newRequestQueue(applicationContext)

        val url ="http://api.gwangju.go.kr/json/arriveInfo?serviceKey=AXQzbuMmvQ3Bo0XkEZT2RYIcSjbGEbBiWHFW7LRA1OZwV8bBrxsSwRKnlDtTOYTmyVkv0RugCI%2FPEhDwECi%2FVQ%3D%3D&BUSSTOP_ID=191"

            request = StringRequest(
                Request.Method.GET,
                url,
                {response ->
                    // key
                    val response1 = JSONObject(response)
                    Log.d("response1",response1.toString())
                    // value
                    val busList = response1.getJSONArray("BUSSTOP_LIST")
                    Log.d("bus", busList.toString())
                    // 버스 도착정보 busList에 입력
                    //val busList = response2.getJSONArray("BUSSTOP_LIST")
                    //Log.d("bus", busList.toString())




                    for (i in 0 until busList.length()){
                        val bus = busList[i] as JSONObject
//                        // 정류소 ID
//                        val bsCurrStopId = bus.getString("CURR_STOP_ID")
//                        // 정류소 명칭
//                        val bsBusStopName = bus.getString("BUSSTOP_NAME")
//                        // 방면
//                        var bsNextBusStop = "db"

                        // 버스 노선 구분 -> 버스 명 색 바꿀 값
                        val bsLineKind = bus.getString("LINE_KIND")
                        // 버스명
                        val bsLineName = bus.getString("LINE_NAME")
                        // 버스 방향
                        val bsNextBusStop = "방향"
                        // 첫 번째 - 남은 시간
                        val bsRemainMin1 = bus.getString("REMAIN_MIN")
                        // 첫 번째 - 남은 정류소 개수
                        val bsRemainStop1 = bus.getString("REMAIN_STOP")
                        // 첫 번째 - 혼잡 정보
                        val bsConfusion1 = "db"

                        // 두 번째 - 남은 시간
                        val bsRemainMin2 = bus.getString("REMAIN_MIN")
                        // 두 번째 - 남은 정류소 개수
                        val bsRemainStop2 = bus.getString("REMAIN_STOP")
                        // 두 번째 - 혼잡 정보
                        val bsConfusion2 = "db"



                        // BusStationVO에 데이터 추가
                        data.add(BusStationVO(R.drawable.star_bus,bsLineName,bsNextBusStop, bsRemainMin1
                            , bsRemainStop1,bsConfusion1,bsRemainMin2,  bsRemainStop2
                            ,bsConfusion2, R.drawable.ridebell))
                    }
                    adapter.notifyDataSetChanged()
                },
                {error -> }
            )
            request.setShouldCache(false)

        ////// 실시간으로 api받아오기 근데 오류가 있음! 밑으로 계속 생김! 중복 없애야 함!

        thread(start = true) {
            while(true){
                queue.add(request)
                Thread.sleep(1000)
            }
        }

        /////////////////




        // Adpater 연결
        adapter = busStationAdapter(applicationContext,data)
        //
        rcStation.adapter = adapter
        var layoutManager = LinearLayoutManager(this)
        rcStation.layoutManager = layoutManager
        //rcStation.layoutManager = GridLayoutManager(this,1)


    }


    }
