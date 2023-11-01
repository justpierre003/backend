package com.example.muzfi;


import com.example.muzfi.Model.OrderDetails;

import com.example.muzfi.Repository.OrderDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class MuzfiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuzfiApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(
            OrderDetailsRepository orderDetailsRepository
    ) {
        return args -> {
            var orderDetails = OrderDetails.builder()
                    .OrderId("fgdr55k")
                    .orderAmount(7849.0)
                    .orderContactNumber("7845944893")

                    .build();
            orderDetailsRepository.insert(orderDetails);
        };
    }
}