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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiasemana() {
		return diasemana;
	}

	public void setDiasemana(String diasemana) {
		this.diasemana = diasemana;
	}

	public String getHorarioinicial() {
		return horarioinicial;
	}

	public void setHorarioinicial(String horarioinicial) {
		this.horarioinicial = horarioinicial;
	}

	public String getQtdhorasdiarias() {
		return qtdhorasdiarias;
	}

	public void setQtdhorasdiarias(String qtdhorasdiarias) {
		this.qtdhorasdiarias = qtdhorasdiarias;
	}

	public String getCodigocurso() {
		return codigocurso;
	}

	public void setCodigocurso(String codigocurso) {
		this.codigocurso = codigocurso;
	}
	
	

}
