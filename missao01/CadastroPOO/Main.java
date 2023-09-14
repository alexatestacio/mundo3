import java.io.IOException;
import java.util.List;

import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;


public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "dados.dat";

        // Repositório de pessoas físicas
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();

        // Adicionar duas pessoas físicas
        PessoaFisica pessoa1 = new PessoaFisica(1, "João", "123456789", 25);
        PessoaFisica pessoa2 = new PessoaFisica(2, "Maria", "987654321", 30);
        repo1.inserir(pessoa1);
        repo1.inserir(pessoa2);

        try {
            // Persistir os dados
            System.out.println("Dados de pessoa física Armazenados");
            repo1.persistir(nomeArquivo);

            // Recuperar os dados em outro repositório de pessoas físicas
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            System.out.println("Dados de pessoa física Recuperados");
            repo2.recuperar(nomeArquivo);

            // Exibir os dados de todas as pessoas físicas recuperadas
            List<PessoaFisica> pessoasFisicas = repo2.obterTodos();
            for (PessoaFisica pessoa : pessoasFisicas) {
                pessoa.exibir();
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Repositório de pessoas jurídicas
        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

        // Adicionar duas pessoas jurídicas
        PessoaJuridica empresa1 = new PessoaJuridica(1, "Empresa 1", "123456789");
        PessoaJuridica empresa2 = new PessoaJuridica(2, "Empresa 2", "987654321");
        repo3.inserir(empresa1);
        repo3.inserir(empresa2);

        try {
            // Persistir os dados
            repo3.persistir(nomeArquivo);

            // Recuperar os dados em outro repositório de pessoas jurídicas
            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar(nomeArquivo);

            // Exibir os dados de todas as pessoas jurídicas recuperadas
            List<PessoaJuridica> pessoasJuridicas = repo4.obterTodos();
            for (PessoaJuridica pessoa : pessoasJuridicas) {
                pessoa.exibir();
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
