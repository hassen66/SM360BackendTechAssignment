package com.sm360.assignment.requests;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListingUpdateRequest {

	@NotNull
	private Long dealerId;
	@NotEmpty
	private String vehicle;
	@NotNull
	private Double price;
}
