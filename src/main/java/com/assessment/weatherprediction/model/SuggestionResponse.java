package com.assessment.weatherprediction.model;

import java.util.List;

public class SuggestionResponse {
	List<WeatherSuggestionPerDay> allDaysSuggestionBasedOnWeather;

	public List<WeatherSuggestionPerDay> getAllDaysSuggestionBasedOnWeather() {
		return allDaysSuggestionBasedOnWeather;
	}

	public void setAllDaysSuggestionBasedOnWeather(List<WeatherSuggestionPerDay> allDaysSuggestionBasedOnWeather) {
		this.allDaysSuggestionBasedOnWeather = allDaysSuggestionBasedOnWeather;
	}

	public SuggestionResponse() {
		super();
	}

}
