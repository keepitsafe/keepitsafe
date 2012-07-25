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

	public void addRight(User usr, PasswordStore ps, String key) {
		Right right = rightDAO.findByUserAndPStore(usr, ps);
		if (right == null) {
			right = new Right(usr, ps);
		}

		try {
			//
			// KeyPairGenerator kpg = null;
			// kpg = KeyPairGenerator.getInstance("RSA", "BC");
			// kpg.initialize(1024, new SecureRandom());
			// KeyPair kp = kpg.generateKeyPair();
			// PrivateKey priKey = kp.getPrivate();
			// PublicKey pubKey = kp.getPublic();

//			byte[] usrKey = Base64.decodeBase64(usr.getPublicKey());
			byte[] usrKey = null;
			byte[] k = Base64.decodeBase64(key);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA/ECB/PKCS1Padding");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(usrKey);
			PublicKey pubKey = keyFactory.generatePublic(keySpec);

			Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			c.init(Cipher.ENCRYPT_MODE, pubKey);
			c.update(k);
			byte[] kC = c.doFinal();

//			right.setPasswordStoreKey(Base64.encodeBase64String(kC));

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rightDAO.save(right);
	}
}
