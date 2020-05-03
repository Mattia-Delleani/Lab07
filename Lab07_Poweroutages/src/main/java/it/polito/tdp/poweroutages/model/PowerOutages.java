package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class PowerOutages {

	private Integer id;
	private Integer nercId;
	private Integer affectedCustomers;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private double durata;
	/**
	 * @param id
	 * @param nercId
	 * @param affectedCustomers
	 * @param dataInizio
	 * @param dataFine
	 */
	public PowerOutages(Integer id, Integer nercId, Integer affectedCustomers, LocalDateTime dataInizio,
			LocalDateTime dataFine) {
		super();
		this.id = id;
		this.nercId = nercId;
		this.affectedCustomers = affectedCustomers;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		float temp = (Duration.between(dataInizio, dataFine).toMinutes());
		this.durata = temp/60;
	}
	@Override
	public String toString() {
		return  id + ", affectedCustomers=" + affectedCustomers
				+ ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", durata=" + durata+"\n";
	}
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public double getDurata() {
		return durata;
	}
	public Integer getAffectedCustomers() {
		return affectedCustomers;
	}
	
	
	
	
	
	
}
