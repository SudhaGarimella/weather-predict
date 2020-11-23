package com.assessment.weatherprediction.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.assessment.weatherprediction.exception.ApplicationException;

@SpringBootTest

public class PredictionServiceTest {

	@Autowired
	CloseableHttpClient pooledClient;

	@Autowired
	PredictionService service;

	@BeforeEach
	public void setup() {
		ReflectionTestUtils.setField(service, "weatherConnectURL",
				"https://samples.openweathermap.org/data/2.5/forecast");
		ReflectionTestUtils.setField(service, "weatherConnectURIAppID", "d2929e9483efc82c82c32ee7e02d5");
	}

	@Test
	public void testResponse() throws ApplicationException, IOException, URISyntaxException {

		

	}

}
