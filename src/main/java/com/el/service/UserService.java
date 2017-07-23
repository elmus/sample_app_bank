package com.el.service;

import java.util.List;
import java.util.Optional;

import com.el.dao.EmbdedDB;
import com.el.model.Account;
import com.el.model.User;

/**
 * 
 * @author Mustapha
 *
 */
public class UserService implements GenericService<User>{
	private EmbdedDB embdedDB;
	/**
	 * Default constructor
	 */
	public UserService() {
	}
	/**
	 * add users 
	 */
	public User create(User model) {
		if(model.getFirstName()== null && model.getLastName()== null){
			// at least lastName or firstName
            throw new IllegalArgumentException("at least lastName or firstName");
        }
        if(this.getEmbdedDB().getUsers().isEmpty()){
            model.setId(1);
            this.getEmbdedDB().getUsers().add(model);
        }else{
        	this.getEmbdedDB().getUsers().stream()
        	.map(User :: getId)
        	.reduce(Long::max).ifPresent(max -> {
                model.setId(max+1);
                this.getEmbdedDB().getUsers().add(model);
            });
        }	
        return model;
	}
	/**
	 * delete one user
	 */
	public void delete(User model) {
		this.findOne(model.getId()).map(item -> {
            this.getEmbdedDB().getUsers().remove(item);
            return item;
        });
		
	}
	/**
	 * update user
	 */
	public User update(User model) {
		this.findOne(model.getId()).map(item -> {
            return item;
        });
        return model;
	}
	/**
	 * find user by id 
	 */
	public Optional<User> findOne(Long id) {
		return this.getEmbdedDB().getUsers().stream()
				.filter(user -> user.getId() == id)
				.findFirst();								
	}
	
	/**
	 * link user to account
	 * @param userId
	 * @param account
	 */
	public void linkAccount(Long userId, Account account) {
        Optional<User> user =this.findOne(userId);
        user.orElseThrow(IllegalArgumentException::new);
        user.ifPresent(item -> item.getBankAccounts().add(account));
    }
	
	
	/**
	 * return all users in the list
	 */
	public List<User> findAll() {
		return this.getEmbdedDB().getUsers();
	}

	public UserService(EmbdedDB embdedDB) {
		super();
		this.embdedDB = embdedDB;
	}

	public EmbdedDB getEmbdedDB() {
		return embdedDB;
	}

	public void setEmbdedDB(EmbdedDB embdedDB) {
		this.embdedDB = embdedDB;
	}

}
