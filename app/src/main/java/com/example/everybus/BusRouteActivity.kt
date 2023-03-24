package com.example.everybus

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class BusRouteActivity : AppCompatActivity() {
    // 통신을 위한 request
    lateinit var queue: RequestQueue
    lateinit var request1: StringRequest
    lateinit var request2: StringRequest

    // 정류장정보 저장할 배열
    lateinit var busStop: MutableList<String>
    lateinit var brBSName : String
    lateinit var brBSId  : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_route)



        val BusInfo = findViewById<View>(R.id.vBusInfo)
        val tvRouteLineKind = findViewById<TextView>(R.id.tvRouteLineKind)
        val tvRouteLineName = findViewById<TextView>(R.id.tvRouteLineName)
        val tvRouteDirUpName = findViewById<TextView>(R.id.tvRouteDirUpName)
        val tvRouteDirDownName = findViewById<TextView>(R.id.tvRouteDirDownName)


        val busStationRoute = findViewById<RecyclerView>(R.id.busStationRoute)
        var imgHome = findViewById<ImageButton>(R.id.imgHome)
        var busRouteList = ArrayList<BusRouteVO>()
        queue = Volley.newRequestQueue(applicationContext)

        val url = "http://59.3.122.229:8081/EveryBus/Route_Bus_info"
        val routeUrl = "http://api.gwangju.go.kr/json/lineStationInfo?serviceKey=AXQzbuMmvQ3Bo0XkEZT2RYIcSjbGEbBiWHFW7LRA1OZwV8bBrxsSwRKnlDtTOYTmyVkv0RugCI%2FPEhDwECi%2FVQ%3D%3D&LINE_ID=15"
        val busUrl = "http://api.gwangju.go.kr/json/busLocationInfo?LINE_ID=15"
        // 상단 버스정보
        request1 = StringRequest(
            Request.Method.POST,
            url,
            // 응답에 성공했을 때 Listener
            { response ->
                val response1 = JSONObject(response)
                Log.d("jsonTest",response1.toString())

                Log.d("line_id",response1.getString("line_id").toString())

                val line_kind = response1.getString("line_kind").toString()

                val lk:Array<String> = arrayOf("광주 급행 간선","광주 간선","광주 지선","광주 마을버스","광주 공항버스","지역버스")
                val busC:Array<String> = arrayOf("#FF6347","#FFC300","#04A80C","#04A80C","mainColor","#C0C0C0")

                for (i in 0..lk.size+1){
                    if(line_kind.equals((i+1).toString())){
                        tvRouteLineKind.text = lk[i]
                        Log.d("text", lk[i])
                        BusInfo.setBackgroundColor(Color.parseColor(busC[i]))
                    }
                }


                tvRouteLineName.text = response1.getString("line_name").toString()
                tvRouteDirUpName.text = response1.getString("dir_up_name").toString()
                tvRouteDirDownName.text = response1.getString("dir_down_name").toString()
                // response(결과값)의 자료형은 String!(StringRequest를 사용했기 때문에!
                // 받아온 결과값을 TextView에 적용(setText를 진행한다)
            },
            // 응답에 실패했을 때에 대한 ErrorListener
            { error ->
                Toast.makeText(
                    this@BusRouteActivity, "오류내용 : $error",
                    Toast.LENGTH_SHORT
                ).show()
                // error(어떤 오류가 들어왔는지)
                // 토스트 창으로 "응답 실패" 띄워주기
            })// !!!request 초기화 끝!!!


        // 안드로이드에서 입력한 값 집어넣기
//        { @Throws(AuthFailureError::class)
//        override fun getParams(): MutableMap<String, String>? {
//            val params: MutableMap<String, String> = HashMap()
//            params["LineName"] = etLineName.text.toString()
//
//            Log.d("여기야",params.toString())
//            return params }
//
//        }
////////////////////////// 버스상단정보 //////////////////////////

        busStop = mutableListOf()
        var request3: StringRequest

        /// 루트
        // apiUrl 값
        request2 = StringRequest(
            Request.Method.GET,
            routeUrl,
            { response ->
                // key
                val response1 = JSONObject(response)
                Log.d("response1",response1.toString())
                // value
                val busList = response1.getJSONArray("BUSSTOP_LIST")
                Log.d("bus", busList.toString())
                var bus = busList[1] as JSONObject
                Log.d("bus", bus.getString("BUSSTOP_NAME"))



                for (i in 0 until busList.length()){
                    bus = busList[i] as JSONObject

                    // 정류장명("BUSSTOP_NAME")
                    brBSName = bus.getString("BUSSTOP_NAME")
                    // 정류장번호("BUSSTOP_ID")
                    brBSId = bus.getString("BUSSTOP_ID")


                    // 정류장 번호 배열로 저장하기
                    busStop.add(brBSId)
                    Log.d("busstop", busStop.toString())
                    // BusStationVO에 데이터 추가
                    // 시작노선이미지, 정류장명("BUSSTOP_NAME"), 정류장번호("BUSSTOP_ID"), 버스 현위치 이미지(imgRouteBus)
                    if(i == 0){
                        busRouteList.add(BusRouteVO(R.drawable.start_arrow,brBSName,brBSId,0,null,null))
                    }else if(i == busList.length()-1){
                        busRouteList.add(BusRouteVO(R.drawable.end_arrow,brBSName,brBSId,0,null,null))
                    }else{
                        busRouteList.add(BusRouteVO(R.drawable.arrow,brBSName,brBSId,0,null,null))
                    }

                    request3 = StringRequest(
                        Request.Method.GET,
                        busUrl,
                        { response ->
                            // key
                            val response2 = JSONObject(response)
                            Log.d("response1",response2.toString())
                            // value
                            val busLoctionList = response2.getJSONArray("BUSLOCATION_LIST")
                            Log.d("buslocation", busLoctionList.toString())
                            var busLocation = busLoctionList[0] as JSONObject
                            Log.d("CURR_STOP_ID", busLocation.getString("CURR_STOP_ID"))


                            var c = 0
                            for (i in 0 until busLoctionList.length()){
                                busLocation = busLoctionList[i] as JSONObject

                                val brBusid = busLocation.getString("BUS_ID")
                                // 현재 정류장명("CURR_STOP_ID”)
                                val brCurrStopId = busLocation.getString("CURR_STOP_ID")
                                Log.d("brCurrStopId", brCurrStopId)
                                Log.d("busStop 요기야", busStop.toString())


                                val con:Array<String> = arrayOf("매우 혼잡","보통","혼잡","매우혼잡","여유","여유","매우 혼잡","보통","혼잡","매우혼잡","여유","여유","매우 혼잡","보통","혼잡","매우혼잡","여유","여유","매우 혼잡","보통","혼잡","매우혼잡","여유","여유")

                                for(j in 0 until busStop.size) {
                                    Log.d("c", c.toString())


                                    if (busStop[j] == brCurrStopId) {
                                        Log.d("busst", busStop[j])
                                        busRouteList[j].imgRouteBus = R.drawable.bus_green
                                        busRouteList[j].tvBRBusId = brBusid
                                        busRouteList[j].tvBRConfusion = con[c]
                                        c=c+1
                                    }
                                }}
                            val adapter = BusRouteAdapter(this, busRouteList)
                            busStationRoute.adapter = adapter
                            val layoutManager = LinearLayoutManager(this)
                            busStationRoute.layoutManager = layoutManager
                        },
                        null
                    )


                    queue.add(request3)

                }

                // 버스 노선 Adapter 연결
                val adapter = BusRouteAdapter(this, busRouteList)
                busStationRoute.adapter = adapter
                val layoutManager = LinearLayoutManager(this)
                busStationRoute.layoutManager = layoutManager



            },
            null
        )
        ////////////////////////// 하단 루트 끝 //////////////////////////

        /////////////////////// 버스 움직이기 위한 코드///////////////////

//        imgHome.setOnClickListener {
//            var intent = Intent(this@BusRouteActivity, MainActivity::class.java)
//            startActivity(intent)
//        }


        // 요청이 여러개면 캐시가 쌓인다! 그래서 캐시 메모리 정리하기!
        //request3.setShouldCache(false)
        request1.setShouldCache(false)
        request2.setShouldCache(false)

        // 내가 만든 요청을 RequestQueue에다가 넣어주자

        queue.add(request1)
        queue.add(request2)




    }
}