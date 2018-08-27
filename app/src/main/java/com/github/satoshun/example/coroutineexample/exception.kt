package com.github.satoshun.example.coroutineexample

import android.util.Log
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun MainActivity.exceptiontest() = runBlocking<Unit> {
  Thread.setDefaultUncaughtExceptionHandler { _, e ->
    Log.e("unhandled error", e.localizedMessage)
  }

  val job = launch {
    println("Throwing exception from launch")
    throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
  }

  job.join()

  val deferred = async {
    println("Throwing exception from async")
    throw ArithmeticException() // Nothing is printed, relying on user to call await
  }
  try {
    deferred.await()
    println("Unreached")
  } catch (e: ArithmeticException) {
    println("Caught ArithmeticException")
  }
}
