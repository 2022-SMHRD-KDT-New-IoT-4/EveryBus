package com.example.everybus

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 어플 실행했을 때 3초간 뜨는 화면
        // 그 다음 BoomarkActivity로 이동
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity,
            MainActivity::class.java)
            startActivity(intent)
            finish()
        },
        3000)
    }

}