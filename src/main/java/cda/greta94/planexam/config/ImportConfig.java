package cda.greta94.planexam.config;

import cda.greta94.planexam.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportConfig {
  @Autowired
  private DatabaseService databaseService;

  @Bean
  public void initializeDatabase() {
    databaseService.initializeDatabase();
  }
}
