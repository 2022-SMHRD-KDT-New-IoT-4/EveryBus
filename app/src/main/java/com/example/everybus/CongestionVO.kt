package com.example.busmo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class CongestionVO @JsonCreator constructor(
    @JsonProperty("line_name") val line_name : String,
    @JsonProperty("busstop_id") val busstop_id : String,
    @JsonProperty("busstop_name") val busstop_name : String,
    @JsonProperty("passenger_cnt") val passenger_cnt : String,
    @JsonProperty("reg_date") val reg_date : String,
    @JsonProperty("next_busstop") val next_busstop : String) {


}