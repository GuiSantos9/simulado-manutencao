package FormatacaoDeCodigo.Ex9;

public class Relatorio {
    public void gerarRelatorio(String cliente, String email, double total, boolean premium, boolean pagamentoEmDia) {
        if (premium && pagamentoEmDia && total > 1000) {
            System.out.println("Relatório especial para " + cliente + " enviado para " + email + " com total de " + total);
        }
    }
}
