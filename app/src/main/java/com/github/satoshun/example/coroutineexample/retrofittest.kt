package com.github.satoshun.example.coroutineexample

import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun MainActivity.retrofittest() {
  val retrofit = Retrofit.Builder()
      .baseUrl("https://api.openweathermap.org/")
      .addConverterFactory(GsonConverterFactory.create(
        GsonBuilder().create())
      )
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()

  val rrr = retrofit.create(RRR::class.java)
  launch {
    delay(100)

//    rrr.get().await()
    Log.d("suspend", rrr.getWeather(10, "20").toString())
  }
}

interface RRR {
  @GET("data/2.5/weather")
  fun _getWeather(
    @Query("a1") a1: Int,
    @Query("a2") a2: String
  ): Deferred<Resp>
}

val RRR.getWeather get() = ::_getWeather.toSuspend

val <A1, A2, R> ((A1, A2) -> Deferred<R>).toSuspend
  get(): (suspend (A1, A2) -> R) = { a1, a2 -> this(a1, a2).await() }

class Resp
