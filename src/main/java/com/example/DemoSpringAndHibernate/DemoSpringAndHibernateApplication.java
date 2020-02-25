package com.example.DemoSpringAndHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoSpringAndHibernateApplication {

	public static void main(String[] args) { SpringApplication.run(DemoSpringAndHibernateApplication.class, args); }
}
