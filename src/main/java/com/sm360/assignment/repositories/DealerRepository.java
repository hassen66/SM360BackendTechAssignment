package com.sm360.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sm360.assignment.model.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    
}
