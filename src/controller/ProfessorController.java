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

import br.edu.estruturaDados.fila.Fila;
import br.edu.estruturaDados.listaEncadeada.ListaEncadeada;
import br.edu.estruturaDados.listaEncadeada.No;
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

	    if (cmd.equals("Editar")) {
	        try {
	            editarProfessor();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }

	    if (cmd.equals("Remover")) {
	        try {
	            removerProfessor();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	}
	
	
	private ListaEncadeada<Professor>
	carregarListaProfessores() throws IOException {

	    ListaEncadeada<Professor> lista =
	            new ListaEncadeada<>();

	    String path =
	        System.getProperty("user.home")
	        + File.separator
	        + "SistemaCadastro";

	    File arq = new File(path, "professor.csv");

	    if(arq.exists()) {

	        BufferedReader buffer =
	            new BufferedReader(
	                new InputStreamReader(
	                    new FileInputStream(arq)));

	        String linha = buffer.readLine();

	        while(linha != null) {

	            String[] dados = linha.split(";");

	            Professor p = new Professor();

	            p.cpf = dados[0];
	            p.nome = dados[1];
	            p.areaInscricao = dados[2];
	            p.pontos = dados[3];

	            lista.addLast(p);

	            linha = buffer.readLine();
	        }

	        buffer.close();
	    }

	    return lista;
	}
	
	
	private void removerProfessor() throws IOException {

	    String cpf = tfProfessorCpf.getText();

	    ListaEncadeada<Professor> lista = carregarListaProfessores();

	    No<Professor> aux = lista.getPrimeiro();

	    while(aux != null) {

	        Professor p = aux.getDado();

	        if(p.cpf.equals(cpf)) {

	            lista.remove(p);

	            salvarListaProfessores(lista);

	            taProfessorLista.setText(
	                "Professor removido com sucesso!"
	            );

	            return;
	        }

	        aux = aux.getProximo();
	    }

	    taProfessorLista.setText(
	        "Professor não encontrado."
	    );
	}
		
	

	private void editarProfessor() throws IOException {

	    String cpf = tfProfessorCpf.getText();
	    String nome = tfProfessorNome.getText();
	    String area = tfProfessorAreaInscricao.getText();
	    String pontos = tfProfessorPontos.getText();

	    
	    if(cpf.isEmpty() ||
	       nome.isEmpty() ||
	       area.isEmpty() ||
	       pontos.isEmpty()) {

	        taProfessorLista.setText(
	            "Todos os campos devem ser preenchidos."
	        );
	        return;
	    }

	    ListaEncadeada<Professor> lista = carregarListaProfessores();

	    No<Professor> aux = lista.getPrimeiro();

	    while(aux != null) {

	        Professor p = aux.getDado();

	        if(p.cpf.equals(cpf)) {

	            p.nome = nome;
	            p.areaInscricao = area;
	            p.pontos = pontos;

	            salvarListaProfessores(lista);

	            taProfessorLista.setText(
	                "Professor atualizado com sucesso!"
	            );

	            return;
	        }

	        aux = aux.getProximo();
	    }

	    taProfessorLista.setText(
	        "Professor não encontrado."
	    );
	}
	
	private void salvarListaProfessores(
	        ListaEncadeada<Professor> lista)
	        throws IOException {

	    String path =
	        System.getProperty("user.home")
	        + File.separator
	        + "SistemaCadastro";

	    File arq = new File(path, "professor.csv");

	    FileWriter fw = new FileWriter(arq, false);
	    PrintWriter pw = new PrintWriter(fw);

	    No<Professor> aux = lista.getPrimeiro();

	    while(aux != null) {

	        Professor p = aux.getDado();

	        pw.println(
	            p.cpf + ";" +
	            p.nome + ";" +
	            p.areaInscricao + ";" +
	            p.pontos
	        );

	        aux = aux.getProximo();
	    }

	    pw.close();
	    fw.close();
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

	    professor.cpf = tfProfessorCpf.getText();

	    professor = buscaProfessor(professor);

	    if(professor.nome != null) {

	        taProfessorLista.setText(
	            "CPF: " + professor.cpf +
	            " - Nome: " + professor.nome +
	            " - Área de Inscrição: " + professor.areaInscricao +
	            " - Pontos: " + professor.pontos
	        );

	    } else {

	        taProfessorLista.setText(
	            "Professor não encontrado"
	        );
	    }
	}
	
	//private Professor buscaProfessor(Professor professor) throws IOException {
	//	String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	//	File arq = new File(path, "professor.csv");
	//	if(arq.exists() && arq.isFile()) {
	//		FileInputStream fis = new FileInputStream(arq);
	//		InputStreamReader isr = new InputStreamReader(fis);
	//		BufferedReader buffer = new BufferedReader(isr);
	//		String linha = buffer.readLine();
	//		while (linha != null) {
	//			
	//			String[] vetLinha = linha.split(";");
    //
	//			if(vetLinha.length < 4) {
	//			    linha = buffer.readLine();
	//			    continue;
	//			}
	//			if (vetLinha[0].equals(professor.cpf)) {
	//				professor.nome = vetLinha[1];
	//				professor.areaInscricao = vetLinha[2];
	//			    professor.pontos = vetLinha[3];
	//			    break;
	//			}
	//			
	//			linha = buffer.readLine();
	//		}
	//		buffer.close();
	//		isr.close();
	//		fis.close();
	//		
	//	}
	//	return professor;
	//}
	
	private Professor buscaProfessor(Professor professor) throws IOException {

	    Fila<Professor> fila = carregarFilaProfessores();

	    try {

	        while(!fila.isEmpty()) {

	            Professor p = fila.dequeue();

	            if(p.cpf.equals(professor.cpf)) {
	                return p;
	            }
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return professor;
	}
	
	private Fila<Professor> carregarFilaProfessores() throws IOException {

	    Fila<Professor> fila = new Fila<>();

	    String path = System.getProperty("user.home")
	            + File.separator + "SistemaCadastro";

	    File arq = new File(path, "professor.csv");

	    if(arq.exists() && arq.isFile()) {

	        BufferedReader buffer =
	                new BufferedReader(
	                        new InputStreamReader(
	                                new FileInputStream(arq)));

	        String linha = buffer.readLine();

	        while(linha != null) {

	            String[] dados = linha.split(";");

	            if(dados.length >= 4) {

	                Professor p = new Professor();

	                p.cpf = dados[0];
	                p.nome = dados[1];
	                p.areaInscricao = dados[2];
	                p.pontos = dados[3];

	                fila.enqueue(p);
	            }

	            linha = buffer.readLine();
	        }

	        buffer.close();
	    }

	    return fila;
	}

	private void cadastro() throws IOException {

	    String cpf = tfProfessorCpf.getText();
	    String nome = tfProfessorNome.getText();
	    String area = tfProfessorAreaInscricao.getText();
	    String pontos = tfProfessorPontos.getText();

	   
	    if(cpf.isEmpty() ||
	       nome.isEmpty() ||
	       area.isEmpty() ||
	       pontos.isEmpty()) {

	        taProfessorLista.setText(
	            "Todos os campos devem ser preenchidos."
	        );
	        return;
	    }

	   
	    if(cpfJaExiste(cpf)) {

	        taProfessorLista.setText(
	            "CPF já existente."
	        );
	        return;
	    }

	    Professor professor = new Professor();

	    professor.cpf = cpf;
	    professor.nome = nome;
	    professor.areaInscricao = area;
	    professor.pontos = pontos;

	    cadastraProfessor(professor.toString());

	    taProfessorLista.setText(
	        "Professor cadastrado com sucesso."
	    );

	    tfProfessorCpf.setText("");
	    tfProfessorNome.setText("");
	    tfProfessorAreaInscricao.setText("");
	    tfProfessorPontos.setText("");
	}
	
	private boolean cpfJaExiste(String cpf) throws IOException {

	    ListaEncadeada<Professor> lista = carregarListaProfessores();

	    No<Professor> aux = lista.getPrimeiro();

	    while(aux != null) {

	        Professor p = aux.getDado();

	        if(p.cpf.equals(cpf)) {
	            return true;
	        }

	        aux = aux.getProximo();
	    }

	    return false;
	}
	

                       
	
	

}
