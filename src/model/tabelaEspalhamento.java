package model;
import br.edu.estruturaDados.listaEncadeada.*;

public class tabelaEspalhamento {
	
    private ListaEncadeada<Disciplina>[] tabela;
    private int tamanho;

    public tabelaEspalhamento(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = (ListaEncadeada<Disciplina>[]) new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            this.tabela[i] = new ListaEncadeada<>();
        }
    }
    
    /*
    private int funcaoHash(String codigo) {
    	int tam = this.tamanho;
    	
    	char[] binario = codigo.toCharArray();
    	
    	if(tam>0) {
    		return Math.abs(codigo % tam);
    	}
    	else return 0;
    }

*/
    public void inserir(Disciplina d) {
        String codigo = d.getCodigo(); 
        //int posicao = funcaoHash(codigo);
        tabela[posicao].addFirst(d);
    }

    public ListaEncadeada<Disciplina> getLista(int posicao) {
        return this.tabela[posicao];
    }
}