package com.el.dao;

import java.util.ArrayList;
import java.util.List;

import com.el.model.Account;
import com.el.model.User;

/**
 * 
 * @author Mustapha
 *
 */
public class EmbdedDB {
	private List<User> users;
    private List<Account> accouts;
    /** L'instance statique */
    private static EmbdedDB instance;
    // Singleton
    private EmbdedDB() {
		users 	= new ArrayList<User>();

	    accouts = new ArrayList<Account>();
	}
    
    public static EmbdedDB getInstance() {
        if (null == instance) { // first call	
        	instance = new EmbdedDB();
        }
        return instance;
    }
        
    /**********************************/
	/***********SETTER & GETTER *******/
	/**********************************/

    public List<Account> getAccounts() {
        return accouts;
    }

    public void setAccounts(List<Account> accouts) {
        this.accouts = accouts;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
