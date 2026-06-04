package model;

public class NoCurso {

	Curso dado;
	NoCurso proximo;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Curso getDado() {
		return dado;
	}

	public void setDado(Curso dado) {
		this.dado = dado;
	}

	public NoCurso getProximo() {
		return proximo;
	}

	public void setProximo(NoCurso proximo) {
		this.proximo = proximo;
	}
	
	
	
}
