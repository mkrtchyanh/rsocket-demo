package io.hayk.demo.service.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan(basePackages = "io.hayk.demo")
class JpaConfiguration {
}
