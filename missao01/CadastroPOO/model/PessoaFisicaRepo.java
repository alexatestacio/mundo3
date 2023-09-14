package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo {
    private List<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        pessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaFisica) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaFisica pf = pessoasFisicas.get(i);
            if (pf.getId() == pessoaFisica.getId()) {
                pessoasFisicas.set(i, pessoaFisica);
                break;
            }
        }
    }

    public boolean excluir(int id) {
        PessoaFisica pessoaFisica = obter(id);
        if (pessoaFisica != null) {
            return pessoasFisicas.remove(pessoaFisica);
        }
        return false;
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica pessoaFisica : pessoasFisicas) {
            if (pessoaFisica.getId() == id) {
                return pessoaFisica;
            }
        }
        return null;
    }

    public List<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        output.writeObject(pessoasFisicas);
        output.close();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomeArquivo));
        pessoasFisicas = (ArrayList<PessoaFisica>) input.readObject();
        input.close();
    }

    public void salvarDados(String nomeArquivo, String prefixoArquivos) throws IOException {
        String nomeCompletoArquivo = prefixoArquivos + ".fisica.bin";

        try (FileOutputStream fileOut = new FileOutputStream(nomeCompletoArquivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(pessoasFisicas);
        }
    }

    public void recuperarDados(String nomeArquivo, String prefixoArquivos) throws IOException, ClassNotFoundException {
        String nomeCompletoArquivo = prefixoArquivos + ".fisica.bin";

        try (FileInputStream fileIn = new FileInputStream(nomeCompletoArquivo);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            pessoasFisicas = (List<PessoaFisica>) objectIn.readObject();
        }
    }
}
