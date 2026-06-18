package FormatacaoDeCodigo.Ex7;

public class PedidoService {
    public void fecharPedido(String cliente, double subtotal, double frete) {
        double total = calcularTotal(subtotal, frete);
        imprimirResumo(cliente, total);
    }

    private void imprimirResumo(String cliente, double total) {
        System.out.println(cliente + ": " + total);
    }

    private double calcularTotal(double subtotal, double frete) {
        return subtotal + frete;
    }
}


