package com.sm360.assignment.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ListingCreateRequest {
	@NotNull(message = "name must be not empty")
	private Long dealerId;
	@NotEmpty(message = "name must be not empty")
	private String vehicle;
	@NotNull
	private Double price;

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


}
