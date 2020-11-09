package com.firebase.pushnotification.model

data class NotificationDTO(
        val targetToken: String,
        val messageTitle: String,
        val messageBody: String
)