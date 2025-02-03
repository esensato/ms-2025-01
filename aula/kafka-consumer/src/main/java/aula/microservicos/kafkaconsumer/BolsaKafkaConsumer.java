package aula.microservicos.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import aula.microservicos.Acao;

@Service
public class BolsaKafkaConsumer {

    @Value("${topic.name.consumer}")
    private String topicName;

    private static final Logger log = LoggerFactory.getLogger(BolsaKafkaConsumer.class);

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, Acao> payload) {
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());

        log.info("Partion: {}", payload.partition());

        Acao ret = payload.value();
        log.info("Sigla: ", ret.sigla);
        log.info("Valor: ", ret.valor);
    }
}
