package com.backintro.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = "com.backintro",
        exclude = UserDetailsServiceAutoConfiguration.class
)
public class MiappApplication {
 public static void main(String[] args) {
     SpringApplication.run(MiappApplication.class, args);
 }
}
