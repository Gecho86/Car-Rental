package com.j24.security.template.controller;

import com.j24.security.template.model.Account;
import com.j24.security.template.model.Vehicle;
import com.j24.security.template.service.AccountService;
import com.j24.security.template.service.BookingService;
import com.j24.security.template.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/vehicle/")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public String list(Model model, Principal principal) {
        Optional<Account> account = accountService.getByUsername(principal.getName());
        if (!account.isPresent()) return "todo: screen with error";

        List<Vehicle> vehicles;


        if (account.get().isAdmin()) vehicles = vehicleService.getAll();
        else vehicles = account.get().getVehicles();

        model.addAttribute("our_vehicle_list", vehicles);

        return "vehicle-list";
    }

    @GetMapping("/add")
    public String vehicleAddForm(Model model, Vehicle vehicle){
        model.addAttribute("edited_vehicle", vehicle);
        model.addAttribute("allBookings", bookingService.getAll());

        return "vehicle-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        Optional<Vehicle> vehicleOptional = vehicleService.getById(id);
        if(vehicleOptional.isPresent()) {
            model.addAttribute("edited_vehicle", vehicleOptional.get());
            model.addAttribute("allBookings", bookingService.getAll());
            return "vehicle-form";
        }
        return "redirect:/vehicle/list";
    }

    @PostMapping("/add")
    public String saveVehicle(Vehicle vehicle, Principal principal){
        Optional<Account> account = accountService.getByUsername(principal.getName());
        if (!account.isPresent()) return "todo: screen with error";

        vehicle.setAccount(account.get());
        vehicleService.save(vehicle);

        return "redirect:/vehicle/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable(name = "id") Long id) {
        vehicleService.deleteById(id);

        return "redirect:/vehicle/list";
    }


}