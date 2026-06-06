package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
			try {
				insere();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Consultar")) {
			try {
				consulta();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Atualizar")) {
			atualiza();
		}
		if(cmd.equals("Remover")) {
			remove();
		}
		
		
	}

	private void insere() throws IOException {
		Disciplina disciplina = new Disciplina();
		disciplina.codigo= tfDisciplinaCodigo.getText();
		disciplina.nome = tfDisciplinaNome.getText();
		disciplina.diasemana = tfDisciplinaSemana  .getText();
		disciplina.horarioinicial = tfDisciplinaHora    .getText();
		disciplina.qtdhorasdiarias = tfDisciplinaQtdHoras.getText();
		disciplina.codigocurso = tfDisciplinaCodCurso.getText();
				
		insereDisciplina(disciplina.toString());
		tfDisciplinaCodigo.setText("");
		tfDisciplinaNome.setText("");
		tfDisciplinaSemana.setText("");
		tfDisciplinaHora.setText("");
		tfDisciplinaQtdHoras.setText("");
		tfDisciplinaCodCurso.setText("");
	}
	
	private void insereDisciplina(String csvDisciplina)throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File (path,"displicina.csv");
		boolean existe = false;
		if(arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter (arq,existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvDisciplina+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}

	private void consulta() throws IOException {
		Disciplina disciplina = new Disciplina();
		disciplina.codigo= tfDisciplinaCodigo.getText();
//		disciplina.nome = tfDisciplinaNome.getText();
//		disciplina.diasemana = tfDisciplinaSemana  .getText();
//		disciplina.horarioinicial = tfDisciplinaHora    .getText();
//		disciplina.qtdhorasdiarias = tfDisciplinaQtdHoras.getText();
//		disciplina.codigocurso = tfDisciplinaCodCurso.getText();
		
//		System.out.println(disciplina);
		disciplina = consultaDisciplina(disciplina);
		if(disciplina.nome!=null) {
			taDisciplinaLista.setText("Código: " + disciplina.codigo+" - Nome: "
										+ disciplina.nome);
		}else {
			taDisciplinaLista.setText("Código inexistente.Disciplina não encontrada.");
		}
		
	}
	private Disciplina consultaDisciplina(Disciplina disciplina) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File (path,"disciplina.csv");
		if(arq.exists() &&arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while(linha !=null) {
				String[] vetLinha = linha.split(";");
				if(vetLinha[0].equals(disciplina.codigo)) {
					disciplina.nome=vetLinha[1];
					break;
				}
				linha=buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return disciplina;
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

	

	
	
	
	
	
	
	

}
