package com.firebase.pushnotification.servis

import com.firebase.pushnotification.model.NotificationDTO
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.IOException
import javax.annotation.PostConstruct

@Service
class NotificationService {

    @Value("\${service.firebase-config-file}")
    private lateinit var firebaseConfig: String

    var logger: Logger = LoggerFactory.getLogger(NotificationService::class.java)


    @PostConstruct
    private fun initFirebase() {

        val serviceAccount = FileInputStream(firebaseConfig)

        try {
            val options = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)

            } else {
                FirebaseApp.getInstance()
            }
        } catch (e: IOException) {
            logger.error("Fail Create FirebaseApp", e)
        }
    }

    fun sendToDevice(notification: NotificationDTO) {

        val message: Message = Message.builder()
                .setToken(notification.targetToken)
                .setNotification(Notification.builder()
                        .setTitle(notification.messageTitle)
                        .setBody(notification.messageBody).build())
                .putData("content", notification.messageTitle)
                .putData("body", notification.messageBody)
                .build()

        try {
            FirebaseMessaging.getInstance().send(message)
        } catch (e: FirebaseMessagingException) {
            logger.error("Fail to send notification to Device", e)
        }
    }
}