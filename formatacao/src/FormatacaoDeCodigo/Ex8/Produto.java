package FormatacaoDeCodigo.Ex8;

public class Produto {
    public void cadastrarProduto(String nome, double preco) {

        String nomeFormatado = nome.trim().toUpperCase();
        boolean precoValido = preco > 0;

        if (!precoValido) {
            System.out.println("Preço inválido");
            return;
        }

        System.out.println("Produto: " + nomeFormatado);
        System.out.println("Preço: " + preco);
        System.out.println("Cadastro finalizado");
    }
}
