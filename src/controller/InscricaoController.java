package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.estruturaDados.fila.Fila;
import br.edu.estruturaDados.listaEncadeada.ListaEncadeada;
import br.edu.estruturaDados.listaEncadeada.No;
import model.Inscricao;
import model.Professor;
import model.Disciplina;
import controller.DisciplinaController;
import controller.ProfessorController;




public class InscricaoController implements ActionListener {
    
	private JTextField TFCodProcesso_;
	private JTextField TFCPFInsc;
	private JTextField TFDaDisciplina;
	private JTextArea TaInsc;
	private ProfessorController profController;
	private DisciplinaController disciplinaController;
	
	public InscricaoController(JTextField TFCodProcesso_, JTextField TFCPFInsc, JTextField TFDaDisciplina, JTextArea TaInsc) {
		super();
		this.TFCodProcesso_ = TFCodProcesso_;
		this.TFCPFInsc = TFCPFInsc;
		this.TFDaDisciplina = TFDaDisciplina;
		this.TaInsc = TaInsc;
		this.disciplinaController = new DisciplinaController(null,null,null,null,null,null,null);
		this.profController = new ProfessorController(null,null,null,null,null);
		
	}
	
	@Override

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Cadastrar")) {
			try {
				insere();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Buscar Inscrição")) {
			try {
				consulta();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Atualizar Inscrição")) {
			try {
				atualiza();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Deletar Inscrição")) {
			try {
				remove();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	private void insere() throws IOException {

	    String cpf = TFCPFInsc.getText();
	    String codProcesso = TFCodProcesso_.getText();
	    String codDisciplina = TFDaDisciplina.getText();
	    
	    Inscricao inscricao = new Inscricao();
	    Professor professor = new Professor();
	    Disciplina disciplina = new Disciplina();
	    
		   
	    if(cpf.isEmpty() ||
	       codProcesso.isEmpty() ||
	       codDisciplina.isEmpty()) {
	    	
	        TaInsc.setText(
	            "Todos os campos devem ser preenchidos."
	        );
	        return;
	    } 
	    
	    
	    if (!profController.cpfJaExiste(cpf)) {
	    	
	    	TaInsc.setText("Cadastro de Professor não encontrado.");
	    	
	    	return;
		}
	    
	    if(!disciplinaController.codigoJaExiste(codDisciplina)) {
	    	TaInsc.setText("Cadastro de Disciplina não encontrado. ");
	    	
	    return;
	    }
	    
	    
	    inscricao.cpf = cpf;
	    inscricao.codProcesso_ = codProcesso;
	    inscricao.codDisciplina = codDisciplina;

	    insereInscricao(inscricao.toString());

	    TaInsc.setText(
	        "Inscrição cadastrado com sucesso."
	    );

		TFCodProcesso_.setText("");
		TFCPFInsc.setText("");
		TFDaDisciplina.setText("");
	}
	
	
	
		private void insereInscricao(String csvInscricao)throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File (path,"inscricoes.csv");
		boolean existe = false;
		if(arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter (arq,existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvInscricao+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
		
	}
		
	private void consulta() throws IOException {
		
		Inscricao inscri = new Inscricao();

		inscri.codProcesso_ = TFCodProcesso_.getText();

		
		inscri = consultaInscricao(inscri);
		
		System.out.println(inscri);

		if (inscri.codDisciplina != null && inscri.codProcesso_ != null) {

			TaInsc.setText("Codigo de processo: " + inscri.codProcesso_ + " - CPF: " + inscri.cpf
					+ " - Codigo da disciplina " + inscri.codDisciplina);

		} else {

			TaInsc.setText("Inscrição não encontrada.");
		}
		
	}
	
	private Inscricao consultaInscricao(Inscricao inscri) throws IOException {

		System.out.println(inscri + " consulta inscricao");
		Fila<Inscricao> fila = carregarFilaInscricao();

		try {

			while (!fila.isEmpty()) {

				Inscricao inscricao = fila.dequeue();

				if(inscri.codProcesso_.equals(inscricao.codProcesso_)) {
					System.out.println("achou");
					return inscricao;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("nao achou");
		return inscri;
	}
	
	private Fila<Inscricao> carregarFilaInscricao() throws IOException {

		Fila<Inscricao> fila = new Fila<>();

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "inscricoes.csv");

		if (arq.exists() && arq.isFile()) {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

			String linha = buffer.readLine();

			while (linha != null) {

				String[] dados = linha.split(";");

				if (dados.length >= 3) {

					Inscricao inscricao = new Inscricao();

					inscricao.cpf = dados[0];
					inscricao.codProcesso_ = dados[1];
					inscricao.codDisciplina = dados[2];
					
					fila.enqueue(inscricao);
				}

				linha = buffer.readLine();
			}

			buffer.close();
		}

		return fila;
	}
	
	
	private ListaEncadeada<Inscricao> carregarListaInscricao() throws IOException {

		ListaEncadeada<Inscricao> lista = new ListaEncadeada<>();

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "inscricoes.csv");

		if (arq.exists()) {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

			String linha ;

			 while ((linha = buffer.readLine()) != null) {

	                if (linha.trim().isEmpty()) continue;

	                String[] dados = linha.split(";");

	                if (dados.length < 3) continue;
			 

				Inscricao inscricao = new Inscricao();

				inscricao.cpf = dados[0];
				inscricao.codProcesso_ = dados[1];
				inscricao.codDisciplina = dados[2];

				lista.addLast(inscricao);

				linha = buffer.readLine();
			}

			buffer.close();
		}

		return lista;
	}
	
	private void remove() throws IOException {

		String codigo = TFCodProcesso_.getText();

		ListaEncadeada<Inscricao> lista = carregarListaInscricao();

		No<Inscricao> aux = lista.getPrimeiro();

		while (aux != null) {

			Inscricao inscri = aux.getDado();

			if (inscri.codProcesso_.equals(codigo)) {

				lista.remove(inscri);

				salvarListaInscricao(lista);

				TaInsc.setText("Inscricao removida com sucesso!");

				return;
			}

			aux = aux.getProximo();
		}

		TaInsc.setText("Inscricao não encontrada.");
	}
	
	private void salvarListaInscricao(ListaEncadeada<Inscricao> lista) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "inscricoes.csv");

		FileWriter fw = new FileWriter(arq, false);
		PrintWriter pw = new PrintWriter(fw);

		No<Inscricao> aux = lista.getPrimeiro();

		while (aux != null) {

			Inscricao inscri = aux.getDado();

			pw.println(

					inscri.cpf + ";" + inscri.codProcesso_ + ";" + inscri.codDisciplina);

			aux = aux.getProximo();
		}

		pw.close();
		fw.close();
	}
	
	private void atualiza() throws IOException {

		String cpf = TFCPFInsc.getText();
		String codProcesso = TFCodProcesso_.getText();
		String codDisciplina = TFDaDisciplina.getText();


		if (cpf.isEmpty() || codProcesso.isEmpty() || codDisciplina.isEmpty()) {

			TaInsc.setText("Todos os campos devem ser preenchidos.");
			return;
		}
		
		if (!profController.cpfJaExiste(cpf)) {

		    TaInsc.setText(
		        "CPF não cadastrado no sistema."
		    );

		    return;
		}

		if (!disciplinaController.codigoJaExiste(codDisciplina)) {

		    TaInsc.setText(
		        "Código de disciplina não cadastrado."
		    );

		    return;
		}

		ListaEncadeada<Inscricao> lista = carregarListaInscricao();

		No<Inscricao> aux = lista.getPrimeiro();

		while (aux != null) {

			Inscricao inscricao = aux.getDado();
			
			if(inscricao.codProcesso_.equals(codProcesso)) {
				
				inscricao.cpf = cpf;
				inscricao.codProcesso_ = codProcesso;
				inscricao.codDisciplina = codDisciplina;

				salvarListaInscricao(lista);
				
				TaInsc.setText("Inscrição atualizada com sucesso!");

				return;
			}

			aux = aux.getProximo();
		}

		TaInsc.setText("Código do Processo não encontrado!");
		
	}
	
	private void limpar() {
		TFCodProcesso_.setText("");
		TFDaDisciplina.setText("");
		TFCPFInsc.setText("");
    }
	
}
