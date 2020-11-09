package com.firebase.pushnotification.controller

import com.firebase.pushnotification.model.NotificationDTO
import com.firebase.pushnotification.servis.NotificationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/notification")
@RestController
class NotificationController(private val notificationService: NotificationService) {

    @PostMapping("/send-to-device")
    private fun sendToDevice(@RequestBody notification: NotificationDTO) {
        return notificationService.sendToDevice(notification)
    }
}