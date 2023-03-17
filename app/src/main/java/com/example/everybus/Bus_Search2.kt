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
        busList.add(BusSearchVO("송암 74", "금호모아아파트 방향", R.drawable.rightarrow))

        rcClick.setOnClickListener {
            var intent = Intent( this@Bus_Search2,
                Bus_Search2::class.java )
            startActivity(intent)

        }

       var adapter = BusSearchAdapter(this, busList)

        rcClick.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        rcClick.layoutManager = layoutManager



    }
}


