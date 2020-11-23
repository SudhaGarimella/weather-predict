package com.assessment.weatherprediction.model;

import java.util.List;

public class OverallWeatherInfo {
	
	String cod;
	List<ClimateForDay> list;
	
	public List<ClimateForDay> getList() {
		return list;
	}

	public void setList(List<ClimateForDay> list) {
		this.list = list;
	}

	public OverallWeatherInfo() {
		super();
	}
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	
	

}
