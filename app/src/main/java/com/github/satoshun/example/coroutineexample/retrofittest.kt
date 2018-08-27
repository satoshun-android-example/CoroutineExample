package com.github.satoshun.example.coroutineexample

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

fun MainActivity.retrofittest() {
  val retrofit = Retrofit.Builder()
      .baseUrl("http://api.openweathermap.org/  ")
      .addConverterFactory(GsonConverterFactory.create(
          GsonBuilder().create())
      )
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()

  val rrr = retrofit.create(RRR::class.java)
  launch {
    delay(100)
    rrr.get().await()
  }
}

interface RRR {
  @GET("data/2.5/weather")
  fun get(): Deferred<Resp>
}

class Resp
