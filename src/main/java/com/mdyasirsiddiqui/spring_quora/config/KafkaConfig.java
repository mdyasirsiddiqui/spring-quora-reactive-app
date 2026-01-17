package com.mdyasirsiddiqui.spring_quora.config;

import com.mdyasirsiddiqui.spring_quora.events.ViewCountEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.group-id:view-count-consumer}")
    private String groupId;

    public static final String TOPIC_NAME = "view-count-topic";

    // Producer Factory (sends messages)
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        log.info("Producer factory produced {} ", config);
        return new DefaultKafkaProducerFactory<>(config);
    }

    // Kafka Template (used by your app to send messages)
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // Consumer Factory (reads messages)
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.mdyasirsiddiqui.spring_quora.events");
        log.info("consumer consume {} ", config);

        return new DefaultKafkaConsumerFactory<>(config);
    }

//    @Bean
//    public ConsumerFactory<String, ViewCountEvent> consumerFactory()
//    {
//        Map<String, Object> config=new HashMap<>();
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, bootstrapServer);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        JsonDeserializer<ViewCountEvent> deserializer=new JsonDeserializer<>(ViewCountEvent.class);
//        deserializer.addTrustedPackages("com.mdyasirsiddiqui.spring_quora.events");
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
//
//    }
//    @Bean
//    public ConsumerFactory<String, ViewCountEvent> consumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        // Specify the target class
//        JsonDeserializer<ViewCountEvent> deserializer = new JsonDeserializer<>(ViewCountEvent.class);
//        deserializer.addTrustedPackages("com.mdyasirsiddiqui.spring_quora.events");
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
//    }

    // Listener Container Factory (connects @KafkaListener to consumer)
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

    // Example Listener (receives messages from topic)
    @KafkaListener(topics = "view-count-topic", groupId = "my-basic-consumer")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
