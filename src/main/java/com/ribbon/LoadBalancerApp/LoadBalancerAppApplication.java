package com.ribbon.LoadBalancerApp;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ribbon.config.LoadBalancerApp.RibbonConfig;

@SpringBootApplication
@RestController
@RibbonClient(name = "time-service",configuration = RibbonConfig.class)
public class LoadBalancerAppApplication {
	
	@Inject
	RestTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(LoadBalancerAppApplication.class, args);
	}
	
	@GetMapping("/")
	public String getTime()
	{
		return template.getForEntity("http://time-service", String.class).getBody();
	}

	@Bean
	@LoadBalanced
	public RestTemplate template()
	{
		return new RestTemplate();
	}
}
