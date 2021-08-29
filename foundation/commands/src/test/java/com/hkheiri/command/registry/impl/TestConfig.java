package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Haseem Kheiri
 *
 */
@Configuration
@ComponentScan(basePackages = "com.hkheiri.command.registry.impl")
public class TestConfig {
  @Bean
  public CommandRegistry commandRegistry() {
    final CommandRegistry commandRegistry = new CommandRegistry();
    commandRegistry.setObjectMapper(new ObjectMapper());
    return commandRegistry;
  }
}
