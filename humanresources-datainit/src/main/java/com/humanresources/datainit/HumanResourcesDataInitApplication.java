package com.humanresources.datainit;

import com.humanresources.datainit.initializers.CompanyInitializer;
import com.humanresources.datainit.initializers.SampleDataInitializer;
import com.humanresources.datainit.initializers.TypesInitializer;
import com.humanresources.datainit.initializers.UserInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.humanresources.model.entity")
@EnableJpaRepositories(basePackages = "com.humanresources.repository")
@ComponentScan(basePackages = {"com.humanresources.datainit", "com.humanresources.repository"})
public class HumanResourcesDataInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourcesDataInitApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner initializeData(
            CompanyInitializer companyInitializer,
            TypesInitializer typesInitializer,
            UserInitializer userInitializer,
            SampleDataInitializer sampleDataInitializer) {
        
        return args -> {
            System.out.println("Starting data initialization...");
            
            // Initialize in the correct order to handle dependencies
            companyInitializer.initialize();
            typesInitializer.initialize();
            userInitializer.initialize();
            sampleDataInitializer.initialize();
            
            System.out.println("Data initialization completed successfully.");
            
            // Exit the application after initialization completes
            System.exit(0);
        };
    }
}