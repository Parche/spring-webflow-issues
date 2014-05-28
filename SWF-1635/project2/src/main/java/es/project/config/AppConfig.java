package es.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={
		WebMvcConfig.class,
		WebFlowConfig.class
	})
public class AppConfig {

}
