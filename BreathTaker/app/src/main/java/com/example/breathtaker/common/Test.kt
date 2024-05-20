package com.example.breathtaker.common

import kotlin.math.sin


fun main() {
    val progress = arrayOf<Double>(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0)
    for (p in progress) {
        print("${sin(p)} \n")
    }
}