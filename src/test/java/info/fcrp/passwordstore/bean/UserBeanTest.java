package info.fcrp.passwordstore.bean;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import info.fcrp.passwordstore.bean.UserBean;
import info.fcrp.passwordstore.model.PasswordStore;
import info.fcrp.passwordstore.model.User;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config.xml" })
public class UserBeanTest {
	@Autowired
	private ApplicationContext context;

	@Test
	public void create() throws NoSuchAlgorithmException,
			NoSuchProviderException {
		UserBean uBean = context.getBean(UserBean.class);

		User usr = uBean.get("ana");
		assertNull(usr);

		usr = uBean.create("ana");

		assertTrue(usr.getId() > 0);

		User usr2 = uBean.get("ana");
		assertEquals(usr.getId(), usr2.getId());

		uBean.create("bob");
		uBean.create("charlie");
	}

	private String generatePublicKey() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024, new SecureRandom());
		KeyPair kp = kpg.generateKeyPair();
		PublicKey pubKey = kp.getPublic();

		return Base64.encodeBase64String(pubKey.getEncoded());
	}
}
