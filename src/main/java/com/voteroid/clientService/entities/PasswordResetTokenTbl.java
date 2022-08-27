package com.voteroid.clientService.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PASSWORD_RESET_TOKEN")
public class PasswordResetTokenTbl implements Serializable {
	
	private static final long serialVersionUID = -2346496737933371272L;

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Column(name="TOKEN", nullable = false, unique = true)
	    private String token;

	    @Column(name="CLIENT_ID",nullable = false)
	    private int clientId;

	    @Column(nullable = false)
	    private Date expiryDate;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public int getClientId() {
			return clientId;
		}

		public void setClientId(int clientId) {
			this.clientId = clientId;
		}

		public Date getExpiryDate() {
	        return expiryDate;
	    }

	    public void setExpiryDate(Date expiryDate) {
	        this.expiryDate = expiryDate;
	    }

	    public void setExpiryDate(int minutes){
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.MINUTE, minutes);
	        this.expiryDate = now.getTime();
	    }

	    public boolean isExpired() {
	        return new Date().after(this.expiryDate);
	    }
}
