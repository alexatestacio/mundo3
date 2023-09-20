package cadastrobd;

import cadastrobd.model.Pessoa;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;


public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        List<PessoaFisica> pessoas = pessoaFisicaDAO.getPessoas();

        for(Pessoa pessoa: pessoas) {
            System.out.println(pessoa.getNome());
        }

        System.out.println("Imprimindo pessoa 1");

        PessoaFisica pessoa1 = pessoaFisicaDAO.getPessoa(1);
        System.out.println(pessoa1.getNome());

        System.out.println("Inserindo nova pessoa");
        PessoaFisica novaPessoa = new PessoaFisica(1, "Teste Inclusao", "Teste Logradouro", "Teste cidade", "RJ", "2223-5565", "teste@atee.com", "22220005552");
        pessoaFisicaDAO.incluir(novaPessoa);

        System.out.println("Agora atualiza pessoa");
        novaPessoa.setNome("Pessoa Nome Alterado");
        pessoaFisicaDAO.alterar(novaPessoa);
    }
}
