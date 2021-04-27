package com.code20210426.bhaveshlakhapati.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.code20210426.bhaveshlakhapati.dto.HealthCategoryEnum;
import com.code20210426.bhaveshlakhapati.dto.HealthDetailsDTO;
import com.code20210426.bhaveshlakhapati.dto.HealthRiskEnum;
import com.code20210426.bhaveshlakhapati.dto.PersonDetailsDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HealthService {
	private Function<Float, HealthDetailsDTO> calculateHealthFunction = (bmi) -> {
		HealthCategoryEnum categoryEnum;
		HealthRiskEnum riskEnum;
		
		if(bmi <= 18.4) {
			categoryEnum = HealthCategoryEnum.UNDERWEIGHT;
			riskEnum = HealthRiskEnum.MALNUTRITION;
		} else if(bmi >= 18.5 && bmi <= 24.9) {
			categoryEnum = HealthCategoryEnum.NORMAL;
			riskEnum = HealthRiskEnum.LOW;						
		} else if(bmi >= 25 && bmi <= 29.9) {
			categoryEnum = HealthCategoryEnum.OVERWEIGHT;
			riskEnum = HealthRiskEnum.ENHANCED;
		} else if(bmi >= 30 && bmi <= 34.9) {
			categoryEnum = HealthCategoryEnum.MOBESE;
			riskEnum = HealthRiskEnum.MEDIUM;
		} else if(bmi >= 35 && bmi <= 39.9) {
			categoryEnum = HealthCategoryEnum.SOBESE;
			riskEnum = HealthRiskEnum.HIGH;
		} else {
			categoryEnum = HealthCategoryEnum.VOBESE;
			riskEnum = HealthRiskEnum.VERY_HIGH;
		}
		
		return new HealthDetailsDTO(bmi, categoryEnum, riskEnum);
	};
	
	public Mono<Map<String, Integer>> calculatePeopleByHealth(List<PersonDetailsDTO> personDetailsDTOList) {
		return Flux.fromIterable(personDetailsDTOList)
				.flatMap(personDetailsDTO -> {
					float bmi = personDetailsDTO.getWeightKg() / personDetailsDTO.getHeightCm() * 100;					
					return Mono.just(calculateHealthFunction.apply(bmi));
				})
				.collectList()
				.flatMap(healthDetailsDTOList -> {
					Map<String, Integer> healthMap = new HashMap<>();
					
					healthDetailsDTOList.stream()
						.forEach(healthDetails -> {
							String categoryName = healthDetails.getCategoryEnum().getValue();
							
							int count = healthMap.getOrDefault(categoryName, 0) + 1;
							healthMap.put(categoryName, count);
						});
					
					return Mono.just(healthMap);
				});
	}
}
