package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;

public class CadastroBDTeste {
    public static void main(String[] args) {
        // Criar uma PessoaFisica
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setId(1);
        pessoaFisica.setNome("João da Silva");
        pessoaFisica.setLogradouro("Rua ABC, 123");
        pessoaFisica.setCidade("Rio de Janeiro");
        pessoaFisica.setEstado("RJ");
        pessoaFisica.setTelefone("(21) 1234-5678");
        pessoaFisica.setEmail("joao@pf.com");
        pessoaFisica.setCpf("123.456.789-00");

        // Criar uma PessoaJuridica
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setId(2);
        pessoaJuridica.setNome("Empresa XYZ");
        pessoaJuridica.setLogradouro("Av. XYZ, 456");
        pessoaJuridica.setCidade("Rio de Janeiro");
        pessoaJuridica.setEstado("RJ");
        pessoaJuridica.setTelefone("(21) 99876-5432");
        pessoaJuridica.setEmail("empresa@pj.com");
        pessoaJuridica.setCnpj("12.345.678/0001-99");

        // Exibir os dados das pessoas
        System.out.println("Dados da Pessoa Física:");
        pessoaFisica.exibir();

        System.out.println("\nDados da Pessoa Jurídica:");
        pessoaJuridica.exibir();
    }
}
