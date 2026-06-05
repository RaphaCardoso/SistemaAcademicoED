package model;

public class Curso {

	
	int codigoCurso;
	String nomeCurso;
	String areaConhecimento;
	
	
	public Curso() {
		this(0, "", "");	
	}
	
	@Override
	public String toString() {
		return codigoCurso +";"+ nomeCurso + ";" + areaConhecimento;
	}
	
	public Curso(int codigoCurso, String nomeCurso, String areaConhecimento) {
		this.codigoCurso = codigoCurso;
		this.nomeCurso = nomeCurso;
		this.areaConhecimento = areaConhecimento;
	}

	public int getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}
	
	
}
