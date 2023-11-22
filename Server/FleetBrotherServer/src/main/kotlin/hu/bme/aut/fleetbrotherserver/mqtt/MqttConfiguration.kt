package hu.bme.aut.fleetbrotherserver.mqtt

import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageProducer
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.integration.router.HeaderValueRouter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler


@Configuration
class MqttConfiguration(val conf: MqttParameters) {
    val logger = LoggerFactory.getLogger(MqttConfiguration::class.java)!!

    @Bean
    fun mqttClientFactory(): MqttPahoClientFactory {
        val factory = DefaultMqttPahoClientFactory()
        val connOptions = MqttConnectOptions()
        connOptions.serverURIs = arrayOf(conf.server)
        connOptions.isCleanSession = true

        factory.connectionOptions = connOptions
        return factory
    }

    @Bean
    fun mqttInputChannel(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    fun inbound(): MessageProducer {
        val adapter = MqttPahoMessageDrivenChannelAdapter(
                conf.serverId,
                mqttClientFactory(),
                conf.livez,
                conf.measurementz
        )
        adapter.setConverter(DefaultPahoMessageConverter())
        adapter.setQos(1)
        adapter.outputChannel = mqttInputChannel()
        return adapter
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    fun router(): HeaderValueRouter {
        val router = HeaderValueRouter(MqttHeaders.RECEIVED_TOPIC)
        router.setChannelMapping(conf.livez, "livez")
        router.setChannelMapping(conf.measurementz, "measurementz")
        return router
    }

    @Bean
    fun livez(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    @ServiceActivator(inputChannel = "livez")
    fun handleLivez(): MessageHandler {
        return MessageHandler {
            message -> logger.info("Livez: ${message.payload}")
        }
    }

    @Bean
    fun measurementz(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    @ServiceActivator(inputChannel = "measurementz")
    fun handleMeasurementz(): MessageHandler {
        return MessageHandler {
            message -> logger.info("Measurementz: ${message.payload}")
        }
    }
}