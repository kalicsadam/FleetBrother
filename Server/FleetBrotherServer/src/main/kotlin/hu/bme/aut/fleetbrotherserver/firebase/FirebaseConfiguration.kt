package hu.bme.aut.fleetbrotherserver.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FirebaseConfiguration(val firebaseParams: FirebaseParameters) {
    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }

    @Bean
    fun firebaseApp(credentials: GoogleCredentials): FirebaseApp {
        val options: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build()

        return if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options)
        } else {
            FirebaseApp.getApps().first()
        }
    }

    @Bean
    fun googleCredentials(): GoogleCredentials {
        val credentialsFile = ClassPathResource(firebaseParams.credentialsFile)

        return GoogleCredentials.fromStream(credentialsFile.inputStream)
    }
}