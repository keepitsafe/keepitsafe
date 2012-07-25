package info.fcrp.keepitsafe.bean;

import static org.junit.Assert.assertEquals;
import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config.xml" })
public class KeepBeanTest {
	@Autowired
	private ApplicationContext context;

	@Test
	public void creation() {
		UserBean uBean = context.getBean(UserBean.class);
		User ana = uBean.get("ana");
		User bob = uBean.get("ana");
		User charlie = uBean.get("ana");

		KeepBean kpBean = context.getBean(KeepBean.class);
		Keep computers = kpBean.create("computers");
		Keep servers = kpBean.create("servers");

		uBean.addRight(ana, computers);
		uBean.addRight(bob, computers);
		uBean.addRight(charlie, computers);

		uBean.addRight(ana, servers);

		assertEquals(2, ana.getRights().size());
		assertEquals(1, bob.getRights().size());
		assertEquals(1, charlie.getRights().size());
	}

}
