package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ServiceOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String description;
	private Status status;
	private LocalDate emissionDate;
	private LocalDate finalizationDate;
	private double price;
	private String notes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDate getEmissionDate() {
		return emissionDate;
	}
	public void setEmissionDate(LocalDate emissionDate) {
		this.emissionDate = emissionDate;
	}
	public LocalDate getFinalizationDate() {
		return finalizationDate;
	}
	public void setFinalizationDate(LocalDate finalizationDate) {
		this.finalizationDate = finalizationDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrder other = (ServiceOrder) obj;
		return Objects.equals(id, other.id);
	}	

}
