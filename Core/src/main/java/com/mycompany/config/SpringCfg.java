package com.mycompany.config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mycompany.dbEntity.CallLogInfo;
import com.mycompany.dbEntity.PersonInfo;
import com.mycompany.dbRepository.CallLogRepository;
import com.mycompany.dbRepository.PersonRepository;


@Configuration
@ComponentScan(basePackages ="com.mycompany")
@EnableJpaRepositories("com.mycompany.dbRepository")
@EntityScan(basePackages= "com.mycompany.dbEntity")
@EnableAutoConfiguration
@ImportResource("classpath:ApplicationContext.xml")
@Profile("live")
public class SpringCfg {

		private static final Logger log = LoggerFactory.getLogger(SpringCfg.class);
		
		
		public static void main(String e[]){
			new SpringApplicationBuilder().profiles("live").sources(SpringCfg.class).run(e);
			
		}
		
		@Bean
		public CommandLineRunner demo(CallLogRepository callLogRepository,PersonRepository personRepository) {
			return (args) -> {
				log.info("count = "+callLogRepository.findAll());
				Calendar startTime = Calendar.getInstance();
				//2016-06-03 20:57:50.448
				//2016-06-04 01:33:03.814
				startTime.set(2016, 5, 3, 0, 0, 0);
				Calendar endTime = Calendar.getInstance();
				endTime.set(2016, 5, 4, 2, 0, 0);
				System.out.println("stat time"+ new Date(startTime.getTimeInMillis()) + "end time"+ new Date(endTime.getTimeInMillis()));
				PersonInfo p = personRepository.findOne(15L);
				List<CallLogInfo> callsLogs = callLogRepository.findCallLogs(p, startTime, endTime);
				
				for (CallLogInfo callLogInfo : callsLogs) {
					System.out.println("call "+callLogInfo);
				}
			};
		}
}
