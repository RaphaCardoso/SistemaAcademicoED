package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Disciplina;

public class DisciplinaController implements ActionListener{
	
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaHora;
	private JTextField tfDisciplinaQtdHoras;
	private JTextField tfDisciplinaCodCurso;
	private JTextField tfDisciplinaSemana;
	private JTextArea taDisciplinaLista;
	
	public DisciplinaController(JTextField tfDisciplinaCodigo, JTextField tfDisciplinaNome, JTextField tfDisciplinaHora,
			JTextField tfDisciplinaQtdHoras, JTextField tfDisciplinaCodCurso, JTextField tfDisciplinaSemana,
			JTextArea taDisciplinaLista) {
		super();
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaNome = tfDisciplinaNome;
		this.tfDisciplinaHora = tfDisciplinaHora;
		this.tfDisciplinaQtdHoras = tfDisciplinaQtdHoras;
		this.tfDisciplinaCodCurso = tfDisciplinaCodCurso;
		this.tfDisciplinaSemana = tfDisciplinaSemana;
		this.taDisciplinaLista = taDisciplinaLista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Inserir")) {
			insere();
		}
		if(cmd.equals("Consultar")) {
			consulta();
		}
		if(cmd.equals("Atualizar")) {
			atualiza();
		}
		if(cmd.equals("Remover")) {
			remove();
		}
		
		
	}

	private void remove() {
		Disciplina disciplina = new Disciplina();
		disciplina.codigo= tfDisciplinaCodigo.getText();
		disciplina.nome = tfDisciplinaNome.getText();
		disciplina.diasemana = tfDisciplinaSemana  .getText();
		disciplina.horarioinicial = tfDisciplinaHora    .getText();
		disciplina.qtdhorasdiarias = tfDisciplinaQtdHoras.getText();
		disciplina.codigocurso = tfDisciplinaCodCurso.getText();
		
		System.out.println(disciplina);
		                     
	}

	private void atualiza() {
		Disciplina disciplina = new Disciplina();
		disciplina.codigo= tfDisciplinaCodigo.getText();
		disciplina.nome = tfDisciplinaNome.getText();
		disciplina.diasemana = tfDisciplinaSemana  .getText();
		disciplina.horarioinicial = tfDisciplinaHora    .getText();
		disciplina.qtdhorasdiarias = tfDisciplinaQtdHoras.getText();
		disciplina.codigocurso = tfDisciplinaCodCurso.getText();
		
		System.out.println(disciplina);
		
	}

	private void consulta() {
		Disciplina disciplina = new Disciplina();
		disciplina.codigo= tfDisciplinaCodigo.getText();
		disciplina.nome = tfDisciplinaNome.getText();
		disciplina.diasemana = tfDisciplinaSemana  .getText();
		disciplina.horarioinicial = tfDisciplinaHora    .getText();
		disciplina.qtdhorasdiarias = tfDisciplinaQtdHoras.getText();
		disciplina.codigocurso = tfDisciplinaCodCurso.getText();
		
		System.out.println(disciplina);
		
	}

	private void insere() {
		Disciplina disciplina = new Disciplina();
		disciplina.codigo= tfDisciplinaCodigo.getText();
		disciplina.nome = tfDisciplinaNome.getText();
		disciplina.diasemana = tfDisciplinaSemana  .getText();
		disciplina.horarioinicial = tfDisciplinaHora    .getText();
		disciplina.qtdhorasdiarias = tfDisciplinaQtdHoras.getText();
		disciplina.codigocurso = tfDisciplinaCodCurso.getText();
		
		System.out.println(disciplina);
		
	}
	
	
	
	
	
	

}
