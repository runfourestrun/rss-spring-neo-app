package com.fournier.statedeptrssfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;


/***
 * TODO: implement DTO pattern : https://www.baeldung.com/java-dto-pattern
 * TODO: Geocoding after AWS Comprehend (https://www.youtube.com/watch?v=-fs8ozxKklQ&list=PL9Hl4pk2FsvWPcphew_GbLjCWvMpmh4mV&index=15&t=1225s&ab_channel=Neo4j)
 */
@SpringBootApplication
@EnableConfigurationProperties
public class StateDeptRssFeedApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args){
		SpringApplication.run(StateDeptRssFeedApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {



	}
}
