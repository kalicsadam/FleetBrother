package hu.bme.aut.fleetbrotherserver.mqtt

import hu.bme.aut.fleetbrotherserver.service.interfaces.LivezHandlerService
import hu.bme.aut.fleetbrotherserver.service.interfaces.MeasurementzHandlerService
import org.eclipse.paho.mqttv5.client.*
import org.eclipse.paho.mqttv5.client.persist.MqttDefaultFilePersistence
import org.eclipse.paho.mqttv5.common.MqttMessage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageProducer
import org.springframework.integration.mqtt.core.*
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.outbound.Mqttv5PahoMessageHandler
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.integration.router.HeaderValueRouter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

@Configuration
class MqttConfiguration(val conf: MqttParameters) {
    @Bean
    fun clientManager(): ClientManager<IMqttAsyncClient, MqttConnectionOptions> {
        val mqttConnectionOptions = MqttConnectionOptions()
        mqttConnectionOptions.serverURIs = arrayOf(conf.server)
        mqttConnectionOptions.isCleanStart = true
        mqttConnectionOptions.isAutomaticReconnect = true

        val clientManager = Mqttv5ClientManager(mqttConnectionOptions, conf.serverId)
        clientManager.setPersistence(MqttDefaultFilePersistence())
        return clientManager
    }

    @Bean
    fun inboundChannelAdapter(clientManager: ClientManager<IMqttAsyncClient, MqttConnectionOptions>): MessageProducer {
        val adapter = Mqttv5PahoMessageDrivenChannelAdapter(
                clientManager,
                conf.channels.livez,
                conf.channels.measurementz,
                //conf.channels.remoteProcResult
        )
        adapter.setQos(1)
        adapter.setPayloadType(MqttMessage::class.java)
        adapter.outputChannel = mqttInputChannel()
        return adapter
    }

    @Bean
    fun mqttInputChannel(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    fun router(): HeaderValueRouter {
        val router = HeaderValueRouter(MqttHeaders.RECEIVED_TOPIC)
        router.setChannelMapping(conf.channels.livez, "livez")
        router.setChannelMapping(conf.channels.measurementz, "measurementz")
        //router.setChannelMapping(conf.channels.remoteProcResult, "remoteProcResult")
        return router
    }

    @Bean
    fun livez(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    @ServiceActivator(inputChannel = "livez")
    fun handleLivez(livezHandlerService: LivezHandlerService): MessageHandler {
        return livezHandlerService
    }

    @Bean
    fun measurementz(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    @ServiceActivator(inputChannel = "measurementz")
    fun handleMeasurementz(measurementzHandlerService: MeasurementzHandlerService): MessageHandler {
        return measurementzHandlerService
    }

//    @Bean
//    fun remoteProcResult(): MessageChannel {
//        return DirectChannel()
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "remoteProcResult")
//    fun handleRemoteProcResult(): MessageHandler {
//        return MessageHandler {
//            message -> logger.info("Remote Proc Result: ${message.payload}")
//        }
//    }

    @Bean
    fun mqttOutboundChannel(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    fun mqttOutbound(clientManager: ClientManager<IMqttAsyncClient, MqttConnectionOptions>): MessageHandler {
        val messageHandler = Mqttv5PahoMessageHandler(clientManager)
        messageHandler.setAsync(true)
        messageHandler.setDefaultQos(1)
        return messageHandler
    }
}