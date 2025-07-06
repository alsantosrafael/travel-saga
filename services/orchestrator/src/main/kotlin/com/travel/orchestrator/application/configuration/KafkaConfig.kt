package com.travel.orchestrator.application.configuration

import com.travel.flight_booking.avro.FlightBookedResponse
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootStrapServers: String

    @Value("\${kafka.consumer.group-id}")
    private lateinit var groupId: String

    @Value("\${spring.kafka.properties.schema.registry.url}")
    private lateinit var schemaRegistryUrl: String

    @Bean
    fun flightBookedResponseConsumerFactory(): ConsumerFactory<String?, FlightBookedResponse?> {
        val props: MutableMap<String?, Any?> = HashMap<String?, Any?>()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer::class.java)
        props.put("schema.registry.url", schemaRegistryUrl)
        props.put("specific.avro.reader", true)
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false)
        return DefaultKafkaConsumerFactory<String?, FlightBookedResponse?>(props)
    }

    @Bean(name = ["kafkaListenerBookedFlightResponse"])
    fun kafkaListenerBookedFlightResponse(): ConcurrentKafkaListenerContainerFactory<String?, FlightBookedResponse?> {
        val factory = ConcurrentKafkaListenerContainerFactory<String?, FlightBookedResponse?>()
        factory.setConsumerFactory(flightBookedResponseConsumerFactory())
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL)
        return factory
    }
}