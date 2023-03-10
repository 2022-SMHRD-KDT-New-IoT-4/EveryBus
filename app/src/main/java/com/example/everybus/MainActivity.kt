package com.example.everybus

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var registerBtn: Button? = null
    var idet: EditText? = null
    var pwet: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "mysql"
        registerBtn = findViewById<View>(R.id.register_btn) as Button
        idet = findViewById<View>(R.id.register_id) as EditText
        pwet = findViewById<View>(R.id.register_pw) as EditText
        registerBtn!!.setOnClickListener {
            try {
                Toast.makeText(this@MainActivity, "버튼눌림", Toast.LENGTH_SHORT).show()
                val result: String?
                val id = idet!!.text.toString()
                val pw = pwet!!.text.toString()
                val task = TA()
                result = task.execute(id,pw).get()
            } catch (e: Exception) {
                Log.i("DBtest", ".....ERROR.....!")
            }
        }
    }
}