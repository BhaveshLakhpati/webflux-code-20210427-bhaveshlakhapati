package com.code20210426.bhaveshlakhapati.dto;

public enum HealthCategoryEnum {
	UNDERWEIGHT("Underweight"), NORMAL("Normal weight"), OVERWEIGHT("Overweight"),
	MOBESE("Moderately obese"), SOBESE("Severely obese"), VOBESE("Very Severely obese");
	
	private String value;
	
	private HealthCategoryEnum(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
