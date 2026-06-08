package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.estruturaDados.algoritmos.QuickSort;
import br.edu.estruturaDados.listaEncadeada.ListaEncadeada;
import br.edu.estruturaDados.listaEncadeada.No;
import model.Professor;

public class ConsultaController implements ActionListener {
	
	private JTextArea taConsultarInscritos;
	
	public ConsultaController(JTextArea taConsultarInscritos) {
		super();
		this.taConsultarInscritos = taConsultarInscritos;
	}

	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Consultar")) {
			try {
				consulta();
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}
	}
	
	private ListaEncadeada<Professor> consultaDisciplina() throws IOException {

	    ListaEncadeada<Professor> lista = new ListaEncadeada<>();

	    String path = System.getProperty("user.home")
	            + File.separator + "SistemaCadastro";

	    File arqInscricao = new File(path, "inscricoes.csv");
	    File arqProfessor = new File(path, "professor.csv");

	    if (arqInscricao.exists() && arqProfessor.exists()) {

	        BufferedReader bufferInscricao = new BufferedReader(
	                new InputStreamReader(
	                        new FileInputStream(arqInscricao)));

	        String linhaInscricao = bufferInscricao.readLine();

	        while (linhaInscricao != null) {

	            if (linhaInscricao.trim().isEmpty()) {
	                linhaInscricao = bufferInscricao.readLine();
	                continue;
	            }

	            String[] dadosInscricao = linhaInscricao.split(";");

	            if (dadosInscricao.length < 3) {
	                linhaInscricao = bufferInscricao.readLine();
	                continue;
	            }

	            String cpfInscrito = dadosInscricao[0];

	            BufferedReader bufferProfessor = new BufferedReader(
	                    new InputStreamReader(
	                            new FileInputStream(arqProfessor)));

	            String linhaProfessor = bufferProfessor.readLine();

	            while (linhaProfessor != null) {

	                String[] dadosProfessor = linhaProfessor.split(";");

	                if (dadosProfessor.length >= 4 &&
	                    dadosProfessor[0].equals(cpfInscrito)) {

	                    Professor p = new Professor();

	                    p.cpf = dadosProfessor[0];
	                    p.nome = dadosProfessor[1];
	                    p.areaInscricao = dadosProfessor[2];
	                    p.pontos = dadosProfessor[3];

	                    lista.addLast(p);

	                    break;
	                }

	                linhaProfessor = bufferProfessor.readLine();
	            }

	            bufferProfessor.close();

	            linhaInscricao = bufferInscricao.readLine();
	        }

	        bufferInscricao.close();
	    }

	    return lista;
	}
	
	private void consulta() throws IOException {

	    ListaEncadeada<Professor> lista = consultaDisciplina();

	    int tamanho = lista.size(); 

	    int[] pontos = new int[tamanho];

	    No<Professor> aux = lista.getPrimeiro();

	    int i = 0;

	    while(aux != null) {

	        pontos[i] =
	            Integer.parseInt(
	                aux.getDado().pontos
	            );

	        aux = aux.getProximo();
	        i++;
	    }

	    QuickSort quick = new QuickSort();

	    quick.sort(pontos, 0, tamanho - 1);

	    String texto = "";

	    for(int j = tamanho - 1; j >= 0; j--) {

	        aux = lista.getPrimeiro();

	        while(aux != null) {

	            Professor professor = aux.getDado();

	            if(Integer.parseInt(professor.pontos)
	                    == pontos[j]) {

	                texto +=
	                    "CPF: " + professor.cpf
	                    + " - Nome: " + professor.nome
	                    + " - Área de Inscrição: " + professor.areaInscricao
	                    + " - Pontos: " + professor.pontos
	                    + "\n";

	                professor.pontos = "-1";

	                break;
	            }

	            aux = aux.getProximo();
	        }
	    }

	    if(!texto.isEmpty()) {

	        taConsultarInscritos.setText(texto);

	    } else {

	        taConsultarInscritos.setText(
	            "Nenhum inscrito encontrado."
	        );
	    }
	}
	
}
