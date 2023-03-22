package com.example.everybus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Bus_Search2 : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_search_2)

        var rcClick = findViewById<RecyclerView>(R.id.rcClick)

        var busList = ArrayList<BusSearchVO>()

        // 데이터 넣어주기
        busList.add(BusSearchVO("일곡28", "살레시오고/ 교통문화연수원 방향", R.drawable.rightarrow))

        rcClick.setOnClickListener {
            var intent = Intent( this@Bus_Search2,
                BusRouteActivity::class.java )
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)

        }

       var adapter = BusSearchAdapter(this, busList)

        rcClick.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        rcClick.layoutManager = layoutManager



    }
}


