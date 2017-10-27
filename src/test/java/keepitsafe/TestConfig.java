package keepitsafe;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ Config.class, WebConfig.class })
public class TestConfig {

}
