package com.ner.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.NERController;
import core.Pipeline;
import model.Type;

@SpringBootApplication
@ComponentScan(basePackageClasses = {NERController.class, Type.class, Pipeline.class})
public class NerProjectApplication {

	public static void main(String[] args) {
		//System.setProperty("server.servlet.context-path", "/api/v1/");
		SpringApplication app = new SpringApplication(NerProjectApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
		app.run(args);
	}

}
