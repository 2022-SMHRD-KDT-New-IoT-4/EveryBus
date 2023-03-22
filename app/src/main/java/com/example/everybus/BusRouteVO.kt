package com.example.everybus
// 버스 클릭했을 때 노선 정보 -> 리싸이클러뷰
// 참고 xml : bus_route.xml , bus_route.xml
// 시작노선이미지, 정류장명, 정류장번호, 버스 현위치 이미지(imgRouteBus), 버스 번호, 버스 혼잡도


data class BusRouteVO(var imgRoute : Int, var tvStation_subT : String, var tvStation_Num : String ,
                      var imgRouteBus: Int, var tvBRBusId : String?, var tvBRConfusion : String?) {


}