package com.j24.security.template.service;

import com.j24.security.template.model.Booking;
import com.j24.security.template.model.Vehicle;
import com.j24.security.template.repository.BookingRepository;
import com.j24.security.template.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteById (Long id) {

        vehicleRepository.deleteById(id); }


    //Wyznacznie ceny za wypożyczenie (
    //Duration duration = Duration.between(receiptDate, returnDate);
    //totalCost = duration * dailyFee
    public Optional<Vehicle> getById(Long id) {
        return vehicleRepository.findById(id);
    }
}