package aula.microservicos.faculdade;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/faculdade")
public class Faculdade {

        @GetMapping("/token/{username}/{senha}")
        public ResponseEntity<String> getToken2(@PathVariable String username, @PathVariable String senha) {

                // repassar a requisição para o provedor de chave
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
                requestBody.add("username", username);
                requestBody.add("password", senha);
                requestBody.add("client_id", "faculdade-client");
                requestBody.add("grant_type", "password");

                ResponseEntity<String> response = restTemplate.postForEntity(
                                "http://localhost:9090/realms/DevHeml/protocol/openid-connect/token",
                                new HttpEntity<>(requestBody, headers), String.class);

                JSONObject json = new JSONObject(response.getBody());
                System.out.println(json);

                return response;
        }

        // http://localhost:8080/faculdade/1
        // Alterar o endpoint DELETE de disciplina para somente permitir excluir uma
        // disciplina que não possua alunos associados;
        @DeleteMapping("/disciplina/{idDisciplina}")
        public ResponseEntity<String> removerDisciplina(@PathVariable int idDisciplina) {

                RestClient restClient = RestClient.create();

                ResponseEntity<String> response = restClient.get()
                                .uri("http://localhost:8080/api/matricula/search/buscarTodasMatriculasPorDisciplina?idDisciplina={idDisciplina}",
                                                idDisciplina)
                                .retrieve()
                                .toEntity(String.class);
                JSONObject json = new JSONObject(response.getBody());
                JSONArray matriculas = json.getJSONObject("_embedded").getJSONArray("matriculaBeans");
                if (matriculas.length() > 0) {
                        return new ResponseEntity<String>("Existem matriculas para a disciplina!",
                                        HttpStatus.BAD_REQUEST);

                } else {
                        // Remover a disciplina
                        return new ResponseEntity<String>("OK", HttpStatus.OK);
                }

        }

        // Retornar a carga horária total de um aluno;
        // http://localhost:8080/faculdade/carga/1
        @GetMapping("/carga/{idAluno}")
        public ResponseEntity<Integer> cargaHoraria(@PathVariable int idAluno)
                        throws JsonProcessingException, JsonMappingException {
                DisciplinasAlunoBean disciplinas = disciplinasAluno(idAluno).getBody();
                int total = 0;
                for (DisciplinaBean disciplina : disciplinas.getDisciplinas()) {
                        total += disciplina.getCargaHoraria();
                }

                return new ResponseEntity<Integer>(total, HttpStatus.OK);

        }

        // Retornar todas as disciplinas ativas de um determinado aluno
        // http://localhost:8080/faculdade/disciplinas/1
        @GetMapping("/disciplinas/{idAluno}")
        public ResponseEntity<DisciplinasAlunoBean> disciplinasAluno(@PathVariable int idAluno)
                        throws JsonProcessingException, JsonMappingException {

                DisciplinasAlunoBean ret = new DisciplinasAlunoBean();
                // obtem dados das disciplinas
                Map<Integer, DisciplinaBean> disciplinas = obterTodasDisciplinas();
                // obtem dados do aluno
                AlunoBean aluno = obtemAluno(idAluno);
                ret.setAluno(aluno);

                RestClient restClient = RestClient.create();

                ResponseEntity<String> response = restClient.get()
                                .uri("http://localhost:8080/api/matricula/search/buscarTodasDisciplinasPorAluno?idAluno={idAluno}",
                                                idAluno)
                                .retrieve()
                                .toEntity(String.class);

                JSONObject json = new JSONObject(response.getBody());
                JSONArray matriculas = json.getJSONObject("_embedded").getJSONArray("matriculaBeans");

                for (int i = 0; i < matriculas.length(); i++) {
                        Integer id = matriculas.getJSONObject(i).getInt("idDisciplina");
                        String status = matriculas.getJSONObject(i).getString("status");
                        if (status.equals("A")) {
                                ret.addDisciplina(disciplinas.get(id));
                        }

                }

                return new ResponseEntity<DisciplinasAlunoBean>(ret, HttpStatus.OK);
        }

        // Obtem todas as disciplinas (http://localhost:8080/api/disciplina)
        private Map<Integer, DisciplinaBean> obterTodasDisciplinas()
                        throws JsonProcessingException, JsonMappingException {

                Map<Integer, DisciplinaBean> ret = new HashMap<Integer, DisciplinaBean>();
                RestClient restClient = RestClient.create();

                ResponseEntity<String> response = restClient.get()
                                .uri("http://localhost:8080/api/disciplina")
                                .retrieve()
                                .toEntity(String.class);
                JSONObject json = new JSONObject(response.getBody());

                JSONArray disciplinas = json.getJSONObject("_embedded").getJSONArray("disciplinaBeans");
                ObjectMapper objectMapper = new ObjectMapper()
                                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                for (int i = 0; i < disciplinas.length(); i++) {
                        DisciplinaBean disciplina = objectMapper.readValue(disciplinas.getJSONObject(i).toString(),
                                        DisciplinaBean.class);
                        ret.put(disciplina.getId(), disciplina);
                }

                return ret;

        }

        // Obtem dados do aluno
        // http://localhost:8080/aluno/100
        private AlunoBean obtemAluno(int idAluno) {

                RestClient restClient = RestClient.create();

                ResponseEntity<AlunoBean> response = restClient.get()
                                .uri("http://localhost:8080/aluno/{idAluno}", idAluno)
                                .retrieve()
                                .toEntity(AlunoBean.class);
                return response.getBody();

        }

}
