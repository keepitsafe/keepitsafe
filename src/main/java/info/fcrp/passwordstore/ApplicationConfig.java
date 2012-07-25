package info.fcrp.passwordstore;

import info.fcrp.passwordstore.bean.UserBean;
import info.fcrp.passwordstore.bean.UserBeanImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Bean
	public UserBean getUserBean() {
		return new UserBeanImpl();
	}

}
