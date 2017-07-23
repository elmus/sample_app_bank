package com.el.serviceTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.el.dao.EmbdedDB;
import com.el.model.Account;
import com.el.model.User;
import com.el.service.AccountService;

public class AccountServiceTest {
	private AccountService accountService;
	private EmbdedDB embdedDB;
	private User myUser;
	private Account myAccount;
	
    @Rule
    public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		this.accountService = new AccountService();
		this.embdedDB = EmbdedDB.getInstance();
		this.accountService.setEmbdedDB(this.embdedDB);
		this.myAccount = new Account("IBAN323");
	}

	@Test(expected=IllegalArgumentException.class)
	public void createAccountNullKO() {
		this.myAccount.setAccountNumber(null);
		this.accountService.create(this.myAccount);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createAccountEmptyKO() {
		this.myAccount.setAccountNumber("");
		this.accountService.create(this.myAccount);
		assertEquals("Account number is required",this.accountService.findAll().size(),0);
	}
	
	@Test
	public void createAccountOK() {
		this.accountService.create(this.myAccount);
		assertEquals("Account created",this.accountService.findAll().isEmpty(),false);
		assertEquals("Account created with correct id",this.accountService.findOne((long)1).get().getAccountNumber(),"IBAN323");
	}
	
	@Test
	public void updateAccountOK() {
		Account secAccount = new Account("IBAN432");
		this.accountService.create(this.myAccount);
		this.accountService.create(secAccount);
		this.myAccount.setBalance(BigDecimal.valueOf(4000));
		secAccount.setBalance(BigDecimal.valueOf(9000));
		this.accountService.update(this.myAccount);
		this.accountService.update(secAccount);
		assertEquals("account1 have a balance 4000€",this.accountService.findOne((long)1).get().getBalance(),BigDecimal.valueOf(4000));
		assertEquals("account2 have a balance 9000€",this.accountService.findOne((long)2).get().getBalance(),BigDecimal.valueOf(9000));
	}
	
	@After
	public void tearDown() throws Exception {
		this.accountService.findAll().clear();
	}

}
