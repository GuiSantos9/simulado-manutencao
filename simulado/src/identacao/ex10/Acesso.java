package identacao.ex10;

public class Acesso {
    public void verificarAcesso(boolean ativo, boolean admin) {
        if (ativo) {
            if (admin) {
                System.out.println("Acesso administrativo");
            } else {
                System.out.println("Acesso comum");
            }
        } else {
            System.out.println("Usuário inativo");
        }
    }
}
