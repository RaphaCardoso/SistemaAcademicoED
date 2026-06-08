package model;

public class DisciplinaProcesso {
    public Disciplina disciplina;
    public Curso curso;
    public String codigoProcesso;

    public DisciplinaProcesso(Disciplina d, Curso c, String processo) {
        this.disciplina = d;
        this.curso = c;
        this.codigoProcesso = processo;
    }
}	