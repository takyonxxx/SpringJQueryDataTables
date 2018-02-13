package com.biliyor.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(name = "city")
	private CityNames city;
	@Column(name = "date")
	private Date date;
	@Column(name = "active")
	private boolean active;

	public Employee() {
		super();
	}

	public Employee(String name, String email, CityNames city, Date date, boolean active) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
		this.date = date;
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CityNames getCity() {
		return city;
	}

	public void setCity(CityNames city) {
		this.city = city;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	
	
    public boolean ifExist(Object o) {
    	
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;
        System.out.println("employee : " + employee.id);

        if (this.id != employee.id) return false;

        return true;
    }

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", city=" + city + ", date=" + date
				+ ", active=" + active + "]";
	}	
}
