package CleanCode.ex14;

public class Cadastro {

    private AlunoRepository alunoRepository;

    public void cadastrarAluno(Aluno aluno){
        alunoRepository.salvar(aluno);
    }
}
