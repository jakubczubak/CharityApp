package pl.czubak.charityapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class CharityAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityAppApplication.class, args);
    }

}
