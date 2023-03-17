package com.example.everybus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class BusSearchActivity : Fragment() {

    // 버스 검색
    lateinit var queue: RequestQueue //Request 객체를 서버로 요청 보내는 역할
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bus_search, container, false)
        var bus_list_view = inflater.inflate(R.layout.bus_search_recently, container, false)
        val rcBusSearch = view.findViewById<RecyclerView>(R.id.rc2)
        var busList = ArrayList<BusSearchVO>()
        // 버스검색 textview
        val etBus = view.findViewById<EditText>(R.id.etStation)
        // 최근검색목록 -> 광주
        val tvRecently = view.findViewById<TextView>(R.id.textView3)

        // 1. Server에 요청해서 모든 버스정보 불러오기 JSON (ArrayList)

        val LineName = bus_list_view.findViewById<TextView>(R.id.tvLineName)
        val LineKind = bus_list_view.findViewById<TextView>(R.id.tvLineKind)


        queue = Volley.newRequestQueue(getActivity()?.applicationContext)

        val url = "http://59.3.122.229:8081/EveryBus/BusSearch"



        // 2. EditText에 addTextChangeLister 걸기
        // 3. onTextChange  메소드의 매개변수 (s) => 현재 EditText 에 적혀있는 글자 =>  Keyword
        // 4. onTextChange메소드 안에서  for 문 돌려가지고 1번에서 가져온 모든 버스정보 중 keyword와 일치하는 데이터
        // 5. 화면에 띄울 결과를 저장 할 temp (ArrayList)에 차곡차곡 누적
        // 6. temp (ArrayList)에 저장된 데이터로 RV 갱신~~





        // 데이터 넣어주기
//        busList.add(BusSearchVO_2("매월26", "어쩌고방향", R.drawable.star,R.drawable.rightarrow))
        busList.add(BusSearchVO("매월26", "월드컵경기장 방향",R.drawable.star_bus_on))

        // 버스 Adapter
        val adapter = BusSearchAdapter(requireActivity().applicationContext, busList )
        rcBusSearch.adapter = adapter
        rcBusSearch.layoutManager = LinearLayoutManager(this.context)

        etBus.setOnClickListener {
//            tvRecently.setText("광주")
            val intent = Intent(activity, Bus_Search2::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        return view
    }


}