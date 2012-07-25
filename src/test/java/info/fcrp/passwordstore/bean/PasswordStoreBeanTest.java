package info.fcrp.passwordstore.bean;

import static org.junit.Assert.assertEquals;
import info.fcrp.passwordstore.bean.PasswordStoreBean;
import info.fcrp.passwordstore.bean.UserBean;
import info.fcrp.passwordstore.model.PasswordStore;
import info.fcrp.passwordstore.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config.xml" })
public class PasswordStoreBeanTest {
	@Autowired
	private ApplicationContext context;

	@Test
	public void creation() {
		UserBean uBean = context.getBean(UserBean.class);
		User ana = uBean.get("ana");
		User bob = uBean.get("ana");
		User charlie = uBean.get("ana");

		PasswordStoreBean psBean = context.getBean(PasswordStoreBean.class);
		PasswordStore computers = psBean.create("computers");
		PasswordStore servers = psBean.create("servers");

		uBean.addRight(ana, computers, null);
		uBean.addRight(bob, computers, null);
		uBean.addRight(charlie, computers, null);

		uBean.addRight(ana, servers, null);

		assertEquals(2, ana.getRights().size());
		assertEquals(1, bob.getRights().size());
		assertEquals(1, charlie.getRights().size());
	}

}
