package net.de1mos.microdiary.familyservice;

import net.de1mos.microdiary.familyservice.messaging.FamilyMessageProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableBinding(FamilyMessageProcessor.class)
public class FamilyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyServiceApplication.class, args);
    }

    @GetMapping("hello")
    public String hello(Authentication auth) {
        return "I am: " + auth.getName();
    }
}
