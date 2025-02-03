package aula.microservicos.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import aula.microservicos.Acao;

@Service
public class BolsaKafkaProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private static final Logger log = LoggerFactory.getLogger(BolsaKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Acao> kafkaTemplate;

    public void send(Acao acao) {
        kafkaTemplate.send(topicName, acao);
        log.debug("Enviado mensagem...");
    }

}