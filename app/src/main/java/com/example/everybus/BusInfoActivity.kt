package com.example.everybus

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class BusInfoActivity : AppCompatActivity() {

    lateinit var queue: RequestQueue
    lateinit var request: StringRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_info)

        queue = Volley.newRequestQueue(this@BusInfoActivity)
        val url = "http://api.gwangju.go.kr/json/lineInfo?serviceKey=AXQzbuMmvQ3Bo0XkEZT2RYIcSjbGEbBiWHFW7LRA1OZwV8bBrxsSwRKnlDtTOYTmyVkv0RugCI%2FPEhDwECi%2FVQ%3D%3D"

        val BusInfoLineName = findViewById<TextView>(R.id.tvBusInfoLineName)
        val DirUpDown = findViewById<TextView>(R.id.tvDirUpDown)
        val BusInfoLineKind = findViewById<TextView>(R.id.tvBusInfoLineKind)
        val RunTime = findViewById<TextView>(R.id.tvRunTime)
        val RunInterval = findViewById<TextView>(R.id.tvRunInterval)


        request = StringRequest(
            Request.Method.GET,
            url,
            {response ->
                // json object 타입으로 response를 받아주자! -> 우리가 받아오는 친구의 타입이 json object{}라서!

                // 전체 API
                val response1 = JSONObject(response)
                Log.d("response1",response1.toString())

                // 전체 API중 내가 필요한 JSONArray
                val response2 = response1.getJSONArray("LINE_LIST")
                Log.d("response2",response2.toString())

                // 테스트로 필요한 value에 접근해봄
                val test = response2[1] as JSONObject
                Log.d("test",test.getString("DIR_DOWN_NAME"))

                //
                for (i in 0 until response2.length()) {
                    val result = response2[i] as JSONObject
                    // equals(" ")에 들어갈 값 -> 사용자가 선택한 버스 값
                    if(result.getString("LINE_NAME").equals("수완03")){
                        // 버스 이름
                        BusInfoLineName.text = result.getString("LINE_NAME")
                        // 기점,종점
                        val dir_up_name = result.getString("DIR_UP_NAME")
                        val dir_down_name = result.getString("DIR_DOWN_NAME")
                        DirUpDown.text = StringBuilder(dir_up_name).append("~").append(dir_down_name)
                        // 무슨 종류? 간선 지선?
                        BusInfoLineKind.text = result.getString("LINE_KIND")
                        // Line Kind 코드 간결화를 위한 array
                        //val lk:Array<String> = arrayOf("1","2","3","4","5")
                        val lk:Array<String> = arrayOf("광주 급행 간선","광주 간선","광주 지선","광주 마을버스","광주 공항버스","지역버스")

                        for (i in 1 .. lk.size+1){
                            if(result.getString("LINE_KIND").equals(i.toString())){
                                BusInfoLineKind.text = lk[i]
                            }
                        }

                        // 첫차,막차
                        RunTime.text = StringBuilder(result.getString("FIRST_RUN_TIME")).append("~").append(result.getString("LAST_RUN_TIME"))
                        // 배차시간
                        RunInterval.text = StringBuilder(result.getString("RUN_INTERVAL")).append("분")

                    }


                }
            },
            {error ->

            }
        )//!!!! request 초기화 끝!!!!

        // 캐시 메모리 정리하고 요청을 queue에 담아주기
        request.setShouldCache(false)
        queue.add(request)
    }




    }
