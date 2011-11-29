package info.swegroup.passwordstore.bean;

import static org.junit.Assert.*;
import info.swegroup.passwordstore.model.PasswordStore;
import info.swegroup.passwordstore.model.User;

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
	public void create() {
		UserBean uBean = context.getBean(UserBean.class);

		User usr = uBean.get("ana");
		assertNull(usr);

		usr = uBean.create(new User("ana", "ana"));

		assertTrue(usr.getId() > 0);

		User usr2 = uBean.get("ana");
		assertEquals(usr.getId(), usr2.getId());

		uBean.create(new User("bob", "bob"));
		uBean.create(new User("charlie", "charlie"));
	}
}
