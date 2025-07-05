package com.travel.flight_booking.application.configuration;

import com.travel.flight_booking.avro.BookFlightCommand;
import com.travel.flight_booking.avro.FlightBookedResponse;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootStrapServers;

	@Value("${group.id}")
	private String groupId;

	@Value("${spring.kafka.properties.schema.registry.url}")
	private String schemaRegistryUrl;

	@Bean
	public ConsumerFactory<String, BookFlightCommand> bookFlightCommandConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
		props.put("schema.registry.url", schemaRegistryUrl);
		props.put("specific.avro.reader", true);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConsumerFactory<String, FlightBookedResponse> flightBookedResponseConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
		props.put("schema.registry.url", schemaRegistryUrl);
		props.put("specific.avro.reader", true);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, BookFlightCommand> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, BookFlightCommand> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(bookFlightCommandConsumerFactory());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FlightBookedResponse> kafkaListenerBookedFlightResponse() {
		ConcurrentKafkaListenerContainerFactory<String, FlightBookedResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(flightBookedResponseConsumerFactory());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		return factory;
	}
}