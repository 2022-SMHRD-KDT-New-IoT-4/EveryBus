package com.example.everybus

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.everybus.CongestionInfoActivity
import com.example.everybus.CongestionSearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // activity_main.xml의 id값 찾아오기
        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val fl = findViewById<FrameLayout>(R.id.fl)

        // Splash 화면 띄워주고 바로 즐겨찾기 화면으로 !
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            BookmarkActivity()
        ).commit()


        // 하단바 버튼 클릭
        bnv.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.tab_bookmark){
                // 즐겨찾기 화면
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl,
                    BookmarkActivity()
                ).commit()
            }else if(item.itemId == R.id.tab_busSearch){
                // 버스 검색 화면
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl,
                    BusSearchActivity()
                ).commit()
            }else if(item.itemId==R.id.tab_stationSearch){
                // 정류장 검색 화면
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl,
                    StationSearch()
                ).commit()
            }else if (item.itemId == R.id.tab_busy){
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl,
                    CongestionSearchActivity()
                ).commit()
            }

            true
        }


    }
}