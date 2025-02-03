package aula.microservicos.faculdade;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TesteJSON {
    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

        String val = "{\"nome\":\"Teste Nome\", \"turma\":\"5F\", \"curso\": \"SI\"}";
        String val2 = "[{\"nome\":\"Teste Nome\"}, {\"nome\":\"Teste Nome 2\"}]";

        System.out.println(val);
        System.out.println(val2);

        JSONObject obj = new JSONObject(val);
        JSONArray obj2 = new JSONArray(val2);

        System.out.println(obj.getString("nome"));

        System.out.println("Total objetos array: " + obj2.length());
        System.out.println(obj2.getJSONObject(1).getString("nome"));

        AlunoBean aluno = new AlunoBean();
        // aluno.setNome(obj.getString("nome"));
        // aluno.setTurma(obj.getString("turma"));
        // aluno.setCurso(obj.getString("curso"));

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        aluno = mapper.readValue(val, AlunoBean.class);

        System.out.println(aluno.getTurma());
        System.out.println(aluno.getCurso());
    }
}
