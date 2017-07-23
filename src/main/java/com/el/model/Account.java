/**
 * 
 */
package com.el.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mustapha
 *
 */
public class Account {
	private long id;
	private String accountNumber;
	private Date creationDate;
	private BigDecimal balance;
	
	
	/**
	 * default constructor
	 */
	public Account() {
		
	}
	
	/**
	 * 
	 * @param accountNumber
	 */
	public Account(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	/**
	 * 
	 * @param id
	 * @param accountNumber
	 * @param creationDate
	 * @param balance
	 */
	public Account(long id, String accountNumber, Date creationDate,
			BigDecimal balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.creationDate = creationDate;
		this.balance = balance;
	}




	/**********************************/
	/***********SETTER & GETTER *******/
	/**********************************/
	
	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber
				+ ", creationDate=" + creationDate + ", balance=" + balance
				+ "]";
	}
	
	

}
