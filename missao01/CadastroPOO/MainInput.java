import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;

public class MainInput {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String nomeArquivoFisica = "dados.fisica.bin";
        String nomeArquivoJuridica = "dados.juridica.bin";

        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        boolean executando = true;
        while (executando) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("6. Salvar dados");
            System.out.println("7. Recuperar dados");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    incluirPessoa(repoFisica, repoJuridica);
                    break;
                case 2:
                    alterarPessoa(repoFisica, repoJuridica);
                    break;
                case 3:
                    excluirPessoa(repoFisica, repoJuridica);
                    break;
                case 4:
                    exibirPorId(repoFisica, repoJuridica);
                    break;
                case 5:
                    exibirTodos(repoFisica, repoJuridica);
                    break;
                case 6:
                    salvarDados(repoFisica, repoJuridica, nomeArquivoFisica, nomeArquivoJuridica);
                    break;
                case 7:
                    recuperarDados(repoFisica, repoJuridica, nomeArquivoFisica, nomeArquivoJuridica);
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("Encerrando o programa...");
    }

    private static void incluirPessoa(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Selecione o tipo de pessoa:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");

        int tipoPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (tipoPessoa == 1) {
            PessoaFisica pessoaFisica = obterDadosPessoaFisica();
            repoFisica.inserir(pessoaFisica);
            System.out.println("Pessoa física adicionada com sucesso.");
        } else if (tipoPessoa == 2) {
            PessoaJuridica pessoaJuridica = obterDadosPessoaJuridica();
            repoJuridica.inserir(pessoaJuridica);
            System.out.println("Pessoa jurídica adicionada com sucesso.");
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void alterarPessoa(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Selecione o tipo de pessoa:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");

        int tipoPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (tipoPessoa == 1) {
            System.out.println("Digite o ID da pessoa física:");
            int idFisica = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            PessoaFisica pessoaFisica = repoFisica.obter(idFisica);
            if (pessoaFisica != null) {
                System.out.println("Dados atuais da pessoa física:");
                pessoaFisica.exibir();

                System.out.println("Digite o novo nome:");
                String novoNome = scanner.nextLine();
                System.out.println("Digite o novo CPF:");
                String novoCpf = scanner.nextLine();
                System.out.println("Digite a nova idade:");
                int novaIdade = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                pessoaFisica.setNome(novoNome);
                pessoaFisica.setCpf(novoCpf);
                pessoaFisica.setIdade(novaIdade);

                System.out.println("Pessoa física alterada com sucesso.");
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipoPessoa == 2) {
            System.out.println("Digite o ID da pessoa jurídica:");
            int idJuridica = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            PessoaJuridica pessoaJuridica = repoJuridica.obter(idJuridica);
            if (pessoaJuridica != null) {
                System.out.println("Dados atuais da pessoa jurídica:");
                pessoaJuridica.exibir();

                System.out.println("Digite o novo nome:");
                String novoNome = scanner.nextLine();
                System.out.println("Digite o novo CNPJ:");
                String novoCnpj = scanner.nextLine();

                pessoaJuridica.setNome(novoNome);
                pessoaJuridica.setCnpj(novoCnpj);

                System.out.println("Pessoa jurídica alterada com sucesso.");
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void excluirPessoa(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Selecione o tipo de pessoa:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");

        int tipoPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (tipoPessoa == 1) {
            System.out.println("Digite o ID da pessoa física:");
            int idFisica = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (repoFisica.excluir(idFisica)) {
                System.out.println("Pessoa física excluída com sucesso.");
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipoPessoa == 2) {
            System.out.println("Digite o ID da pessoa jurídica:");
            int idJuridica = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (repoJuridica.excluir(idJuridica)) {
                System.out.println("Pessoa jurídica excluída com sucesso.");
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void exibirPorId(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Selecione o tipo de pessoa:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");

        int tipoPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (tipoPessoa == 1) {
            System.out.println("Digite o ID da pessoa física:");
            int idFisica = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            PessoaFisica pessoaFisica = repoFisica.obter(idFisica);
            if (pessoaFisica != null) {
                pessoaFisica.exibir();
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipoPessoa == 2) {
            System.out.println("Digite o ID da pessoa jurídica:");
            int idJuridica = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            PessoaJuridica pessoaJuridica = repoJuridica.obter(idJuridica);
            if (pessoaJuridica != null) {
                pessoaJuridica.exibir();
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void exibirTodos(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Selecione o tipo de pessoa:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");

        int tipoPessoa = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (tipoPessoa == 1) {
            List<PessoaFisica> pessoasFisicas = repoFisica.obterTodos();
            if (!pessoasFisicas.isEmpty()) {
                System.out.println("Pessoas físicas cadastradas:");
                for (PessoaFisica pessoa : pessoasFisicas) {
                    pessoa.exibir();
                    System.out.println();
                }
            } else {
                System.out.println("Não há pessoas físicas cadastradas.");
            }
        } else if (tipoPessoa == 2) {
            List<PessoaJuridica> pessoasJuridicas = repoJuridica.obterTodos();
            if (!pessoasJuridicas.isEmpty()) {
                System.out.println("Pessoas jurídicas cadastradas:");
                for (PessoaJuridica pessoa : pessoasJuridicas) {
                    pessoa.exibir();
                    System.out.println();
                }
            } else {
                System.out.println("Não há pessoas jurídicas cadastradas.");
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void salvarDados(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica, String nomeArquivoFisica, String nomeArquivoJuridica) {
        try {
            System.out.println("Digite o prefixo dos arquivos:");
            String prefixoArquivos = scanner.nextLine();

            repoFisica.salvarDados(nomeArquivoFisica, prefixoArquivos);
            repoJuridica.salvarDados(nomeArquivoJuridica, prefixoArquivos);

            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados(PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica, String nomeArquivoFisica, String nomeArquivoJuridica) {
        try {
            System.out.println("Digite o prefixo dos arquivos:");
            String prefixoArquivos = scanner.nextLine();

            repoFisica.recuperarDados(nomeArquivoFisica, prefixoArquivos);
            repoJuridica.recuperarDados(nomeArquivoJuridica, prefixoArquivos);

            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }

    private static PessoaFisica obterDadosPessoaFisica() {
        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();
        System.out.println("Digite a idade:");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        return new PessoaFisica(id, nome, cpf, idade);
    }

    private static PessoaJuridica obterDadosPessoaJuridica() {
        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CNPJ:");
        String cnpj = scanner.nextLine();

        return new PessoaJuridica(id, nome, cnpj);
    }
}
