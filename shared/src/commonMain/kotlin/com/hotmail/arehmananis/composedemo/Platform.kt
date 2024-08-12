package com.hotmail.arehmananis.composedemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform