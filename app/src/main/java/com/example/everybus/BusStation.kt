package com.example.everybus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BusStation: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bus_station)



        var rcStation1 = findViewById<RecyclerView>(R.id.rcStation1)



        var busList = ArrayList<BusStationVO>()


        busList.add(BusStationVO("송암72","송암공단","10분","20분","3번쨰전","4번째전","혼잡","여유"))
        busList.add(BusStationVO("금호36","cbs방송국","10분","20분","3번쨰전","4번째전","혼잡","여유"))
        busList.add(BusStationVO("금호46","cbs방송국","10분","20분","3번쨰전","4번째전","혼잡","여유"))

        val adapter = busStationAdapter(applicationContext,busList)

        rcStation1.adapter = adapter
        rcStation1.layoutManager = GridLayoutManager(this,1)












    }
}