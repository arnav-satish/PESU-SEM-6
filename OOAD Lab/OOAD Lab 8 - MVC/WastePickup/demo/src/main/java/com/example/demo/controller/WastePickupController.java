package com.example.demo.controller;

import com.example.demo.model.WastePickup;
import com.example.demo.service.WastePickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wastepickups")
public class WastePickupController {

    @Autowired
    private WastePickupService service;

    @GetMapping
    public String showWastePickups(Model model) {
        List<WastePickup> wastePickups = service.getAllWastePickups();
        model.addAttribute("wastePickups", wastePickups);
        return "waste_pickups"; // Thymeleaf view
    }

    @GetMapping("/new")
    public String showNewWastePickupForm(Model model) {
        model.addAttribute("wastePickup", new WastePickup());
        return "new_waste_pickup";
    }

    @PostMapping("/save")
    public String saveWastePickup(@ModelAttribute WastePickup wastePickup) {
        service.saveWastePickup(wastePickup);
        return "redirect:/wastepickups";
    }

    @GetMapping("/view/{id}")
    public String viewWastePickup(@PathVariable Long id, Model model) {
        Optional<WastePickup> wastePickup = service.getWastePickupById(id);
        if (wastePickup.isEmpty()) {
            return "redirect:/wastepickups";
        }
        model.addAttribute("wastePickup", wastePickup.get());
        return "view_waste_pickup";
    }

    @GetMapping("/edit/{id}")
    public String editWastePickup(@PathVariable Long id, Model model) {
        Optional<WastePickup> wastePickup = service.getWastePickupById(id);
        if (wastePickup.isEmpty()) {
            return "redirect:/wastepickups";
        }
        model.addAttribute("wastePickup", wastePickup.get());
        return "edit_waste_pickup";
    }

    @PostMapping("/update/{id}")
    public String updateWastePickup(@PathVariable Long id, @ModelAttribute WastePickup wastePickup) {
        wastePickup.setId(id);
        service.saveWastePickup(wastePickup);
        return "redirect:/wastepickups";
    }

    @GetMapping("/delete/{id}")
    public String deleteWastePickup(@PathVariable Long id) {
        service.deleteWastePickup(id);
        return "redirect:/wastepickups";
    }

    @GetMapping("/")
    public String home() {
        return "index"; // Landing page
    }
}