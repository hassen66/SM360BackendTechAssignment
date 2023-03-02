package com.sm360.assignment.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sm360.assignment.model.Dealer;
import com.sm360.assignment.model.Listing;
import com.sm360.assignment.model.ListingState;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findByDealerAndState(Dealer dealer, ListingState state);

    Listing findFirstByDealerAndStateAndPublishedAtIsNotNullOrderByPublishedAtDesc(Dealer dealer, ListingState state);
    
    long countByDealerAndState(Dealer dealer, ListingState state);
    
}
