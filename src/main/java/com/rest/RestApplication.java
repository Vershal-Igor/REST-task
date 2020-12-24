package com.rest;

import com.github.javafaker.Faker;
import com.rest.entity.Region;
import com.rest.entity.User;
import com.rest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 1; i < 31; i++) {
                int id =+ i;
                String fullName = faker.name().fullName();
                String address = faker.address().fullAddress();
                String phone = faker.phoneNumber().cellPhone();
                repository.save(
                        new User(id, fullName, address, phone, Region.valueOf(new Random().nextInt(3))));
            }
        };
    }

}
