package com.code20210426.bhaveshlakhapati.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthDetailsDTO {
	private float bmi;
	private HealthCategoryEnum categoryEnum;
	private HealthRiskEnum riskEnum;
}
