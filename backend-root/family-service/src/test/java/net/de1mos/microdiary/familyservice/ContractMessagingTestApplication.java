package net.de1mos.microdiary.familyservice;

import net.de1mos.microdiary.familyservice.messaging.FamilyMessageProcessor;
import org.axonframework.springboot.autoconfig.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;

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
@EnableBinding(FamilyMessageProcessor.class)
public class ContractMessagingTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContractMessagingTestApplication.class, args);
    }
}
