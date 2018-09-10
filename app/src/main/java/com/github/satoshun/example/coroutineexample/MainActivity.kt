package com.github.satoshun.example.coroutineexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import kotlin.coroutines.experimental.suspendCoroutine

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    retrofittest()

//    thread { exceptiontest() }

    launch {
      withContext(kotlin.coroutines.experimental.coroutineContext) {
      }
      async(CommonPool) { }
      val a = hoge3()
      val b = hoge4()
      println(a + b)
    }
  }
}

suspend fun master() {
  val a = hoge()
  val b = hoge2()
  println(a + b)
}

suspend fun hoge(): String {
  delay(1000)
  return "hoge"
}

suspend fun hoge2(): Int {
  delay(1000)
  return 100
}

suspend fun hoge3(): Int {
  delay(1000)
  return suspendCoroutine {
    println(it.context)
    it.resume(1000)
  }
}

suspend fun hoge4(): Int {
  delay(1000)
  return suspendCoroutine {
    println(it.context)
    it.resume(1000)
  }
}

