package com.example.my_first_springboot_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.my_first_springboot_app")
public class MyFirstSpringbootAppApplication {

	private static final Logger logger = LoggerFactory.getLogger(MyFirstSpringbootAppApplication.class);

	@Value("${server.port:8080}")
	private String serverPort;

	public static void main(String[] args) {
		logger.info("Starting My First Spring Boot App...");
		SpringApplication.run(MyFirstSpringbootAppApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		logger.info("🚀 My First Spring Boot App is ready and running on port {}", serverPort);
		logger.info("📋 Available endpoints:");
		logger.info("   - Home: http://localhost:{}/", serverPort);
		logger.info("   - Hello: http://localhost:{}/hello", serverPort);
		logger.info("   - Users API: http://localhost:{}/api/users", serverPort);
		logger.info("   - User Stats: http://localhost:{}/api/users/stats", serverPort);
		logger.info("   - Health Check: http://localhost:{}/actuator/health", serverPort);
	}
}