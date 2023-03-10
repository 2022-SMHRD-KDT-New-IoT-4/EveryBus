package com.example.everybus


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView



class BookmarkActivity : Fragment() {

    // 즐겨찾기
   override  fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View{
       var view = inflater.inflate(R.layout.fragment_bookmark, container, false)
       val rc2 = view.findViewById<RecyclerView>(R.id.rc)
       var bookmarkList = ArrayList<BookmarkVO>()
       // 데이터 넣어주기
       bookmarkList.add(BookmarkVO(R.drawable.bus,"송암74","동명회,송원고방면",R.drawable.ridebell))
       bookmarkList.add(BookmarkVO(R.drawable.station, "송원대학교","", R.drawable.burger))

      // 즐겨찾기 Adapter
       val adapter = BookmarkAdapter(requireActivity().applicationContext,bookmarkList)
       rc2.adapter = adapter
       rc2.layoutManager = LinearLayoutManager(this.context)
       return view
   }


}