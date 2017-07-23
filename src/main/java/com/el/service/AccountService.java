package com.el.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.el.dao.EmbdedDB;
import com.el.model.Account;

/**
 * 
 * @author Mustapha
 *
 */
public class AccountService implements GenericService<Account>{
	private EmbdedDB embdedDB;
	public AccountService() {
	}
	
	public List<Account> getUserBankAccounts(Set<String> acoountsN){
        return this.findAll().stream()
        		.filter(bankAccount -> acoountsN.contains(bankAccount.getAccountNumber()))
        		.collect(Collectors.toList());
    }
	/**
	 * tofind out the wealth of a user fron list of account
	 * @param bankAccounts
	 * @return
	 */
    public Optional<BigDecimal> getUserWealth(Set<String> bankAccounts) {
       return  this.getUserBankAccounts(bankAccounts).stream()
    		   .map(Account::getBalance)
    		   .reduce(BigDecimal::add);
    }
    
    public  void deposit(String accountNumber, BigDecimal amount) throws IllegalArgumentException{
        if(amount == null || amount.signum() == -1 || amount.compareTo(BigDecimal.ZERO) == 0){
            throw new IllegalArgumentException("Value shloud be positive");
        }
        if(accountNumber == null || accountNumber == ""){
        	throw new IllegalArgumentException("account number is required");
        }
        Optional<Account> account = this.findbyAccountNumber(accountNumber);
        account.orElseThrow(IllegalArgumentException::new);
        account.ifPresent(bankAccount -> bankAccount.setBalance(bankAccount.getBalance().add(amount)));
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
    	// check of amount
        if(amount.signum() == -1 || amount.compareTo(BigDecimal.ZERO) ==0){
            throw new IllegalArgumentException("Value shloud be positive");
        }
        //check account number
        if(accountNumber == null || accountNumber == ""){
        	throw new IllegalArgumentException("account number is required");
        }
        Optional<Account> account = this.findbyAccountNumber(accountNumber);
        account.orElseThrow(IllegalArgumentException::new);
        if(account.isPresent() && account.get().getBalance().compareTo(amount) <0){
        	// amount greaten then balance exist
            throw new IllegalArgumentException("No enough money in your account");
        }
        account.ifPresent(bankAccount -> bankAccount.setBalance(bankAccount.getBalance().subtract(amount)));
    }

	@Override
	public Account create(Account model) {
		// check on account number
        if(null == model.getAccountNumber() || "".equals(model.getAccountNumber())){
            throw new IllegalArgumentException("Account Number should not be null or empty");
        }
        // check no duplicate
        if(this.findbyAccountNumber(model.getAccountNumber()).isPresent()){
            throw new IllegalArgumentException("Account already exist");
        }

        if(this.getEmbdedDB().getAccounts().isEmpty()){
        	//first account
        	model.setId(1);
            this.getEmbdedDB().getAccounts().add(model);
        }else{
        	this.getEmbdedDB().getAccounts().stream()
        	.map(Account::getId)
        	.reduce(Long::max)
        	.ifPresent(max -> {
                model.setId(max+1);
                this.getEmbdedDB().getAccounts().add(model);
            });
        }
        return model;
	}

	@Override
	public void delete(Account model) {
		this.findOne(model.getId()).map(item -> {
            this.getEmbdedDB().getAccounts().remove(item);
            return item;
        });
		
	}

	@Override
	public Account update(Account model) {
		this.findOne(model.getId()).map(item -> {
            return item;
        });
        return model;
	}

	@Override
	public Optional<Account> findOne(Long id) {
		return this.getEmbdedDB().getAccounts().stream()
				.filter(account -> account.getId() == (id))
				.findFirst();
	}
	/**
	 * return all account
	 */
	@Override
	public List<Account> findAll() {
		return this.getEmbdedDB().getAccounts();
	}
	/**
	 * find account by number (RIB)
	 * @param accountNumber
	 * @return
	 */
	public Optional<Account> findbyAccountNumber(String accountNumber){
        return this.getEmbdedDB().getAccounts().stream()
        		.filter(item -> item.getAccountNumber().equals(accountNumber))
        		.findFirst();
    }
	
	public AccountService(EmbdedDB embdedDB) {
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
