package com.StudentManagementSystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int duration; // in semesters
    private double totalFees;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Semester> semesters = new ArrayList<>();

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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(double totalFees) {
		this.totalFees = totalFees;
	}

	public List<Semester> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<Semester> semesters) {
		this.semesters = semesters;
	}

	public Course(Long id, String name, int duration, double totalFees, List<Semester> semesters) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.totalFees = totalFees;
		this.semesters = semesters;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
