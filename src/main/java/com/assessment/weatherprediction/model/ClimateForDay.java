package com.assessment.weatherprediction.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



public class ClimateForDay {

	String dt;
	TemperatureWrapper main;
	List<Weather> weather;
	Clouds clouds;
	
	
	
	public ClimateForDay() {
		super();
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public TemperatureWrapper getMain() {
		return main;
	}
	public void setMain(TemperatureWrapper main) {
		this.main = main;
	}
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	public Clouds getClouds() {
		return clouds;
	}
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
	
	
	
	
}
