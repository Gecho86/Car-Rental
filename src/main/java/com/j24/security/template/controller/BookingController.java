package com.j24.security.template.controller;

import com.j24.security.template.model.Booking;
import com.j24.security.template.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/booking/")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Booking> bookings = bookingService.getAll();
        model.addAttribute("bookings", bookings);

        return "booking-list";
    }

    @GetMapping("/remove")
    public String delete(@RequestParam(name = "bookingId") Long id) {
        bookingService.deleteById(id);

        return "redirect:/booking/list";
    }

    @GetMapping("/add")
    public String addForm(Model model, Booking booking){
        model.addAttribute("edited_object", booking);

        return "booking-form";
    }

    @PostMapping("/add")
    public String save(Booking booking){
        bookingService.save(booking);

        return "redirect:/booking/list";
    }


    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "bookingId") Long id) {
        Optional<Booking> bookingOptional = bookingService.getById(id);
        if(bookingOptional.isPresent()) {
            model.addAttribute("edited_object", bookingOptional.get());
            return "booking";
        }
        return "redirect:/booking/list";
    }
}