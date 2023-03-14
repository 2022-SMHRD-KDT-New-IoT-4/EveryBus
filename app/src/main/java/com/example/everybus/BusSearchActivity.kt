package com.example.everybus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BusSearchActivity : Fragment() {

// 버스 검색
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_bus_search, container, false)
        val rc3 = view.findViewById<RecyclerView>(R.id.rc2)
        var busSearchRecentlyList = ArrayList<BusSearchVO>()
        // 데이터 넣어주기
        busSearchRecentlyList.add(BusSearchVO("대촌69", "지선버스",R.drawable.star_bus_on))
        busSearchRecentlyList.add(BusSearchVO("마을760", "마을버스",R.drawable.star_bus))
        busSearchRecentlyList.add(BusSearchVO("1187", "지선버스",R.drawable.star_bus_on))

        // 버스 Adapter
        val adapter = BusSearchAdapter(requireActivity().applicationContext, busSearchRecentlyList )
        rc3.adapter = adapter
        rc3.layoutManager = LinearLayoutManager(this.context)



        return view
    }


}