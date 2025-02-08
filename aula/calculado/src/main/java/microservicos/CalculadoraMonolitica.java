package microservicos;

public class CalculadoraMonolitica {

    public void calcular(String[] args) {
        String operacao = args[0];
        Integer n1 = Integer.parseInt(args[1]);
        Integer n2 = Integer.parseInt(args[2]);

        System.out.println("\n\n------------ CALCULADORA MONOLITICA ------------");
        System.out.print("Resultado: ");

        if (operacao.equals("soma")) {
            System.out.println(n1 + n2);
        } else if (operacao.equals("subtracao")) {
            System.out.println(n1 - n2);
        } else if (operacao.equals("divisao")) {
            System.out.println(n1 / n2);
        } else if (operacao.equals("multiplicacao")) {
            System.out.println(n1 * n2);
        } else {
            System.out.println("Operacao invalida: " + operacao);
        }

        System.out.println();

    }

    public static void main(String[] args) {

        // Calculadora instanciada pelo usuário, dono do fluxo principal
        CalculadoraMonolitica c = new CalculadoraMonolitica();
        c.calcular(args);

    }
}
