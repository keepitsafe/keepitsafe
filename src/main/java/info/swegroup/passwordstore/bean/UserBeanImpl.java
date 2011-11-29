package info.swegroup.passwordstore.bean;

import info.swegroup.passwordstore.dao.RightDAO;
import info.swegroup.passwordstore.dao.UserDAO;
import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.Right;
import info.swegroup.passwordstore.model.RightEnum;
import info.swegroup.passwordstore.model.User;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

public class UserBeanImpl implements UserBean {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RightDAO rightDAO;

	public User create(User usr) {
		if (usr == null) {
			throw new InvalidParameterException("null usr");
		}

		if (usr.getId() > 0) {
			throw new InvalidParameterException("user already saved");
		}

		if (usr.getPassword() != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-1");
				md.update(usr.getPassword().getBytes());
				usr.setPassword(Base64.encodeBase64String(md.digest()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
			}
		}

		userDAO.save(usr);
		return usr;
	}

	public User get(String login) {
		User usr = userDAO.findByLogin(login);
		return usr;
	}

	public void addRight(User usr, PasswordStore ps, String key, RightEnum... rights) {
		Right right = rightDAO.findByUserAndPStore(usr, ps);
		if (right == null) {
			right = new Right(usr, ps);
		}

		for (RightEnum rightEnum : rights) {
			switch (rightEnum) {
			case WRITER:
				right.setWriter(true);
				break;
			case MANAGER:
				right.setManager(true);
				break;
			case ADMIN:
				right.setAdmin(true);
				break;
			}
		}
		
		rightDAO.save(right);
	}

}
