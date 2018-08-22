package com.github.satoshun.example.coroutineexample

interface Hoge {
  fun hoge()

  fun hoge2() {
    println(100)
  }
}

class Hoge2 : Hoge {
  override fun hoge() {
  }

  override fun hoge2() {
  }
}


fun a(hoge: Hoge) {
  hoge.hoge2()
}

fun a2(hoge: Hoge2) {
  hoge.hoge2()
}
