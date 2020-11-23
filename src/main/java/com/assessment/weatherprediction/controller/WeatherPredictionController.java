package com.assessment.weatherprediction.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.weatherprediction.exception.ApplicationException;
import com.assessment.weatherprediction.model.SuggestionResponse;
import com.assessment.weatherprediction.service.PredictionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WeatherPredictionController {

	org.apache.logging.log4j.Logger log = LogManager.getLogger(WeatherPredictionController.class);

	@Autowired
	PredictionService service;

	@GetMapping("/predict/{city}")
	public @ResponseBody SuggestionResponse predict(@PathVariable String city) {
		SuggestionResponse returnValue = null;
		log.info("predict() city : {}", city);
		try {
			returnValue = service.predict(city);
			log.info("returnValue: " + returnValue);
		} catch (ApplicationException | IOException e) {
			log.error("Exception :" + e);
			e.printStackTrace();
		} catch (URISyntaxException e) {
			log.error("Exception :" + e);
			e.printStackTrace();
		}
		return returnValue;
	}

}
