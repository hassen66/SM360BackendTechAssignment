package com.sm360.assignment.responses;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sm360.assignment.model.ListingState;

public class ListingGetResponse {
	private Long id;
	private DealerResponse dealer;
	private String vehicle;
	private Double price;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private ListingState state;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DealerResponse getDealer() {
		return this.dealer;
	}

	public void setDealer(DealerResponse dealer) {
		this.dealer = dealer;
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

	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ListingState getState() {
		return this.state;
	}

	public void setState(ListingState state) {
		this.state = state;
	}

    
	public static class DealerResponse {
		private Long id;
		private String name; 

		public Long getId() {
			return this.id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return this.name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	}
}
