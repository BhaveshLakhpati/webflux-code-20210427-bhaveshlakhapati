package com.code20210426.bhaveshlakhapati.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDetailsDTO {
	@NotBlank
	private String gender;
	
	@NotNull
	private float heightCm;
	
	@NotNull
	private float weightKg;
}
