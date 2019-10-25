package com.j24.security.template.controller;

import com.j24.security.template.model.Vehicle;
import com.j24.security.template.service.BookingService;
import com.j24.security.template.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/vehicle/")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Vehicle> vehicles = vehicleService.getAll();
        model.addAttribute("vehicles", vehicles);

        return "vehicle-list";
    }

    @GetMapping("/add")
    public String vehicleAddForm(Model model, Vehicle vehicle){
        model.addAttribute("edited_vehicle", vehicle);
        model.addAttribute("allBookings", bookingService.getAll());

        return "vehicle-form";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "vehicleId") Long id) {
        Optional<Vehicle> vehicleOptional = vehicleService.getById(id);
        if(vehicleOptional.isPresent()) {
            model.addAttribute("edited_vehicle", vehicleOptional.get());
            model.addAttribute("allBookings", bookingService.getAll());
            return "book-form";
        }
        return "redirect:/book/list";
    }

    @PostMapping("/add")
    public String saveVehicle(Vehicle vehicle, Long bookingId){
        vehicleService.save(vehicle, bookingId);

        return "redirect:/vehicle/list";
    }
}