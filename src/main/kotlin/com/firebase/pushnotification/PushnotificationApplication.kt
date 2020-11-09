package com.firebase.pushnotification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PushnotificationApplication

fun main(args: Array<String>) {
    runApplication<PushnotificationApplication>(*args)
}
