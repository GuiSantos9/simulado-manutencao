package CleanCode.ex14;

public class AlunoRepository {
    public void salvar(Aluno aluno) {

        System.out.println("Salvando o aluno: " + aluno.getNome() + " (CPF: " + aluno.getCpf() + ")");

    }
}
