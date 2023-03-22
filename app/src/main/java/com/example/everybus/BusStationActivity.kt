package com.example.everybus

import android.graphics.Color
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


class BusStationActivity: AppCompatActivity() {

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
        Log.d("tag",data.size.toString())

        // Template : bus_stationlist.xml
        // Item : ArrayList<BusStationVO>


        queue = Volley.newRequestQueue(applicationContext)
        val url ="http://api.gwangju.go.kr/json/arriveInfo?serviceKey=AXQzbuMmvQ3Bo0XkEZT2RYIcSjbGEbBiWHFW7LRA1OZwV8bBrxsSwRKnlDtTOYTmyVkv0RugCI%2FPEhDwECi%2FVQ%3D%3D&BUSSTOP_ID=191"
            request = StringRequest(
                Request.Method.GET,
                url,
                {response ->
                    Log.d("tag","들어오니3?")
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

                        // BusStationVO에 데이터 추가
                        data.add(BusStationVO(R.drawable.star_bus,bsLineKind,bsLineName,bsNextBusStop, bsRemainMin1
                            ,bsRemainStop1,bsConfusion1, R.drawable.ridebell))
                        Log.d("데이터 한 줄",data[0].toString())
                    }
                    adapter.notifyDataSetChanged()
                },
                {error -> Log.d("tag",error.toString())}
            )

            request.setShouldCache(false)
            queue.add(request)
            // queue.add가 없었음 1문제
            // 2문제 --> rebuild가 제대로 안됨 --> rebuild --> error가 뜸
            // 3문제 --> permission문제 발생 --> 권한이 열려있음
            // 4문제 --> 권한 추가했음에도 반응 안함 --> uninstall --> 다시 실행

        ////// 실시간으로 api받아오기 근데 오류가 있음! 밑으로 계속 생김! 중복 없애야 함!
//
//        thread(start = true) {
//            while(true){
//                queue.add(request)
//                Thread.sleep(1000)
//            }
//        }

        // Adpater 연결
        adapter = busStationAdapter(applicationContext,data)
        //
        rcStation.adapter = adapter
        var layoutManager = LinearLayoutManager(this)
        rcStation.layoutManager = layoutManager
        //rcStation.layoutManager = GridLayoutManager(this,1)


    }


    }
