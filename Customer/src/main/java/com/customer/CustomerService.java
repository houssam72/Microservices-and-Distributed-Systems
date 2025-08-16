package com.customer;

import com.clients.fraud.FraudCheckResponse;
import com.clients.fraud.FraudClient;
import com.clients.notification.NotificationClient;
import com.clients.notification.NotificationRequest;
import com.rabbitmq.RabbitMQMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient, RabbitMQMessageProducer rabbitMQMessageProducer ) {



    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer= Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }


        NotificationRequest notificationRequest =  new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Houssam",
                String.format("Hi "+customer.getFirstName()+ ". I'm Houssam i'm so happy to lear from you"),
                LocalDateTime.now()
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
                        );



    }
}
