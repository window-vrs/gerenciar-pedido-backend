package br.com.pedido.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // This keeps JPA auditing active for your app, 
    // but won't interfere with your Controller tests!
}