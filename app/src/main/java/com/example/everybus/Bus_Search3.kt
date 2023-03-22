package com.example.everybus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 정류장 하나 클릭했을 때 뜨는 화면
// bus_search_3.xml
class Bus_Search3 : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_search_3)

        // 여기에 정류장 노선 정보 뜸
        val busStationRoute = findViewById<RecyclerView>(R.id.busStationRoute)

        var busRouteList = ArrayList<BusRouteVO>()

        var imgHome = findViewById<ImageButton>(R.id.imgHome)

        // 데이터 넣어주기
        // _null : 빈 화면
        busRouteList.add(BusRouteVO(R.drawable.start_arrow, "양동시장역(천변)", "2170", R.drawable.bus_red))
        busRouteList.add(BusRouteVO(R.drawable.arrow, "양동시장역(북)", "2167", R.drawable.bus_green))
        busRouteList.add(BusRouteVO(R.drawable.arrow, "돌고개역(동)", "2164", R.drawable.bus_green))

        busStationRoute.setOnClickListener {
            var intent = Intent(this@Bus_Search3,
            BusStationActivity::class.java)
            startActivity(intent)
        }



        imgHome.setOnClickListener {
            var intent = Intent(this@Bus_Search3, MainActivity::class.java)
            startActivity(intent)
        }

        // 버스 노선 Adapter 연결
        val adapter = BusRouteAdapter(this, busRouteList)
        busStationRoute.adapter =adapter
        val layoutManager = LinearLayoutManager(this)
        busStationRoute.layoutManager = layoutManager



    }
}