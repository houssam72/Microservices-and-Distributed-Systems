package com.nitifications;

//import com.rabbitmq.RabbitMQMessageProducer;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
//import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.nitifications",
                "com.rabbitmq"
        }
)
@EnableDiscoveryClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
}
)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig
//    ) {
//        return args -> {
//            producer.publish(
//                    new Person("houssam",25),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey()
//            );
//        };
//    }
//
//    record Person(String name, int age){}

}