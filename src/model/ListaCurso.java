package model;

public class ListaCurso {

	private NoCurso primeiro;
	
	public boolean listaVazia() {
	    return primeiro == null;
	}
	
	public int tamanho() {
	    int cont = 0;

	    NoCurso auxiliar = primeiro;

	    while (auxiliar != null) {
	        cont++;
	        auxiliar = auxiliar.getProximo();
	    }

	    return cont;
	}
	
	public void addFirst(Curso curso) {

	    NoCurso novo = new NoCurso();
	    novo.setDado(curso);
	    novo.setProximo(primeiro);

	    primeiro = novo;
	}
	
	public void addLast(Curso curso) {

	    if (listaVazia()) {
	        addFirst(curso);
	    } else {
	        NoCurso novo = new NoCurso();
	        novo.setDado(curso);
	        novo.setProximo(null);

	        NoCurso auxiliar = primeiro;

	        while (auxiliar.getProximo() != null) {
	            auxiliar = auxiliar.getProximo();
	        }

	        auxiliar.setProximo(novo);
	    }
	}
	
	public Curso get(int pos) throws Exception {

	    if (listaVazia()) {
	        throw new Exception("Lista vazia");
	    }

	    if (pos < 0 || pos >= tamanho()) {
	        throw new Exception("Posição inválida");
	    }

	    NoCurso auxiliar = primeiro;

	    for (int i = 0; i < pos; i++) {
	        auxiliar = auxiliar.getProximo();
	    }

	    return auxiliar.getDado();
	}

}
