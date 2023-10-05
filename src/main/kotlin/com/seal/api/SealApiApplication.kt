package com.seal.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SealApiApplication

fun main(args: Array<String>) {
    runApplication<SealApiApplication>(*args)
}