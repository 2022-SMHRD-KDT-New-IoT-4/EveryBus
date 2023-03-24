package com.example.everybus


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import androidx.recyclerview.widget.RecyclerView



class BookmarkActivity : Fragment() {

    // 즐겨찾기
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var view = inflater.inflate(R.layout.fragment_bookmark, container, false)
        val rc2 = view.findViewById<RecyclerView>(R.id.rc)
        var bookmarkList = ArrayList<BookmarkVO>()


        // 데이터 넣어주기
        bookmarkList.add(
            BookmarkVO(
                R.drawable.bus,
                "일곡28",
                "광주 간선버스",
                "교통문화연수원 방향",
                R.drawable.ridebell
            )
        )
        bookmarkList.add(BookmarkVO(R.drawable.busstop, "송원대", "송암공단입구 방면", "", 0))

        // 즐겨찾기 Adapter
        // 없어도 되는 코드다
        val adapter = BookmarkAdapter(requireContext(), bookmarkList, object : BookmarkAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                if (context != null && !activity?.isFinishing!!) {
                    // 다이얼로그를 띄우는 코드
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("AlertDialog 제목")
                        .setMessage("AlertDialog 메시지")
                        .setPositiveButton("확인") { _, _ ->
                            // 확인 버튼을 눌렀을 때 처리하는 코드
                        }
                        .setNegativeButton("취소") { _, _ ->
                            // 취소 버튼을 눌렀을 때 처리하는 코드
                        }
                    builder.create().show()
                }else{
                    Log.d("ㅡㅡ","너무 화난다")
                }
            }
        })
        rc2.layoutManager = LinearLayoutManager(this.context)
        rc2.adapter = adapter

        return view

//        if (isAdded){
//            Log.d("들어왔니?", "들어왔어")
//            val myAlertBuilder = AlertDialog.Builder(activity)
//            // alert의 title과 Messege 세팅
//            myAlertBuilder.setTitle("Alert")
//            myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:")
//            // 버튼 추가 (Ok 버튼과 Cancle 버튼 )
//            myAlertBuilder.setPositiveButton("Ok",
//                DialogInterface.OnClickListener { dialog, which -> // OK 버튼을 눌렸을 경우
//                })
//            myAlertBuilder.setNegativeButton("Cancle",
//                DialogInterface.OnClickListener { dialog, which -> // Cancle 버튼을 눌렸을 경우
//                })
//            // Alert를 생성해주고 보여주는 메소드(show를 선언해야 Alert가 생성됨)
//            myAlertBuilder.show()
//        }


    }
}
