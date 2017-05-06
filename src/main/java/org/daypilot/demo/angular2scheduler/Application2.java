package org.daypilot.demo.angular2scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
	basePackageClasses = { Application2.class, Jsr310JpaConverters.class }
)
@SpringBootApplication
public class Application2 {
    public static void main2(String[] args) throws Exception {
        SpringApplication.run(Application2.class, args);
    }
}