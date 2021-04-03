package com.inn.kafkaproducer.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

	  @Value("${spring.kafka.consumer.bootstrap-servers}")
	  private String bootstrapServers;
	
	  @Bean
	  public Properties producerConfigs() {
		  Properties props = new Properties();
		    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
		    return props;
	  }
	
	  @Bean
	  public KafkaProducer<String, byte[]> kafkaProducer(){
		  return new KafkaProducer<>(producerConfigs());
	  }
	
}
