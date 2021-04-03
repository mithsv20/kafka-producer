package com.inn.kafkaproducer.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inn.kafkaproducer.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerRest {

	private static Logger logger = LoggerFactory.getLogger(KafkaProducerRest.class);

	@Autowired
	KafkaProducerService kafkaProducerService;
	
	@PostMapping(value = "/syncFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> syncFiles(@RequestParam("topic") String topic,
											@RequestParam("fileName") String fileName,
											@RequestParam("file") MultipartFile file) throws IOException {

		try {
			kafkaProducerService.syncFile(topic, file);
		} catch (Exception e) {
			String errorMsg = "Error in producing message with topic " + topic + " : ";
			logger.error(errorMsg + e);
			return new ResponseEntity<>(errorMsg + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		return  new ResponseEntity<>("Message Produces Sucessfully with topic " + topic + " ...!", HttpStatus.OK);
	}

	@PostMapping(value = "/syncMessage", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> syncFiles(@RequestParam("topic") String topic,
											@RequestBody String data) {
		try {
			kafkaProducerService.syncMessage(topic, data);
		} catch (Exception e) {
			String errorMsg = "Error in producing message with topic " + topic + " : ";
			logger.error(errorMsg);
			return  new ResponseEntity<>(errorMsg + e.getStackTrace(), HttpStatus.NOT_ACCEPTABLE);
		}
		return  new ResponseEntity<>("Message Produces Sucessfully with topic " + topic + " ...!", HttpStatus.OK);
	}

}
