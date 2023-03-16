package com.example.everybus

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class BusRouteActivity : AppCompatActivity() {
    // 통신을 위한 request
    lateinit var queue: RequestQueue
    lateinit var request: StringRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_route)
        // AdapterView 사용 6단계
        // 1. Container 결정 :

        // 2. Template 결정(xml을 만드는 단계) : bus_route.xml
        // 3. Item 결정 :


        val BusInfo = findViewById<View>(R.id.vBusInfo)
        val tvRouteLineKind = findViewById<TextView>(R.id.tvRouteLineKind)
        val tvRouteLineName = findViewById<TextView>(R.id.tvRouteLineName)
        val tvRouteDirUpName = findViewById<TextView>(R.id.tvRouteDirUpName)
        val tvRouteDirDownName = findViewById<TextView>(R.id.tvRouteDirDownName)

        queue = Volley.newRequestQueue(applicationContext)

        val url = "http://59.3.122.229:8081/EveryBus/Route_Bus_info"

        request = StringRequest(
            Request.Method.POST,
            url,
            // 응답에 성공했을 때 Listener
            { response ->
                val response = JSONObject(response)
                Log.d("jsonTest",response.toString())

                Log.d("line_id",response.getString("line_id").toString())

                val line_kind = response.getString("line_kind").toString()

                if(line_kind.equals("1")){
                    tvRouteLineKind.text = "급행 간선"
                    BusInfo.setBackgroundColor(Color.parseColor("#FF6347"))
                }else if(line_kind.equals("2")){
                    tvRouteLineKind.text = "간선"
                    BusInfo.setBackgroundColor(Color.parseColor("#FFD700"))
                }else if(line_kind.equals("3")){
                    tvRouteLineKind.text = "지선"
                    BusInfo.setBackgroundColor(Color.parseColor("#04A80C"))
                }else if(line_kind.equals("4")){
                    tvRouteLineKind.text = "마을버스"
                    BusInfo.setBackgroundColor(Color.parseColor("#04A80C"))
                }else if(line_kind.equals("5")){
                    tvRouteLineKind.text = "공항버스"
                    BusInfo.setBackgroundColor(Color.parseColor("mainColor"))
                }else if(line_kind.equals("6")){
                    tvRouteLineKind.text = "지역버스"
                    BusInfo.setBackgroundColor(Color.parseColor("#C0C0C0"))
                }
                tvRouteLineName.text = response.getString("line_name").toString()
                tvRouteDirUpName.text = response.getString("dir_up_name").toString()
                tvRouteDirDownName.text = response.getString("dir_down_name").toString()
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

        // 요청이 여러개면 캐시가 쌓인다! 그래서 캐시 메모리 정리하기!
        request.setShouldCache(false)
        // 내가 만든 요청을 RequestQueue에다가 넣어주자
        queue.add(request)



    }
}