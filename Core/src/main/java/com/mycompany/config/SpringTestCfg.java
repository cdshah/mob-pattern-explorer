package com.mycompany.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan(basePackages ="com.mycompany")
@EnableJpaRepositories("com.mycompany.dbRepository")
@EntityScan(basePackages= "com.mycompany.dbEntity")
@EnableAutoConfiguration
@ImportResource("classpath:TestApplicationContext.xml")
@Profile("test")
public class SpringTestCfg {

		
		public static void main(String e[]){
			
		}
		public SpringTestCfg() {
			// TODO Auto-generated constructor stub
		}
		
		
		
		
}
