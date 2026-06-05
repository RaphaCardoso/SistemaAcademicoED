package controller;

import java.io.*;
import model.*;

public class CursoController {

    private ListaCurso lista;

    public CursoController() {
        lista = new ListaCurso();
    }

    public void carregarCSV(String caminho) throws IOException {

        lista = new ListaCurso();

        File file = new File(caminho);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader br = new BufferedReader(new FileReader(caminho));

        String linha;

        while ((linha = br.readLine()) != null) {

            String[] partes = linha.split(";");

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

        NoCurso aux = lista.getPrimeiro();

        while (aux != null) {

            Curso c = aux.getDado();

            bw.write(c.toString());
            bw.newLine();

            aux = aux.getProximo();
        }

        bw.close();
    }

    public void adicionar(Curso curso, String caminho) throws Exception {

        carregarCSV(caminho);

        if (buscaRecursiva(lista.getPrimeiro(), curso.getCodigoCurso()) != null) {
            throw new Exception("Curso já existe!");
        }

        lista.addLast(curso);

        salvarCSV(caminho);
    }

    public Curso buscar(int codigo, String caminho) throws IOException {

        carregarCSV(caminho);

        return buscaRecursiva(lista.getPrimeiro(), codigo);
    }

    private Curso buscaRecursiva(NoCurso no, int codigo) {

        if (no == null) {
            return null;
        }

        if (no.getDado().getCodigoCurso() == codigo) {
            return no.getDado();
        }

        return buscaRecursiva(no.getProximo(), codigo);
    }
    
    public String listarTodos(String caminho) throws IOException {

        carregarCSV(caminho);

        StringBuilder sb = new StringBuilder();

        NoCurso aux = lista.getPrimeiro();

        while (aux != null) {
            sb.append(aux.getDado().toString()).append("\n");
            aux = aux.getProximo();
        }

        return sb.toString();
    }
    
    public void remover(int codigo, String caminho) throws Exception {

        carregarCSV(caminho);

        if (buscaRecursiva(lista.getPrimeiro(), codigo) == null) {
            throw new Exception("Curso não encontrado!");
        }

        ListaCurso novaLista = new ListaCurso();

        removerRecursivo(lista.getPrimeiro(), codigo, novaLista);

        lista = novaLista;

        salvarCSV(caminho);
    }
    
    private void removerRecursivo(NoCurso no, int codigo, ListaCurso novaLista) {
    	    	

        if (no == null) {
            return;
        }

        if (no.getDado().getCodigoCurso() != codigo) {
            novaLista.addLast(no.getDado());
        }

        removerRecursivo(no.getProximo(), codigo, novaLista);
    }
    
    public void atualizar(int codigo, Curso novoCurso, String caminho) throws Exception {
    	carregarCSV(caminho);

    	if (buscaRecursiva(lista.getPrimeiro(), codigo) == null) {
    	    throw new Exception("Curso não encontrado!");
    	}
    	
        ListaCurso novaLista = new ListaCurso();

        atualizarRecursivo(lista.getPrimeiro(), codigo, novoCurso, novaLista);

        lista = novaLista;

        salvarCSV(caminho);
    }
    
    private void atualizarRecursivo(NoCurso no, int codigo, Curso novoCurso, ListaCurso novaLista) {

        if (no == null) {
            return;
        }

        if (no.getDado().getCodigoCurso() == codigo) {
            novaLista.addLast(novoCurso);
        } else {
            novaLista.addLast(no.getDado());
        }

        atualizarRecursivo(no.getProximo(), codigo, novoCurso, novaLista);
    }
}