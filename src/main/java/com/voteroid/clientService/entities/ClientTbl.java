package com.voteroid.clientService.entities;

import java.util.Objects;

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
	
	@Column(name="CLIENT_NAME",length=100)
	public String clientName;
	
	@Column(name="CLIENT_PHONE_NO",unique=true,length=10)
	public String clientPhoneNo;
	
	@Column(name="CLIENT_EMAIL_ID",unique=true,length=100)
	public String clientEmailId;
	
	@Column(name="PAYMENT")
	public float clientPayment;

	@Column(name="PASSWORD",length=100)
	public String password;
	
	@Override
	public int hashCode() {
		return Objects.hash(clientEmailId, clientId, clientName, clientPayment, clientPhoneNo, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientTbl other = (ClientTbl) obj;
		return Objects.equals(clientEmailId, other.clientEmailId) && clientId == other.clientId
				&& Objects.equals(clientName, other.clientName)
				&& Float.floatToIntBits(clientPayment) == Float.floatToIntBits(other.clientPayment)
				&& Objects.equals(clientPhoneNo, other.clientPhoneNo);
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPhoneNo() {
		return clientPhoneNo;
	}

	public void setClientPhoneNo(String clientPhoneNo) {
		this.clientPhoneNo = clientPhoneNo;
	}

	public String getClientEmailId() {
		return clientEmailId;
	}

	public void setClientEmailId(String clientEmailId) {
		this.clientEmailId = clientEmailId;
	}

	public float getClientPayment() {
		return clientPayment;
	}

	public void setClientPayment(float clientPayment) {
		this.clientPayment = clientPayment;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	
}
