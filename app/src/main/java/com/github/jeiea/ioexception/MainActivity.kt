package com.github.jeiea.ioexception

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    btn_fire.setOnClickListener {
      GlobalScope.launch(Dispatchers.Main) {
        request()
      }
    }
  }

  suspend fun request() {
    val http = HttpClient(OkHttp.create {})
    val job = GlobalScope.launch(Dispatchers.IO) {
      http.get<String>("http://nghttp2.org/httpbin/delay/10")
    }
    delay(1000)
    job.cancel()
    delay(9000)
    btn_fire.text = "CLEAR"
  }
}
