package controller;

import java.io.IOException;

import br.edu.estruturaDados.listaEncadeada.*;
import model.Curso;
import model.Disciplina;
import model.DisciplinaProcesso;
import model.Inscricao;
import model.TabelaHashProcesso;

public class tabelaEspalhamentoController {

	public tabelaEspalhamentoController() {
		// TODO Auto-generated constructor stub
	}
	
	public TabelaHashProcesso gerarHash() throws IOException {

	    TabelaHashProcesso hash = new TabelaHashProcesso(31);

	    DisciplinaController dc = new DisciplinaController(null,null,null,null,null,null,null);
	    CursoController cc = new CursoController(null,null,null,null);
	    InscricaoController ic = new InscricaoController(null,null,null,null);

	    ListaEncadeada<Inscricao> inscricoes = ic.carregarListaInscricao();
	    ListaEncadeada<Disciplina> disciplinas = dc.carregarListaDisciplinas();
	    ListaEncadeada<Curso> cursos = cc.carregarLista();

	    No<Inscricao> i = inscricoes.getPrimeiro();

	    while (i != null) {

	        Inscricao insc = i.getDado();

	        Disciplina d = buscarDisciplina(insc.codDisciplina, disciplinas);

	        if (d != null) {
	            Curso c = buscarCurso(d.codigocurso, cursos);

	            DisciplinaProcesso dp =
	                new DisciplinaProcesso(d, c, insc.codProcesso_);

	            hash.inserir(dp);
	        }

	        i = i.getProximo();
	    }

	    return hash;
	}	
	private Disciplina buscarDisciplina(String codigo, ListaEncadeada<Disciplina> lista) {
	    No<Disciplina> aux = lista.getPrimeiro();

	    while (aux != null) {
	        if (aux.getDado().codigo.equals(codigo)) {
	            return aux.getDado();
	        }
	        aux = aux.getProximo();
	    }
	    return null;
	}
	
	private Curso buscarCurso(String codigo, ListaEncadeada<Curso> lista) {

	    if (codigo == null || codigo.isEmpty()) return null;

	    int codigoInt;

	    try {
	        codigoInt = Integer.parseInt(codigo);
	    } catch (NumberFormatException e) {
	        return null;
	    }

	    No<Curso> aux = lista.getPrimeiro();

	    while (aux != null) {    

	        Curso c = aux.getDado();

	        if (c != null && c.getCodigoCurso() == codigoInt) {
	            return c;
	        }

	        aux = aux.getProximo();
	    }

	    return null;
	}
}
