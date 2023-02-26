package com.sm360.assignment.responses;

import java.time.LocalDateTime;

import com.sm360.assignment.model.ListingState;

public class ListingUpdateResponse {
	private Long id;
	private Long dealerId;
	private String vehicle;
	private Double price;
	private LocalDateTime createdAt;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ListingState getState() {
		return this.state;
	}

	public void setState(ListingState state) {
		this.state = state;
	}
	private ListingState state;
}
