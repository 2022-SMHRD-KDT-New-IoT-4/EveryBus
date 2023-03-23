package com.example.everybus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.TimePicker.OnTimeChangedListener
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class CongestionSearchActivity : Fragment()  {

    lateinit var queue: RequestQueue //Request 객체를 서버로 요청 보내는 역할
    lateinit var request: StringRequest
    lateinit var response_JSON: JSONArray



    // 버스 검색
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_congestion_search_activity, container, false)

        val etBusName = view.findViewById<AutoCompleteTextView>(R.id.etBusName)
        val etBusStop = view.findViewById<AutoCompleteTextView>(R.id.etBusstop)
        val tp = view.findViewById<TimePicker>(R.id.tp)
        val btnSearch = view.findViewById<Button>(R.id.btnSearch)

        queue = Volley.newRequestQueue(getActivity()?.applicationContext)

        btnSearch.setOnClickListener {
            val intent = Intent(getActivity(), CongestionInfoActivity::class.java)
            startActivity(intent)
        }

        // 이건 서버 URL
        val url = "http://115.23.24.235:8082/EveryBus/congestionBusSearch"
        etBusName.setOnClickListener {

            // StringRequest에 url담아주고 GET방식으로 가져온다?
            // request에 들어가는 매개변수 4개
            // 1) 데이터 전송 방법(GET/POST)
            // 2) 요청할 서버주소
            // 3) 응답 성공
            // 4) 응답 실패
            request = StringRequest(
                Request.Method.POST,
                url,
                // 응답에 성공했을 때 Listener
                { response ->
                    response_JSON = JSONArray(response)
                    val busNames = response_JSON.getJSONObject(0)
                    Log.d("result", busNames.toString())
                    Log.d("result", busNames.getString("line_name"))
                    val list = mutableListOf<JSONArray>(response_JSON)
                    busNames.getString("line_name").length

                    // tvDown.text = response.getString("dir_down_name").toString()
                    // response(결과값)의 자료형은 String!(StringRequest를 사용했기 때문에!
                    // 받아온 결과값을 TextView에 적용(setText를 진행한다)

                    etBusName.addTextChangedListener(object : TextWatcher{
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            // 3. onTextChange  메소드의 매개변수 (s) => 현재 EditText 에 적혀있는 글자 =>  Keyword
                            // 4. onTextChange메소드 안에서  for 문 돌려가지고 1번에서 가져온 모든 버스정보 중 keyword와 일치하는 데이터
                            Log.d("TAG", "afterTextChanged: $p0")
                            // 자동검색 Adapter
                            // 어쩌고 엑티비티, 어떤 디자인, 어떤 resources
                            val adapter = ArrayAdapter(requireActivity().applicationContext, android.R.layout.simple_list_item_1, list)
                            etBusName.setAdapter(adapter)

                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }
                    })
                },
                // 응답에 실패했을 때에 대한 ErrorListener
                { error ->
                    Toast.makeText(
                        getActivity(), "오류내용 : $error",
                        Toast.LENGTH_SHORT
                    ).show()
                    // error(어떤 오류가 들어왔는지)
                    // 토스트 창으로 "응답 실패" 띄워주기
                })// !!!request 초기화 끝!!!

            // 요청이 여러개면 캐시가 쌓인다! 그래서 캐시 메모리 정리하기!
            request.setShouldCache(false)
            // 내가 만든 요청을 RequestQueue에다가 넣어주자
            queue.add(request)
        }

        // 1. Server에 요청해서 모든 버스정보 불러오기 JSON (ArrayList)
        // 2. EditText에 addTextChangeLister 걸기

        // 5. 화면에 띄울 결과를 저장 할 temp (ArrayList)에 차곡차곡 누적
        // 6. temp (ArrayList)에 저장된 데이터로 RV 갱신~~

        // strings.xml 샘플코드 해보기 ^^..............
        // val bus = resources.getStringArray(R.array.bus_array)

        val spf = activity?.getSharedPreferences("SPF", Context.MODE_PRIVATE)
        val editor = spf?.edit()

        tp.setOnTimeChangedListener(OnTimeChangedListener { timePicker, hour, min ->
//            Toast.makeText(
//                getActivity(),
//                "hour : $hour, min : $min",
//                Toast.LENGTH_LONG
//            ).show()

            Log.d("tag4", hour.toString())
            Log.d("tag4", min.toString())

            editor?.putString("hour", hour.toString())?.commit()
            editor?.putString("min", min.toString())?.commit()
        })

        return view
    }

}