package com.ttknp;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// by default spring boot scan only same folder of main app ** i have to set it scans on logic_layer folder (Optional)
@SpringBootApplication(scanBasePackages = {"com.ttknp.logic_layer.*"})
@Slf4j
public class SpringBootCrudOneToManyAndApplyAopApplication {
	// static Logger log = LoggerFactory.getLogger(SpringBootCrudOneToManyAndApplyAopApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudOneToManyAndApplyAopApplication.class, args);
		log.info("Initial Spring Boot Application");
	}

}
