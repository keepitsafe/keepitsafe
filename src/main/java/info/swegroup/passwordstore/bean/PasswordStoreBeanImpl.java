package info.swegroup.passwordstore.bean;

import info.swegroup.passwordstore.dao.PasswordStoreDAO;
import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.User;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PasswordStoreBeanImpl implements PasswordStoreBean {

	@Autowired
	PasswordStoreDAO passwordStoreDAO;

	public PasswordStore create(PasswordStore ps) {
		if (ps == null) {
			throw new InvalidParameterException("null password store");
		}

		if (ps.getId() > 0) {
			throw new InvalidParameterException("password store already exist");
		}

		passwordStoreDAO.save(ps);

		return ps;
	}

	public PasswordStore get(String name) {
		PasswordStore ps = passwordStoreDAO.findByName(name);
		return ps;
	}
}
