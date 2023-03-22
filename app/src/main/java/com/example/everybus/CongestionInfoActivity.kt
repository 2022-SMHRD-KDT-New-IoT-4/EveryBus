package com.example.busmo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.everybus.R
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONArray


class CongestionInfoActivity : AppCompatActivity(){

    lateinit var queue: RequestQueue //Request 객체를 서버로 요청 보내는 역할
    lateinit var request: StringRequest
    lateinit var adapter: CongestionAdapter
    var data = ArrayList<CongestionVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congestion_info)

        queue = Volley.newRequestQueue(this)

        val Crc = findViewById<RecyclerView>(R.id.Crc)
        val tvCBusstopName = findViewById<TextView>(R.id.tvCBusstopName)
        val tvCNextBusstop = findViewById<TextView>(R.id.tvCNextBusstop)
        val tvCBusstopId = findViewById<TextView>(R.id.tvCBusstopId)
        val btnCHome = findViewById<ImageButton>(R.id.btnCHome)
        val btnCBack = findViewById<ImageButton>(R.id.btnCBack)

        val spf = this.getSharedPreferences("SPF", Context.MODE_PRIVATE)

        val hour = spf.getString("hour", "00")
        val min = spf.getString("min", "00")

        Log.d("real", hour!!)
        Log.d("real", min!!)

//        btnCHome.setOnClickListener {
//            val intent = Intent(this@CongestionInfoActivity, MainActivity::class.java)
//                startActivity(intent)
//        }
//        btnCBack.setOnClickListener {
//            val intent = Intent(this@CongestionInfoActivity, CongestionSearchActivity::class.java)
//            startActivity(intent)
//        }

        val url = "http://115.23.24.235:8082/EveryBus/CongestionInfo"

        request = StringRequest(
            Request.Method.GET,
            url,
            {response ->
                Log.d("tag", response)
                val result = JSONArray(response)
                Log.d("response1", result.toString())
                Log.d("response1", result[0].toString())
                Log.d("response1", result.getString(0)[6].toString())

                var cnt : String
                val mapper = ObjectMapper()
                for(i  in 0 until result.length()){
                    val item = mapper.readValue(result[i].toString(), CongestionVO::class.java)
                    if(item.passenger_cnt.toInt() <= 20){
                        cnt = item.passenger_cnt.replace(item.passenger_cnt,"여유")
                    }else if(item.passenger_cnt.toInt() <= 35){
                        cnt = item.passenger_cnt.replace(item.passenger_cnt,"보통")
                    }else if(item.passenger_cnt.toInt() <= 50){
                        cnt = item.passenger_cnt.replace(item.passenger_cnt,"혼잡")
                    }else{
                        cnt = item.passenger_cnt.replace(item.passenger_cnt,"매우 혼잡")
                    }

                    data.add(CongestionVO(item.line_name, item.busstop_id, item.busstop_name, cnt, item.reg_date.substring(6 until item.reg_date.length-3), item.next_busstop))
                    Log.d("tag", data.toString())
                    tvCBusstopId.setText(item.busstop_id)
                    tvCBusstopName.setText(item.busstop_name)
                    tvCNextBusstop.setText(item.next_busstop + " 방면")
                }
                adapter.notifyDataSetChanged()
            },
            {error ->

            }
        )//!!!! request 초기화 끝!!!!
        // 캐시 메모리 정리하고 요청을 queue에 담아주기
        request.setShouldCache(false)
        queue.add(request)

        adapter = CongestionAdapter(this, data)

        Crc.adapter = adapter
        Crc.layoutManager = LinearLayoutManager(this)
    }
}

