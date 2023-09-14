package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        pessoasJuridicas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica pj = pessoasJuridicas.get(i);
            if (pj.getId() == pessoaJuridica.getId()) {
                pessoasJuridicas.set(i, pessoaJuridica);
                break;
            }
        }
    }

    public boolean excluir(int id) {
        PessoaJuridica pessoaJuridica = obter(id);
        if (pessoaJuridica != null) {
            return pessoasJuridicas.remove(pessoaJuridica);
        }
        return false;
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
            if (pessoaJuridica.getId() == id) {
                return pessoaJuridica;
            }
        }
        return null;
    }

    public List<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        output.writeObject(pessoasJuridicas);
        output.close();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomeArquivo));
        pessoasJuridicas = (ArrayList<PessoaJuridica>) input.readObject();
        input.close();
    }

    public void salvarDados(String nomeArquivo, String prefixoArquivos) throws IOException {
        String nomeCompletoArquivo = prefixoArquivos + ".juridica.bin";

        try (FileOutputStream fileOut = new FileOutputStream(nomeCompletoArquivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(pessoasJuridicas);
        }
    }

    public void recuperarDados(String nomeArquivo, String prefixoArquivos) throws IOException, ClassNotFoundException {
        String nomeCompletoArquivo = prefixoArquivos + ".juridica.bin";

        try (FileInputStream fileIn = new FileInputStream(nomeCompletoArquivo);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            pessoasJuridicas = (List<PessoaJuridica>) objectIn.readObject();
        }
    }
}
