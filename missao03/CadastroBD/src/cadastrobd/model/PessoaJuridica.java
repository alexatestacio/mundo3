package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    // Construtores e polimorfismo em exibir

    public PessoaJuridica() {
    }

    public PessoaJuridica(int pessoaID, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(pessoaID, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
}
