package com.kafka.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topicPartitions =
                    { @TopicPartition(topic = "topic1",
                            partitionOffsets = @PartitionOffset(partition = "0-9", initialOffset = "0"))
                    },
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listener(String data) {
        System.out.println("Listener received: " + data);
    }


}
