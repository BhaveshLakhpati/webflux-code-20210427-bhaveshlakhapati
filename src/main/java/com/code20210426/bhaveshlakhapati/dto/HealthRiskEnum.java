package com.code20210426.bhaveshlakhapati.dto;

public enum HealthRiskEnum {
	MALNUTRITION("Malnutrition risk"), LOW("LOW RISK"), ENHANCED(""), MEDIUM(""), HIGH(""), VERY_HIGH("");
	
	private String value;
	
	private HealthRiskEnum(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
