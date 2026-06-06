package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Professor;

public class ProfessorController implements ActionListener {
	
	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorAreaInscricao;
	private JTextField tfProfessorPontos;
	private JTextArea taProfessorLista;
	
	public ProfessorController(JTextField TFProfessorCpf, JTextField TFProfessorNome,
			JTextField TFProfessorAreaInscricao, JTextField TFProfessorPontos, JTextArea taProfessorLista) {
		super();
		this.tfProfessorCpf = TFProfessorCpf;
		this.tfProfessorNome = TFProfessorNome;
		this.tfProfessorAreaInscricao = TFProfessorAreaInscricao;
		this.tfProfessorPontos = TFProfessorPontos;
		this.taProfessorLista = taProfessorLista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar")) {
			try {
				cadastro();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Buscar")) {
			try {
				busca();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
	}
	}
	
	private void cadastraProfessor(String csvProfessor) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "professor.csv");
		boolean existe = false;
		if (arq.exists()){
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvProfessor+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
		
	} 

	private void busca() throws IOException {
		Professor professor = new Professor();
		professor.cpf = tfProfessorCpf .getText();
		
		
	//	System.out.println(professor);
		
		professor = buscaProfessor(professor);
		if (professor.nome != null){
			taProfessorLista.setText("CPF: "+professor.cpf+" - Nome: "+professor.nome+" - Area de Inscrição: "+professor.areaInscricao+" - Pontos: "+professor.pontos);
		} else {
			taProfessorLista.setText("Cliente não encontrado");
		}
	}
	
	private Professor buscaProfessor(Professor professor) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "professor.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(professor.cpf)) {
					professor.nome = vetLinha[1];
					professor.areaInscricao = vetLinha[2];
				    professor.pontos = vetLinha[3];
				    break;
				}
				
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
			
		}
		return professor;
	}

	private void cadastro() throws IOException {
		Professor professor = new Professor();
		professor.cpf = tfProfessorCpf.getText();
		professor.nome= tfProfessorNome.getText();
		professor.areaInscricao = tfProfessorAreaInscricao.getText();
		professor.pontos = tfProfessorPontos.getText();
		
		System.out.println(professor);
		
		cadastraProfessor(professor.toString());
		tfProfessorCpf.setText("");
		tfProfessorNome.setText("");
		tfProfessorAreaInscricao.setText("");
		tfProfessorPontos.setText("");
		
	}

                       
	
	

}
