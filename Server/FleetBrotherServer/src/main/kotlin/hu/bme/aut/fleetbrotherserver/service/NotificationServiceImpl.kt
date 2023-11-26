package hu.bme.aut.fleetbrotherserver.service

import com.google.firebase.messaging.*
import hu.bme.aut.fleetbrotherserver.data.entities.AlertHistory
import hu.bme.aut.fleetbrotherserver.firebase.FirebaseParameters
import hu.bme.aut.fleetbrotherserver.service.interfaces.NotificationService
import jakarta.mail.MessagingException
import jakarta.mail.Session
import jakarta.mail.Transport
import jakarta.mail.internet.InternetAddress
import jakarta.mail.Message as JakartaMessage
import jakarta.mail.internet.MimeMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*


@Service
class NotificationServiceImpl(private val firebaseMessaging: FirebaseMessaging, private val firebaseParameters: FirebaseParameters): NotificationService {
    private val logger = LoggerFactory.getLogger(NotificationServiceImpl::class.java)!!

    override fun dispatchFirebaseAlert(alertHistory: AlertHistory) {
        val notification = Notification.builder()
                .setTitle(composeTitle())
                .setBody(composeMessage(alertHistory))
                .build()

        val message = Message.builder()
                .setNotification(notification)
                .setTopic(firebaseParameters.alertTopic)
                .build()

        firebaseMessaging.send(message)
        logger.info("An alert (history id: ${alertHistory.id}, alert id: ${alertHistory.alert.id}, " +
                "measurement id: ${alertHistory.measurement.id}) was dispatched to firebase clients.")
    }

    override fun dispatchEmailAlert(alertHistory: AlertHistory) {
        val props = Properties()
        props["mail.smtp.auth"] = false
        props["mail.smtp.host"] = "localhost"
        props["mail.smtp.port"] = "25"

        val session = Session.getInstance(props)
        try {
            val message = MimeMessage(session)

            message.setFrom("testfrom@test.com")
            message.setRecipient(JakartaMessage.RecipientType.TO, InternetAddress("testto@test.com"))
            message.subject = composeTitle()
            message.setText(composeMessage(alertHistory))

            Transport.send(message)
        } catch (e: MessagingException) {
            logger.error(e.message)
        }

        logger.info("An alert (history id: ${alertHistory.id}, alert id: ${alertHistory.alert.id}, " +
                "measurement id: ${alertHistory.measurement.id}) was dispatched to email clients.")
    }

    private fun composeTitle(): String {
        return "Measurement alert"
    }

    private fun composeMessage(alertHistory: AlertHistory): String {
        return "Measurement with id: ${alertHistory.measurement.id} triggered " +
                "an alarm: ${alertHistory.alert.name}"
    }
}