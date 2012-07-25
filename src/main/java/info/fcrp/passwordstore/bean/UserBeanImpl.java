package info.fcrp.passwordstore.bean;

import info.fcrp.passwordstore.dao.RightDAO;
import info.fcrp.passwordstore.dao.UserDAO;
import info.fcrp.passwordstore.model.PasswordStore;
import info.fcrp.passwordstore.model.Right;
import info.fcrp.passwordstore.model.User;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

public class UserBeanImpl implements UserBean {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RightDAO rightDAO;

	
	public User create(String login) {
		User usr = new User(login);
		userDAO.save(usr);
		return usr;
	}

	
	public User get(String login) {
		User usr = userDAO.findByLogin(login);
		return usr;
	}

	public void addRight(User usr, PasswordStore ps) {
		Right right = rightDAO.findByUserAndPStore(usr, ps);
		if (right == null) {
			right = new Right(usr, ps);
		}
		usr.getRights().add(right);
		ps.getRights().add(right);
		rightDAO.save(right);
	}
}
