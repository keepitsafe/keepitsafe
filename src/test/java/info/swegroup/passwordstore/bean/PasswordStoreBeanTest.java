package info.swegroup.passwordstore.bean;

import static org.junit.Assert.*;
import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.RightEnum;
import info.swegroup.passwordstore.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;

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
		PasswordStore computers = psBean.create(new PasswordStore("computers"));
		PasswordStore servers = psBean.create(new PasswordStore("servers"));

		uBean.addRight(ana, computers, null);
		uBean.addRight(bob, computers, null, RightEnum.WRITER);
		uBean.addRight(charlie, computers, null, RightEnum.WRITER,
				RightEnum.MANAGER);

		uBean.addRight(ana, servers, null, RightEnum.WRITER, RightEnum.MANAGER,
				RightEnum.ADMIN);

		assertEquals(2, ana.getRights().size());
		assertEquals(1, bob.getRights().size());
		assertEquals(1, charlie.getRights().size());
	}

}
