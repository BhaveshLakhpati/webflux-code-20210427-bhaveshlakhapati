package com.code20210426.bhaveshlakhapati.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.code20210426.bhaveshlakhapati.dto.PersonDetailsDTO;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = Controller.class)
@Import(HealthService.class)
public class ControllerTest {
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void testGetHealthDetailsFromBMI() {
		List<PersonDetailsDTO> personDTOList = new ArrayList<>();
		personDTOList.add(new PersonDetailsDTO("Male", 171, 96));
		personDTOList.add(new PersonDetailsDTO("Male", 100, 25));
		personDTOList.add(new PersonDetailsDTO("Female", 180, 77));
		personDTOList.add(new PersonDetailsDTO("Female", 100, 25));
		
		webTestClient.post()
			.uri("/bmi")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(personDTOList))
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.Overweight").isEqualTo(2);
	}
}
