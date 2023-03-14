package com.example.everybus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
// 안드로이드에서 db값 받아오기 내가 만든 소스코드

class TestActivity : AppCompatActivity() {
    // 초기화는 onCreate안에서 할거니까 전역변수 선언해줄 땐 초기화를 늦추는 lateinit
    lateinit var queue: RequestQueue //Request 객체를 서버로 요청 보내는 역할
    // 요청과 응답에 대한 로직(기능)을 담고 있는 객체
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // 이어져 있는 xml은 activity_test.xml

        // Volley 동작 순서
        // 1. View의 id값 찾아오기
        val btnTest = findViewById<Button>(R.id.btnTest)
        val tvLineId = findViewById<TextView>(R.id.tvLineId)
        val tvLineName = findViewById<TextView>(R.id.tvLineName)
        val tvUp = findViewById<TextView>(R.id.tvUp)
        val tvDown = findViewById<TextView>(R.id.tvDown)
        val tvLineKind = findViewById<TextView>(R.id.tvLineKind)
        val etLineName = findViewById<EditText>(R.id.etLineName)


        // 2.통신을 위해서 필요한 객체 2개 만들어 놓기
        // Request, RequestQueue
        // onCreate바깥쪽에서 가져온다!(전역변수로 만든다!)
        // 2-1. RequestQueue 초기화! 요청이 담길 공간(Queue)은 한 번만 만들어도 된다!
        // 초기화가 처음이라면! == Queue가 null 이라면! -> Queue를 초기화!
        queue = Volley.newRequestQueue(applicationContext)

        // 3-1. btnSend를 눌렀을 때
        // 3-2. EditText에 적혀있는 Url값을 가져온다
        // 3-3. 실제 요청하고 싶은 내용을 Request객체에 작성한다
        // 3-4. 응답을 받아왔을 때에 대한 코드를 작성한다(성공/실패)
        val url = "http://59.3.122.229:8081/EveryBus/Bus_info"
        btnTest.setOnClickListener {

            // StringRequest에 url담아주고 GET방식으로 가져온다?
            // request에 들어가는 매개변수 4개
            // 1) 데이터 전송 방법(GET/POST)
            // 2) 요청할 서버주소
            // 3) 응답 성공
            // 4) 응답 실패
            val request = object: StringRequest(
                Request.Method.POST,
                url,
                // 응답에 성공했을 때 Listener
                { response ->
                    val response = JSONObject(response)
                    // "jsonTest" -> 이클립스에서 보내준 결과값이 담긴 곳
                    // 소스코드의 경우 controller의 Bus_info.java에 있음
                    // 로그로 확인하기 위한 코드!
                    Log.d("jsonTest",response.toString())

                    // json형식의 데이터 key값에 접근해서 value값 뽑아오는 로직!
                    Log.d("line_id",response.getString("line_id").toString())

                    // xml에 있는 TextView의 값 바꿔주는 코드!
                    // response(결과값)의 자료형은 String!(StringRequest를 사용했기 때문에!
                    // 받아온 결과값을 TextView에 적용(setText를 진행한다)

                    tvLineKind.text = response.getString("line_kind").toString()
                    tvLineId.text = response.getString("line_id").toString()
                    tvLineName.text = response.getString("line_name").toString()
                    tvUp.text = response.getString("dir_up_name").toString()
                    tvDown.text = response.getString("dir_down_name").toString()

                },
                // 응답에 실패했을 때에 대한 ErrorListener
                { error ->
                    Toast.makeText(
                        this@TestActivity, "오류내용 : $error",
                        Toast.LENGTH_SHORT
                    ).show()
                    // error(어떤 오류가 들어왔는지)
                    // 토스트 창으로 "응답 실패" 띄워주기
                })// !!!request 초기화 끝!!!

            /////////////////////////////////////////////////////////////////////////

            // 안드로이드에서 입력한 값 집어넣기 -> 안드로이드에서 입력하는 값이 없으면 작성 안 해도 됨!


            { @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["LineName"] = etLineName.text.toString()

                Log.d("여기야",params.toString())
                return params }

            }
            ///////////////////////////////////////////////////////////////////
            queue.add(request)
            // 요청이 여러개면 캐시가 쌓인다! 그래서 캐시 메모리 정리하기!
            request.setShouldCache(false)
            // 내가 만든 요청을 RequestQueue에다가 넣어주자
        }
    }
}