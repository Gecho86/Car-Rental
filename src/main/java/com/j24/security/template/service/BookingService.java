package com.j24.security.template.service;

import com.j24.security.template.model.Booking;
import com.j24.security.template.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public Optional<Booking> getById(Long id) {
        return bookingRepository.findById(id);
    }

}
