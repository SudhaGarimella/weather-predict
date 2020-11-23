package com.assessment.weatherprediction.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assessment.weatherprediction.exception.ApplicationException;
import com.assessment.weatherprediction.model.ClimateForDay;
import com.assessment.weatherprediction.model.WeatherSuggestionPerDay;
import com.assessment.weatherprediction.model.OverallWeatherInfo;
import com.assessment.weatherprediction.model.SuggestionResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service

public class PredictionService {

	@Value("${external.weather.url}")
	private String weatherConnectURL;

	@Value("${external.weather.url.appID}")
	private String weatherConnectURIAppID;

	org.apache.logging.log4j.Logger log = LogManager.getLogger(PredictionService.class);

	@Autowired
	CloseableHttpClient pooledClient;

	public SuggestionResponse predict(final String city) throws ApplicationException, IOException, URISyntaxException {

		log.info("predict() city : {}", city); 
		HttpGet someHttpGet = new HttpGet(weatherConnectURL);

		URI uri = new URIBuilder(someHttpGet.getURI()).addParameter("q", city)
				.addParameter("appid", weatherConnectURIAppID).build();
		
		log.info("predict() uri : {}", uri); 

		((HttpRequestBase) someHttpGet).setURI(uri);

		OverallWeatherInfo result = null;
		SuggestionResponse weatherResp = null;

		CloseableHttpResponse response = pooledClient.execute(someHttpGet);

		// Get HttpResponse Status
		
		log.info(response.getStatusLine().getStatusCode()); // 200
		

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// return it as a String
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			result = mapper.readValue(EntityUtils.toString(entity), OverallWeatherInfo.class);

			log.info("OverallWeatherInfo" + result);

			weatherResp = processResponse(result);
			
			log.info("predict() weatherResp : {}", weatherResp); 
		}
		return weatherResp;
	}

	private SuggestionResponse processResponse(OverallWeatherInfo weatherInfoPostHttpCall) {
		log.info("processResponse() weatherInfoPostHttpCall : {}", weatherInfoPostHttpCall);

		//output POJOs
		List<WeatherSuggestionPerDay> allDaysSuggestionBasedOnWeather = new ArrayList<>();
		SuggestionResponse response = new SuggestionResponse();

		//process input
		List<ClimateForDay> listOfClimte = weatherInfoPostHttpCall.getList();
		log.info("processResponse() listOfClimte : {}", listOfClimte);

		for (ClimateForDay singledayClimate : listOfClimte) {
			WeatherSuggestionPerDay singleDaySuggestion = new WeatherSuggestionPerDay();
			singleDaySuggestion.setMaxTemp(singledayClimate.getMain().getTemp_max());
			singleDaySuggestion.setMinTemp(singledayClimate.getMain().getTemp_min());
			//40 degrees celsius to Fareheight
			if (singledayClimate.getMain().getTemp() > 104) {
				singleDaySuggestion.setSuggestionForTheDay("Use Sunscreen Lotion");
			} else if (singledayClimate.getClouds().getAll() > 0) {
				singleDaySuggestion.setSuggestionForTheDay("Keep Umbrellas Handy.. It is going to rain!");
			}
			allDaysSuggestionBasedOnWeather.add(singleDaySuggestion);
		}

		response.setAllDaysSuggestionBasedOnWeather(allDaysSuggestionBasedOnWeather);

		log.info("processResponse() end response : {}", response);
		return response;
	}

}
