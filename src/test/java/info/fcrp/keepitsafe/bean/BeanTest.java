package info.fcrp.keepitsafe.bean;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CryptBeanTest.class, UserBeanTest.class,
		KeepBeanTest.class })
public class BeanTest {

}
