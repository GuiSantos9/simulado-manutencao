package AfinidadeConceitual.ex11;

public class FormatacaoUtils {
    public String formatarCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }
    public String formatarNome(String nome) {
        return nome.trim().toUpperCase();
    }
}
