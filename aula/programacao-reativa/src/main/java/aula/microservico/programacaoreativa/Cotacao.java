package aula.microservico.programacaoreativa;

public class Cotacao {

    public static final String[] IDS = { "VALE3", "ITUB4", "PETR4", "BBDC4", "PETR4" };

    public Cotacao(String idEmpresa, float valor) {
        super();
        this.idEmpresa = idEmpresa;
        this.valor = valor;
    }

    private String idEmpresa;

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    private float valor;

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}