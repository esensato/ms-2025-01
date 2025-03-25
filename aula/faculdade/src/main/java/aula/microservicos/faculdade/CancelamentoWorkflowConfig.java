package aula.microservicos.faculdade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

// Configurações da máquina de estado
@Configuration
@EnableStateMachineFactory
public class CancelamentoWorkflowConfig
        extends StateMachineConfigurerAdapter<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> {

    private static final Logger logger = LoggerFactory.getLogger(CancelamentoWorkflowConfig.class);

    @Autowired
    private MatriculaIntegracao matricula;

    // configurações gerais
    @Override
    public void configure(
            StateMachineConfigurationConfigurer<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> config)
            throws Exception {

        config.withConfiguration()
                .autoStartup(true)
                .listener(listener())
                .machineId("cancelamento-matricula");
    }

    // metodo acionado quando houver mudança de estados
    @Bean
    public StateMachineListener<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> listener() {
        return new StateMachineListenerAdapter<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento>() {
            @Override
            public void stateChanged(State<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> from,
                    State<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> to) {

                if (from != null) {
                    logger.info("Estado alterado de: " + from.getId());
                }
                logger.info("Estado alterado para: " + to.getId());
            }
        };
    }

    // configurar os estados e definir o estado inicial e o estado final
    @Override
    public void configure(StateMachineStateConfigurer<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> states)
            throws Exception {

        states.withStates()
                .initial(CancelamentoMatriculaEstado.SOLICITADO, initialAction())
                .end(CancelamentoMatriculaEstado.APROVADO_SECRETARIA)
                .state(CancelamentoMatriculaEstado.APROVADO_COORDENADOR);

    }

    // criar um método (opcional) para iniciar o WF
    @Bean
    public Action<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> initialAction() {
        return new Action<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento>() {

            @Override
            public void execute(StateContext<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> context) {
                logger.info("Iniciado o WF Cancelamento de Matricula");
            }
        };
    }

    // Definir os eventos entre os estados
    @Override
    public void configure(
            StateMachineTransitionConfigurer<CancelamentoMatriculaEstado, CancelamentoMatriculaEvento> transitions)
            throws Exception {

        transitions.withExternal()
                // SOLICITADO -> |APROVAR_COORDENADOR| -> APROVADO_COORDENADOR
                .source(CancelamentoMatriculaEstado.SOLICITADO)
                .event(CancelamentoMatriculaEvento.APROVAR_COORDENADOR)
                .target(CancelamentoMatriculaEstado.APROVADO_COORDENADOR)
                .and()
                .withExternal()
                // APROVADO_COORDENADOR -> |APROVAR_SECRETARIA| -> APROVADO_SECRETARIA (FIM)
                .source(CancelamentoMatriculaEstado.APROVADO_COORDENADOR)
                .event(CancelamentoMatriculaEvento.APROVAR_SECRETARIA)
                .target(CancelamentoMatriculaEstado.APROVADO_SECRETARIA)
                // disparara uma ação quando o estado for APROVADO_SECRETARIA
                .action(context -> {
                    logger.info("Efetuar um request no endpoint matricula para efetivar o cancelamento...");
                    // obter o valor das variaveis alunoId e disciplinaId
                    Map<Object, Object> variaveis = context.getStateMachine().getExtendedState().getVariables();
                    logger.info(variaveis.toString());
                    // excluir a matricula acessando o endpoint do microservico matricula
                    matricula.excluir(variaveis.get("disciplinaId").toString());
                });

    }

}
