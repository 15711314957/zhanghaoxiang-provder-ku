package com.bw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GoodluckZhanghaoxiangProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodluckZhanghaoxiangProviderApplication.class, args);
	}
}
