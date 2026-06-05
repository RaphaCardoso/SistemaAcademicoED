package model;

public class Disciplina {
	
	public String codigo;
	public String nome;
	public String diasemana;
	public String horarioinicial;
	public String qtdhorasdiarias;
	public String codigocurso;
	//codigo do processo?
	
	@Override
	public String toString() {
		return codigo+";"+nome+";"+diasemana+";"+horarioinicial+";"+qtdhorasdiarias+";"+codigocurso;
	}
	
	

}
