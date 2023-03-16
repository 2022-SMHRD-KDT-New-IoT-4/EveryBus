package com.example.everybus

class BusStationVO (
    var tvBusId : String,
    var tvStopName : String,
    var tvRoute1 : String,

    // 즐겨찾기 이미지
    var imgBST_bm : Int,
    //  버스명 , 버스 방향
    var tvTitle : String, var tvSub : String,
    // 곧 도착 버스 시간정보, 몇 번째 정거장, 혼잡정보
    var tvTime1 : String, var tvAlive1 : String, var tvPO1 : String,
    // 2번 째로 빨리 오는 버스 정보
    var tvTime2 : String, var tvAlive2 : String, var tvPO2 : String,
    // 곧 도착 버스 승차벨 이미지
    var imgBST_rb : Int){
}