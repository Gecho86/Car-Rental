package com.j24.security.template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String city;

	private String addres;

	private String email;

	private String phone;

	public Location(String city, String addres, String email, String phone, Booking booking) {
		this.city = city;
		this.addres = addres;
		this.email = email;
		this.phone = phone;
		this.booking = booking;
	}

	@ManyToOne()
	private Booking booking;
}
