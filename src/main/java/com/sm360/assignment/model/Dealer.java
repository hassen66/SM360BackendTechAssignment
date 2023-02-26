package com.sm360.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Entity
@Table
public class Dealer {
	@Id
    @GeneratedValue
    private Long id;

	private String name;
	
	private int tierLimit;

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

	public int getTierLimit() {
		return this.tierLimit;
	}

	public void setTierLimit(int tierLimit) {
		this.tierLimit = tierLimit;
	}

}
