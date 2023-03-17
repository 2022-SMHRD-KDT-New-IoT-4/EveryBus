package com.example.everybus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StationSearch : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_station_search, container, false)
        val rcStation = view.findViewById<RecyclerView>(R.id.rcStation)
        var StationList = ArrayList<StationSearchVO>()
        // 검색창
        var etStation = view.findViewById<EditText>(R.id.etStation)

        StationList.add(StationSearchVO("CBS방송국","상무중 방면","2013"))
        StationList.add(StationSearchVO("송원대","동명회.송원고 방면","3162"))
        StationList.add(StationSearchVO("금호시영아파트","금호한국아파트 방면","2010"))


        val adapter = StationSearchAdapter(requireActivity().applicationContext, StationList )
        rcStation.adapter = adapter
        rcStation.layoutManager = LinearLayoutManager(this.context)

        etStation.setOnClickListener {
            val intent = Intent(activity, BusStation::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }


        return view
    }
}