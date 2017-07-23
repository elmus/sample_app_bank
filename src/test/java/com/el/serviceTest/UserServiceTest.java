package com.el.serviceTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.el.dao.EmbdedDB;
import com.el.model.Account;
import com.el.model.User;
import com.el.service.UserService;

public class UserServiceTest {
	private UserService userService;
	private EmbdedDB embdedDB;
	private User myUser;
	private Account myAccount;
	
    @Rule
    public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		this.userService = new UserService();
		this.myUser = new User("Mustapha", "ELKABOUS");
		this.myAccount = new Account("RIB123");
		this.embdedDB = EmbdedDB.getInstance();
		this.userService.setEmbdedDB(embdedDB);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createLNameorFNameTestKO() {
		this.myUser.setLastName(null);
		this.myUser.setFirstName(null);
        this.userService.create(this.myUser);
        exception.expect(IllegalArgumentException.class);
        assertEquals("User should have at least lastName or firstName",userService.findAll().size(),0);
	}
	
	@Test
	public void createUserOK() {
        this.userService.create(this.myUser);
        assertEquals("User create",userService.findAll().size(),1);
        assertEquals("User create with id ",userService.findOne((long)1).isPresent(),true);
	}
	
	@Test
	public void updateUserOK() {
		this.myUser.setLastName("Mustapha");
        this.userService.create(this.myUser);
		this.myUser.setAge(26);
        this.userService.update(this.myUser);
        assertEquals("User update",userService.findOne((long)1).get().getAge(),26);
	}
	
	@Test
	public void removeUserOK() {
		this.myUser.setLastName("Mustapha");
        this.userService.create(this.myUser);
        assertEquals("User create with id ",userService.findOne((long)1).isPresent(),true);
        this.userService.delete(this.myUser);
        assertEquals("User create with id ",userService.findOne((long)1).isPresent(),false);
	}
	
	@Test
	public void linkAccountTestOK() {
        this.userService.create(this.myUser);
        this.userService.linkAccount((long)1, myAccount);
        assertEquals("linkAccount ",userService.findOne((long)1).get().getBankAccounts().isEmpty(), false);
	}
	
	@After
	public void tearDown() throws Exception {
		// clear all user
		this.userService.findAll().clear();
	}


}
