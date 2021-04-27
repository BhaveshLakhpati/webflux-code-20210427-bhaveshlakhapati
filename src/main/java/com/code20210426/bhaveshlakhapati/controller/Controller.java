package com.code20210426.bhaveshlakhapati.controller;

import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code20210426.bhaveshlakhapati.dto.PersonDetailsDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bmi")
public class Controller {
	@Autowired
	private HealthService healthService;
	
	@PostMapping
	public Mono<Map<String, Integer>> getHealthDetailsFromBMI(@Valid @RequestBody Flux<PersonDetailsDTO> healthDetailsDTOFlux) {
		return healthDetailsDTOFlux.collectList().flatMap(this.healthService::calculatePeopleByHealth);
	}
}
