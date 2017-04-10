package com.eretailservice.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eretailservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Collection<Booking> findByAccountUsername(String username);
}
