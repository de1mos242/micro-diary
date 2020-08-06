package net.de1mos.microdiary.familyservice;

import net.de1mos.microdiary.familyservice.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import(SecurityConfig.class)
public class BaseTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseTestApplication.class, args);
    }
}
