package info.fcrp.passwordstore.bean;

import info.fcrp.passwordstore.dao.PasswordStoreDAO;
import info.fcrp.passwordstore.model.PasswordStore;
import info.fcrp.passwordstore.model.User;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PasswordStoreBeanImpl implements PasswordStoreBean {

	@Autowired
	PasswordStoreDAO passwordStoreDAO;

	public PasswordStore create(String name) {
		
		PasswordStore ps = new PasswordStore(name);
		passwordStoreDAO.save(ps);
		return ps;
	}

	public PasswordStore get(String name) {
		PasswordStore ps = passwordStoreDAO.findByName(name);
		return ps;
	}
}
