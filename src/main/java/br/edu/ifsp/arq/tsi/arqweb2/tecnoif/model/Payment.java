package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model;

import java.io.Serializable;
import java.util.Objects;

public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String paymentType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}	
	
}
