package com.ribbon.config.LoadBalancerApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class RibbonConfig {
	
	@Bean
	public IRule ribbonRule()
	{
		return new RandomRule();
	}

}
