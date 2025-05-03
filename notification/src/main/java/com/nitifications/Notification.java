package com.nitifications;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "notification_id_sequence"
    )
    private Integer id;

    private Integer toCustomerId;

    private String toCustomerEmail;

    private String sender;

    private String message;

    private LocalDateTime sentAt;


}
