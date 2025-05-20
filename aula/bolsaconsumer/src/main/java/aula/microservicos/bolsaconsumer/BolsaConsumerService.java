package aula.microservicos.bolsaconsumer;

import java.util.HashSet;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BolsaConsumerService {

    @Value("${topic.name.consumer}")
    private String topicName;

    static Set<String> acoes = new HashSet<String>();

    private static final Logger log = LoggerFactory.getLogger(BolsaConsumerService.class);

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload) {
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Valor: {}", payload.value());

        acoes.add(payload.value());
    }

    public Set<String> getAcoes() {
        return acoes;
    }
}
