/**
 * 
 */
package com.el.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mustapha
 *
 */
public class User {
	private long id;
	private String firstName;
	private String lastName;
	private int age;
	private String phone;
	private Set<Account> bankAccounts= new HashSet<Account>();
	/**
	 * 
	 */
	public User() {
		
	}
	
	
	/**
	 * constructor with all fields
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param phone
	 * @param bankAccounts
	 */
	public User(long id, String firstName, String lastName, int age,
			String phone, Set<Account> bankAccounts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
		this.bankAccounts = bankAccounts;
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param phone
	 */
	public User(String firstName, String lastName, int age, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
	}

	/**
	 * constructor with Fname & Lname
	 * @param firstName
	 * @param lastName
	 */
	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Set<Account> getBankAccounts() {
		return bankAccounts;
	}


	public void setBankAccounts(Set<Account> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + ", phone=" + phone
				+ ", bankAccounts=" + bankAccounts + "]";
	}	
	

}
