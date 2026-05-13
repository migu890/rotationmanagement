package ch.sbb.rotationmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:catalogurl.properties")
public class CatalogUrl {
}