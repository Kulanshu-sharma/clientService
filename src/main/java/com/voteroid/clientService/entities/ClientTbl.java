package com.voteroid.clientService.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT_TBL")
public class ClientTbl {

	@Id
	@GeneratedValue(generator = "client-sequence-generator")
	@SequenceGenerator(name = "client-sequence-generator", sequenceName = "ClientIdSequence", initialValue = 2, allocationSize = 100)
	@Column(name="CLIENT_ID")
	public int clientId;
	
	@Column(name="CLIENT_NAME")
	public int clientName;
	
	@Column(name="CLIENT_PHONE_NO")
	public int clientPhoneNo;
	
	@Column(name="CLIENT_EMAIL_ID")
	public int clientEmailId;
	
	@Column(name="PAYMENT")
	public float clientPayment;

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientName() {
		return clientName;
	}

	public void setClientName(int clientName) {
		this.clientName = clientName;
	}

	public int getClientPhoneNo() {
		return clientPhoneNo;
	}

	public void setClientPhoneNo(int clientPhoneNo) {
		this.clientPhoneNo = clientPhoneNo;
	}

	public int getClientEmailId() {
		return clientEmailId;
	}

	public void setClientEmailId(int clientEmailId) {
		this.clientEmailId = clientEmailId;
	}

	public float getClientPayment() {
		return clientPayment;
	}

	public void setClientPayment(float clientPayment) {
		this.clientPayment = clientPayment;
	}
	
	
	
	
}
