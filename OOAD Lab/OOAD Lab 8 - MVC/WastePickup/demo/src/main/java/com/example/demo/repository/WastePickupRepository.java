package com.example.demo.repository;

import com.example.demo.model.WastePickup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WastePickupRepository extends JpaRepository<WastePickup, Long> {
    List<WastePickup> findByStatus(String status);
    List<WastePickup> findByUserName(String userName);
}