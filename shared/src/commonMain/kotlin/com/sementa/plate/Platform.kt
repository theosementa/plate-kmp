package com.sementa.plate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform