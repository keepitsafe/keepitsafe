package info.fcrp.passwordstore.bean;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CryptBeanTest.class, UserBeanTest.class,
		PasswordStoreBeanTest.class })
public class BeanTest {

}
