package ch.sbb.rotationmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:query.xml")
public class QueryConfig {
}