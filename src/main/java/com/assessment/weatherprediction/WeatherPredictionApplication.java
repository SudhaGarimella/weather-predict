package com.assessment.weatherprediction;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration //dl.com
public class WeatherPredictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherPredictionApplication.class, args);
	}
	
	
	@Bean(name="pooledClient")
	public CloseableHttpClient httpClient() {
	    return HttpClientBuilder.create().build();
	}

}
