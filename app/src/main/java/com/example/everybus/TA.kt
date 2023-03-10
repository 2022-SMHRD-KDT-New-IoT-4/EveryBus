package com.example.everybus

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class TA : AsyncTask<String?, Void?, String?>() {
    var receiveMsg: String? = null


    override fun doInBackground(vararg strings: String?): String? {
        try {
            var str: String?

            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            val url = URL("http://59.3.122.229:8081/EveryBus/bus_info.jsp")
            // http://ip주소:포트번호/이클립스프로젝트명/WebContent아래폴더/androidDB.jsp
            val conn = url.openConnection() as HttpURLConnection
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            conn.requestMethod = "POST"
            val osw = OutputStreamWriter(conn.outputStream, "UTF-8")

            //jsp와 통신 성공 시 수행
            if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                val tmp = InputStreamReader(conn.inputStream, "UTF-8")
                val reader = BufferedReader(tmp)
                val buffer = StringBuffer()

                // jsp에서 보낸 값을 받는 부분
                while (reader.readLine().also { str = it } != null) {
                    buffer.append(str)
                }
                receiveMsg = buffer.toString()
            } else {
                // 통신 실패
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //jsp로부터 받은 리턴 값
        return receiveMsg
    }




}