package controller;

import java.io.*;
import model.Curso;
import br.edu.estruturaDados.listaEncadeada.*;
import br.edu.estruturaDados.listaEncadeada.No;
import br.edu.estruturaDados.fila.*;

public class CursoController {

    private ListaEncadeada<Curso> lista;

    public CursoController() {
        lista = new ListaEncadeada<>();
    }

    public void carregarCSV(String caminho) throws IOException {

        lista = new ListaEncadeada<>();

        File file = new File(caminho);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader br = new BufferedReader(new FileReader(caminho));

        String linha;

        while ((linha = br.readLine()) != null) {

            if (linha.trim().isEmpty()) {
                continue;
            }

            String[] partes = linha.split(";");

            if (partes.length < 3) {
                continue;
            }

            Curso c = new Curso(
                Integer.parseInt(partes[0]),
                partes[1],
                partes[2]
            );

            lista.addLast(c);
        }

        br.close();
    }

    public void salvarCSV(String caminho) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {
            bw.write(aux.getDado().toString());
            bw.newLine();
            aux = aux.getProximo();
        }

        bw.close();
    }

    public void adicionar(Curso curso, String caminho) throws Exception {

        carregarCSV(caminho);

        if (buscar(curso.getCodigoCurso(), caminho) != null) {
            throw new Exception("Curso já existe!");
        }

        lista.addLast(curso);

        salvarCSV(caminho);
    }

    public Curso buscar(int codigo, String caminho) throws IOException {

        carregarCSV(caminho);

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {
            if (aux.getDado().getCodigoCurso() == codigo) {
                return aux.getDado();
            }
            aux = aux.getProximo();
        }

        return null;
    }

    public String listarTodos(String caminho) throws IOException {

        carregarCSV(caminho);

        Fila<Curso> fila = new Fila<>();

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {
            fila.enqueue(aux.getDado());
            aux = aux.getProximo();
        }

        StringBuilder sb = new StringBuilder();

        while (!fila.isEmpty()) {
            try {
                sb.append(fila.dequeue().toString()).append("\n");
            } catch (Exception e) {
                break;
            }
        }

        return sb.toString();
    }

    public void remover(int codigo, String caminho) throws Exception {

        carregarCSV(caminho);

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {
            if (aux.getDado().getCodigoCurso() == codigo) {
                lista.remove(aux.getDado());
                salvarCSV(caminho);
                return;
            }
            aux = aux.getProximo();
        }

        throw new Exception("Curso não encontrado!");
    }

    public void atualizar(int codigo, Curso novoCurso, String caminho) throws Exception {

        carregarCSV(caminho);

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {
            if (aux.getDado().getCodigoCurso() == codigo) {

                aux.getDado().setNomeCurso(novoCurso.getNomeCurso());
                aux.getDado().setAreaConhecimento(novoCurso.getAreaConhecimento());

                salvarCSV(caminho);
                return;
            }
            aux = aux.getProximo();
        }

        throw new Exception("Curso não encontrado!");
    }
}