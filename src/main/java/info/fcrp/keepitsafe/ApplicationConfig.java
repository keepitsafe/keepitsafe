package info.fcrp.keepitsafe;

import info.fcrp.keepitsafe.bean.UserBean;
import info.fcrp.keepitsafe.bean.UserBeanImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Bean
	public UserBean getUserBean() {
		return new UserBeanImpl();
	}

}
