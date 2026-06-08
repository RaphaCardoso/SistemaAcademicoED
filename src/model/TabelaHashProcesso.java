package model;

import br.edu.estruturaDados.listaEncadeada.ListaEncadeada;
import br.edu.estruturaDados.listaEncadeada.No;

public class TabelaHashProcesso {

    private ListaEncadeada<DisciplinaProcesso>[] tabela;
    private int tamanho;

    public TabelaHashProcesso(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ListaEncadeada[tamanho];

        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada<>();
        }
    }

    private int funcaoHash(String codigoDisciplina) {
        int hash = 0;

        for (int i = 0; i < codigoDisciplina.length(); i++) {
            hash = (hash * 31) + codigoDisciplina.charAt(i);
        }

        return Math.abs(hash % tamanho);
    }

    public void inserir(DisciplinaProcesso dp) {
        int pos = funcaoHash(dp.disciplina.codigo);
        tabela[pos].addLast(dp);
    }

    public ListaEncadeada<DisciplinaProcesso>[] getTabela() {
        return tabela;
    }
}