package com.StudentManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Subject {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double fee;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

	public Subject(Long id, String name, double fee, Semester semester) {
		super();
		this.id = id;
		this.name = name;
		this.fee = fee;
		this.semester = semester;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
