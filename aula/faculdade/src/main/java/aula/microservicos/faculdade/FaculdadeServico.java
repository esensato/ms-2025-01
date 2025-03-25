package aula.microservicos.faculdade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class FaculdadeServico {

    private static final Logger logger = LoggerFactory.getLogger(FaculdadeServico.class);

    // Injeção da máquina de estado conforme configuração definida em
    // CancelamentoWorkflowConfig
    @Autowired
    private StateMachineFactory<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> cancelamentoFactory;

    private StateMachine<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> cancelamento;

    // aluno solicita o cancelamento
    public CancelamentoMatriculaEstado solicitar() {
        this.cancelamento = cancelamentoFactory.getStateMachine();
        logger.info("Solicitado Cancelamento::UUID = " + cancelamento.getUuid().toString());
        // inicia o WF
        this.cancelamento.startReactively().block();
        logger.info("Estado = " + cancelamento.getState().getId() + " - finalizado = " + cancelamento.isComplete());
        // retorna o estado atual dentro do WF
        return cancelamento.getState().getId();
    }

    // coordenador aprova a solicitacao
    public CancelamentoMatriculaEstado aprovarCoordenador() {
        // envia o evento APROVAR_COORDENADOR
        cancelamento
                .sendEvent(
                        Mono.just(MessageBuilder.withPayload(CancelamentoMatriculaEvento.APROVAR_COORDENADOR).build()))
                .blockFirst();
        logger.info("Estado = " + cancelamento.getState().getId() + " - finalizado = " + cancelamento.isComplete());
        return cancelamento.getState().getId();

    }

    // secretaria aprova a solicitação e finaliza o WF
    public CancelamentoMatriculaEstado aprovarSecretaria(String alunoId, String disciplinaId) {
        // incluir uma variavel para armazenar o id do aluno e da disciplina
        cancelamento.getExtendedState().getVariables().put("alunoId", alunoId);
        cancelamento.getExtendedState().getVariables().put("disciplinaId", disciplinaId);
        // envia o evento APROVAR_SECRETARIA
        cancelamento
                .sendEvent(
                        Mono.just(MessageBuilder.withPayload(CancelamentoMatriculaEvento.APROVAR_SECRETARIA).build()))
                .blockFirst();
        logger.info("Estado = " + cancelamento.getState().getId() + " - finalizado = " + cancelamento.isComplete());
        return cancelamento.getState().getId();
    }

}
