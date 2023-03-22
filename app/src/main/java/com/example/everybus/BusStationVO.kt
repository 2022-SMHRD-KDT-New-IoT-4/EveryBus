package com.example.everybus
data class BusStationVO (

    // 즐겨찾기 이미지
    var imgBS_bm : Int,
    //  버스 노선, 버스명 , 버스 방향
    var BSLineKind : String, var bsLineName:String, var BSNextBusStop : String,
    // 곧 도착 버스 시간정보, 몇 번째 정거장, 혼잡정보
    var BSRemainMin1 : String, var BSRemainStop1 : String, var BSConfusion1 : String,
    // 곧 도착 버스 승차벨 이미지
    var imgBS_rb : Int){
}