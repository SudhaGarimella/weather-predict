package com.assessment.weatherprediction.model;

public class WeatherSuggestionPerDay {

	Float minTemp;
	Float maxTemp;
	String suggestionForTheDay;
	
	public WeatherSuggestionPerDay() {
		super();
	}
	public Float getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(Float minTemp) {
		this.minTemp = minTemp;
	}
	public Float getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(Float maxTemp) {
		this.maxTemp = maxTemp;
	}
	public String getSuggestionForTheDay() {
		return suggestionForTheDay;
	}
	public void setSuggestionForTheDay(String suggestionForTheDay) {
		this.suggestionForTheDay = suggestionForTheDay;
	}
}
