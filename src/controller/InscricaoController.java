package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Inscricao;
import model.Professor;
import model.Disciplina;




public class InscricaoController implements ActionListener {
    
	private JTextField TFCodProcesso_;
	private JTextField TFCPFInsc;
	private JTextField TFDaDisciplina;
		
	public InscricaoController(JTextField TFCodProcesso_, JTextField TFCPFInsc, JTextField TFDaDisciplina) {
		super();
		this.TFCodProcesso_ = TFCodProcesso_;
		this.TFCPFInsc = TFCPFInsc;
		this.TFDaDisciplina = TFDaDisciplina;
		
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
		
	}
	
	
	private void insere() throws IOException {
		
		Inscricao inscricao = new Inscricao();
		
		inscricao.codProcesso_ = TFCodProcesso_.getText() ;
		inscricao.cpf = TFCPFInsc.getText();
		inscricao.codDisciplina = TFDaDisciplina.getText();
	
		if (!cpfJaExiste(inscricao.cpf)) {
			JOptionPane.showMessageDialog(null, "CPF não pode ser inscrito pois não há cadastro Professor.csv");
			return;
		} else if(!disciplinaJaExiste(inscricao.codDisciplina)) {
			JOptionPane.showMessageDialog(null, "CPF não pode ser inscrito pois não há cadastro em Disciplina.csv");
			return;
		}
		
	    insereInscricao(inscricao.toString());
		
//		System.out.println(disciplina);
		insereInscricao(inscricao.toString());
		TFCodProcesso_.setText("");
		TFCPFInsc.setText("");
		TFDaDisciplina.setText("");
		}
		
	
	private boolean cpfJaExiste(String cpf) throws IOException {
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	    File professor = new File(path, "Professores.csv");

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

	}

	
	
	
}
