package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private String cpf;

    // Construtores e polimorfismo em exibir

    public PessoaFisica() {
    }

    public PessoaFisica(int pessoaID, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(pessoaID, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }
}
