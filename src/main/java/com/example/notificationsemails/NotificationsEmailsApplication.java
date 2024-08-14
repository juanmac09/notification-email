package com.example.notificationsemails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NotificationsEmailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationsEmailsApplication.class, args);
    }

}
