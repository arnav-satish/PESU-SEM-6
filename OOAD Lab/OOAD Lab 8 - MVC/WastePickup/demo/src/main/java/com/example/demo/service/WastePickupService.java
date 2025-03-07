package com.example.demo.service;

import com.example.demo.model.WastePickup;
import com.example.demo.repository.WastePickupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WastePickupService {
    @Autowired
    private WastePickupRepository repository;

    public List<WastePickup> getAllWastePickups() {
        return repository.findAll();
    }

    public Optional<WastePickup> getWastePickupById(Long id) {
        return repository.findById(id);
    }

    public WastePickup saveWastePickup(WastePickup wastePickup) {
        if (wastePickup.getPickupDateTime().isBefore(LocalDateTime.now())) {
            wastePickup.setStatus("Completed");
        }
        return repository.save(wastePickup);
    }

    public void deleteWastePickup(Long id) {
        repository.deleteById(id);
    }

    public List<WastePickup> getWastePickupsByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<WastePickup> getWastePickupsByUser(String userName) {
        return repository.findByUserName(userName);
    }
}