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




public class InscricaoController implements ActionListener {
    
	private JTextField TFCodProcesso_;
	private JTextField TFCPFInsc;
	private JTextField TFDaDisciplina;
	private JTextArea TaInsc;
	
	public InscricaoController(JTextField TFCodProcesso_, JTextField TFCPFInsc, JTextField TFDaDisciplina, JTextArea TaInsc) {
		super();
		this.TFCodProcesso_ = TFCodProcesso_;
		this.TFCPFInsc = TFCPFInsc;
		this.TFDaDisciplina = TFDaDisciplina;
		this.TaInsc = TaInsc;
		
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
		if(cmd.equals("Consultar")) {
			try {
				consulta();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Atualizar")) {
			try {
				atualiza();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Remover")) {
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
	    
		if (!cpfJaExiste(professor.cpf)) {
			JOptionPane.showMessageDialog(null, "CPF não pode ser inscrito pois não há cadastro Professor.csv");
			return;
		} else if(!disciplinaJaExiste(disciplina.codigo)) {
			JOptionPane.showMessageDialog(null, "CPF não pode ser inscrito pois não há cadastro em Disciplina.csv");
			return;
		}
	   
	    if(cpf.isEmpty() ||
	       codProcesso.isEmpty() ||
	       codDisciplina.isEmpty()) {
	    	
	        TaInsc.setText(
	            "Todos os campos devem ser preenchidos."
	        );
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
	
	
	
	private boolean cpfJaExiste(String cpf) throws IOException {
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	    File professor = new File(path, "Professor.csv");

	    if (!professor.exists()) {
	        return true;
	    }

	    BufferedReader br = new BufferedReader(new FileReader(professor));

	    String linha;

	    while ((linha = br.readLine()) != null) {
	        String[] dados = linha.split(";");
	        if (dados[0].equals(cpf)) {
	            br.close();
	            return false;
	        }
	    }
	    

	    br.close();
	    return true;
	}
	
	private boolean disciplinaJaExiste( String codigo) throws IOException {
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	    File disciplina = new File(path, "Disciplinas.csv");

	    if (!disciplina.exists()) {
	        return true;
	    }

	    BufferedReader bt = new BufferedReader(new FileReader(disciplina));

	    String linha;


	    while ((linha = bt.readLine()) != null) {
	        String[] dados = linha.split(";");
	        if (dados[0].equals(codigo)) {
	            bt.close();
	            return false;
	        }
	    }

	    bt.close();
	    return true;
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

		if (inscri.codDisciplina!= null) {

			TaInsc.setText("CPF: " + inscri.cpf + " - Codigo de processo: " + inscri.codProcesso_
					+ " - Codigo da disciplina " + inscri.codDisciplina);

		} else {

			TaInsc.setText("Disciplina não encontrada.");
		}
		
	}
	
	private Inscricao consultaInscricao(Inscricao inscri) throws IOException {

		Fila<Inscricao> fila = carregarFilaInscricao();

		try {

			while (!fila.isEmpty()) {

				Inscricao inscricao = fila.dequeue();

				if(inscricao.codProcesso_.equals(inscricao.codProcesso_)) {
					return inscricao;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return inscri;
	}
	
	private Fila<Inscricao> carregarFilaInscricao() throws IOException {

		Fila<Inscricao> fila = new Fila<>();

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "inscicoes.csv");

		if (arq.exists() && arq.isFile()) {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

			String linha = buffer.readLine();

			while (linha != null) {

				String[] dados = linha.split(";");

				if (dados.length >= 6) {

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

		File arq = new File(path, "iscricoes.csv");

		if (arq.exists()) {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

			String linha = buffer.readLine();

			while (linha != null) {

				String[] dados = linha.split(";");

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

		ListaEncadeada<Inscricao> lista = carregarListaInscricao();

		No<Inscricao> aux = lista.getPrimeiro();

		while (aux != null) {

			Inscricao inscricao = aux.getDado();
			
			if(inscricao.codProcesso_.equals(codProcesso)) {
				
				inscricao.cpf = cpf;
				inscricao.codProcesso_ = codProcesso;
				inscricao.codDisciplina = codDisciplina;

				salvarListaInscricao(lista);
				
				TaInsc.setText("I atualizada com sucesso!");

				return;
			}

			aux = aux.getProximo();
		}

		TaInsc.setText("Disciplina não encontrada.");
		
	}
	
}
