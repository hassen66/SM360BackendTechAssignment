package com.sm360.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sm360.assignment.model.Dealer;
import com.sm360.assignment.model.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    
}
