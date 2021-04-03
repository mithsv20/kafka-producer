package com.inn.kafkaproducer.service;

import java.io.IOException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inn.kafkaproducer.config.KafkaConfig;
import com.inn.kafkaproducer.utils.KafkaUtils;

@Service
public class KafkaProducerService {

	private static Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafkaConfig kafkaConfig;
	
	public void syncFile(String topic, MultipartFile file) throws Exception {

		byte[] fileData = KafkaUtils.convertToByteArray(file.getInputStream());
		logger.info("file Data converterd");
		ProducerRecord<String, byte[]> keyedMessage = new ProducerRecord<>(topic, fileData);
		logger.info("getting keyedMessage {} ", keyedMessage);
		
		kafkaConfig.kafkaProducer().send(keyedMessage);
		logger.info("data sent in kafka topic : {}", topic);
		
	}

	public void syncMessage(String topic, String data) throws IOException {
		kafkaTemplate.send(topic, data);		
		logger.info("data sent in kafka topic : {}", topic);
	}

	
}
