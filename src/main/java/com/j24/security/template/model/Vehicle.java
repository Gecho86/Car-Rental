package com.j24.security.template.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registration;

    private String brand;

    private String model;

    private BigDecimal dailyFee;

    private Long locationId;

    private String vehicleStatus;

    @OneToOne(mappedBy = "vehicles")
    private VehicleParameters vehicleParameters;

    @ManyToOne()
    private Booking booking;
}

