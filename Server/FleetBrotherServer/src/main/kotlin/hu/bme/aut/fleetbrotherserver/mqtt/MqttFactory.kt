package hu.bme.aut.fleetbrotherserver.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MqttFactory(val conf: MqttConfiguration) {
    @Bean
    fun mqttClient() = MqttClient(conf.server, conf.serverId, MemoryPersistence())

    @Bean
    fun connectionOptions(): MqttConnectOptions {
        val conn = MqttConnectOptions()
        conn.userName = conf.userName
        conn.password = conf.password.toCharArray()
        return conn
    }
}