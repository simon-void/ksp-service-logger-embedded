package com.example

import org.springframework.stereotype.Service

@Service
class AClass(private val a: Int, val b: String, val c: Double) {
    val p = "$a, $b, $c"
    fun foo() = p
}