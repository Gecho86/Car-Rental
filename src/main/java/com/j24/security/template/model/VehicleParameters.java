package com.j24.security.template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleID;

    private String bodytype;

    private Integer productionYear;

    private String color;

    @OneToOne(mappedBy = "vehicleParameters")
    private Vehicle vehicle;
}
