package com.example.cardemo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object BleServer {
    val dataScope = CoroutineScope(Dispatchers.IO)
}