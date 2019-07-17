package ru.itfbgroup.telecom.services.notificationservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itfbgroup.telecom.services.notificationservice.service.ClientService;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner createCustomers(ClientService service) {
        return args -> {
            service.create("dima", "123", "Дима", "USER");
            service.create("sasha", "456", "Саша", "ADMIN");
        };
    }
}
