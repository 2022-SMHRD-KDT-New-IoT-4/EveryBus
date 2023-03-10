package com.example.everybus

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var registerBtn: Button? = null
    var idet: EditText? = null
    var pwet: EditText? = null
    // activity_main.xml의 id값 찾아오기
    val bnv = findViewById<BottomNavigationView>(R.id.bnv)
    val fl = findViewById<FrameLayout>(R.id.fl)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "mysql"

        registerBtn!!.setOnClickListener {
            try {
                Toast.makeText(this@MainActivity, "버튼눌림", Toast.LENGTH_SHORT).show()
                val result: String?
                val id = idet!!.text.toString()
                val pw = pwet!!.text.toString()
                val task = TA()
                result = task.execute(id,pw).get()
            } catch (e: Exception) {
                Log.i("DBtest", ".....ERROR.....!")
            }
        }
        // 하단바 버튼 클릭
        bnv.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.tap_bookmark){
                // 즐겨찾기 화면
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl,
                    BookmarkActivity()
                ).commit()
            }else if(item.itemId == R.id.tap_busSearch){
                // 버스 검색 화면
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl,
                    BusSearchActivity()
                ).commit()
            }

            true
        }


    }
}