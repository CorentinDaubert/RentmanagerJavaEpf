package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
	private int id;
	private int idClient;
	private int idVehicle;
	public Reservation(int idClient, int idVehicle, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	private LocalDate dateStart;
	private LocalDate dateEnd;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(int idVehicule) {
		this.idVehicle = idVehicule;
	}
	public LocalDate getDateStart() {
		return dateStart;
	}
	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}
	public LocalDate getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Reservation(int id, int idClient, int idVehicle, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
    public Reservation() {
    }
}
