package microservicos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalculadoraSpring {

    // operação é definida no beans.xml
    private Operacao operacao;

    public void calcular(String[] args) {
        Integer n1 = Integer.parseInt(args[0]);
        Integer n2 = Integer.parseInt(args[1]);

        System.out.println("\n------------ CALCULADORA SPRING ------------");
        System.out.print("Resultado: ");
        // qual operação será executada? Depende do que foi definido no beans.xml
        System.out.println(operacao.executar(n1, n2));
        System.out.println();

    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // cria o contexto e carrega os beans definidos no beans.xml
        // ou anotados com @Service (vide Multiplicar e Somar)
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        System.out.println("\nBeans reconhecidos dentro do contexto:\n");

        for (String beanName : ctx.getBeanDefinitionNames()) {
            // Evita que o nome dos beans do proprio spring sejam exibidos
            if (!beanName.contains("org.springframework")) {
                System.out.println(" - " + beanName);
            }
        }

        CalculadoraSpring c = ctx.getBean("calculadora", CalculadoraSpring.class);
        c.calcular(args);

    }
}
