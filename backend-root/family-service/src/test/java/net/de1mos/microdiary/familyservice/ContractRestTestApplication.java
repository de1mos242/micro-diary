package net.de1mos.microdiary.familyservice;

import net.de1mos.microdiary.familyservice.config.SecurityConfig;
import org.axonframework.springboot.autoconfig.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        MetricsAutoConfiguration.class,
        EventProcessingAutoConfiguration.class,
        AxonAutoConfiguration.class,
        JpaAutoConfiguration.class,
        JpaEventStoreAutoConfiguration.class,
        JdbcAutoConfiguration.class,
        TransactionAutoConfiguration.class,
        NoOpTransactionAutoConfiguration.class,
        InfraConfiguration.class,
        ObjectMapperAutoConfiguration.class,
        AxonServerAutoConfiguration.class
})
@Import(SecurityConfig.class)
public class ContractRestTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContractRestTestApplication.class, args);
    }
}
