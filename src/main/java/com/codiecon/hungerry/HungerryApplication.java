package com.codiecon.hungerry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.codiecon.hungerry"})
@EnableJpaRepositories(basePackages = {"com.codiecon.hungerry.repository"})
@EntityScan(basePackages = {"com.codiecon.hungerry.model.dao"})
@ComponentScan(basePackages = {"com.codiecon.hungerry.repository", "com.codiecon.hungerry.web", "com.codiecon.hungerry.service"})
public class HungerryApplication {

  public static void main(String[] args) {
    SpringApplication.run(HungerryApplication.class, args);
  }

}
