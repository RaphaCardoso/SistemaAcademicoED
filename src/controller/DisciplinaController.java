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
import model.Disciplina;

public class DisciplinaController implements ActionListener {

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
		if (cmd.equals("Inserir")) {
			try {
				insere();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Consultar")) {
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

	private ListaEncadeada<Disciplina> carregarListaDisciplinas() throws IOException {

		ListaEncadeada<Disciplina> lista = new ListaEncadeada<>();

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "disciplina.csv");

		if (arq.exists()) {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

			String linha = buffer.readLine();

			while (linha != null) {

				String[] dados = linha.split(";");

				Disciplina d = new Disciplina();

				d.codigo = dados[0];
				d.nome = dados[1];
				d.diasemana = dados[2];
				d.horarioinicial = dados[3];
				d.qtdhorasdiarias = dados[4];
				d.codigocurso = dados[5];

				lista.addLast(d);

				linha = buffer.readLine();
			}

			buffer.close();
		}

		return lista;
	}

	private void remove() throws IOException {

		String codigo = tfDisciplinaCodigo.getText();

		ListaEncadeada<Disciplina> lista = carregarListaDisciplinas();

		No<Disciplina> aux = lista.getPrimeiro();

		while (aux != null) {

			Disciplina d = aux.getDado();

			if (d.codigo.equals(codigo)) {

				lista.remove(d);

				salvarListaDisciplinas(lista);

				taDisciplinaLista.setText("Disciplina removida com sucesso!");

				return;
			}

			aux = aux.getProximo();
		}

		taDisciplinaLista.setText("Disciplina não encontrada.");
	}

	private void atualiza() throws IOException {

		String codigo = tfDisciplinaCodigo.getText();
		String nome = tfDisciplinaNome.getText();
		String horainicio = tfDisciplinaHora.getText();
		String qtdhoras = tfDisciplinaQtdHoras.getText();
		String codcurso = tfDisciplinaCodCurso.getText();
		String semana = tfDisciplinaSemana.getText();

		if (codigo.isEmpty() || nome.isEmpty() || horainicio.isEmpty() || qtdhoras.isEmpty() || codcurso.isEmpty()
				|| semana.isEmpty()) {

			taDisciplinaLista.setText("Todos os campos devem ser preenchidos.");
			return;
		}

		ListaEncadeada<Disciplina> lista = carregarListaDisciplinas();

		No<Disciplina> aux = lista.getPrimeiro();

		while (aux != null) {

			Disciplina d = aux.getDado();

			if (d.codigo.equals(codigo)) {

				d.codigo = codigo;
				d.nome = nome;
				d.diasemana = semana;
				d.horarioinicial = horainicio;
				d.qtdhorasdiarias = qtdhoras;
				d.codigocurso = codcurso;

				salvarListaDisciplinas(lista);

				taDisciplinaLista.setText("Disciplina atualizada com sucesso!");

				return;
			}

			aux = aux.getProximo();
		}

		taDisciplinaLista.setText("Disciplina não encontrada.");
	}

	private void salvarListaDisciplinas(ListaEncadeada<Disciplina> lista) throws IOException {

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "disciplina.csv");

		FileWriter fw = new FileWriter(arq, false);
		PrintWriter pw = new PrintWriter(fw);

		No<Disciplina> aux = lista.getPrimeiro();

		while (aux != null) {

			Disciplina d = aux.getDado();

			pw.println(

					d.codigo + ";" + d.nome + ";" + d.diasemana + ";" + d.horarioinicial + ";" + d.qtdhorasdiarias + ";"
							+ d.codigocurso);

			aux = aux.getProximo();
		}

		pw.close();
		fw.close();
	}

	private void insereDisciplina(String csvDisciplina) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "disciplina.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvDisciplina + "\r\n");
		pw.flush();
		pw.close();
		fw.close();

	}

	private void consulta() throws IOException {

		Disciplina disciplina = new Disciplina();

		disciplina.codigo = tfDisciplinaCodigo.getText();

		disciplina = consultaDisciplina(disciplina);

		if (disciplina.codigo!= null) {

			taDisciplinaLista.setText("Código: " + disciplina.codigo + " - Nome: " + disciplina.nome
					+ " - Dia da semana: " + disciplina.diasemana + " - Horário de Início: " + disciplina.horarioinicial
					+ " - Quantidade de horas diárias: " + disciplina.qtdhorasdiarias + " - Código do Curso: "
					+ disciplina.codigocurso);

		} else {

			taDisciplinaLista.setText("Disciplina não encontrada.");
		}
	}

	private Disciplina consultaDisciplina(Disciplina disciplina) throws IOException {

		Fila<Disciplina> fila = carregarFilaDisciplinas();

		try {

			while (!fila.isEmpty()) {

				Disciplina d = fila.dequeue();

				if (d.codigo.equals(disciplina.codigo)) {
					return d;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return disciplina;
	}

	private Fila<Disciplina> carregarFilaDisciplinas() throws IOException {

		Fila<Disciplina> fila = new Fila<>();

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

		File arq = new File(path, "disciplina.csv");

		if (arq.exists() && arq.isFile()) {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

			String linha = buffer.readLine();

			while (linha != null) {

				String[] dados = linha.split(";");

				if (dados.length >= 6) {

					Disciplina d = new Disciplina();

					d.codigo = dados[0];
					d.nome = dados[1];
					d.diasemana = dados[2];
					d.horarioinicial = dados[3];
					d.qtdhorasdiarias = dados[4];
					d.codigocurso = dados[5];

					fila.enqueue(d);
				}

				linha = buffer.readLine();
			}

			buffer.close();
		}

		return fila;
	}

	private void insere() throws IOException {

		String codigo = tfDisciplinaCodigo.getText();
		String nome = tfDisciplinaNome.getText();
		String horainicio = tfDisciplinaHora.getText();
		String qtdhoras = tfDisciplinaQtdHoras.getText();
		String codcurso = tfDisciplinaCodCurso.getText();
		String semana = tfDisciplinaSemana.getText();

		if (codigo.isEmpty() || nome.isEmpty() || horainicio.isEmpty() || qtdhoras.isEmpty() || codcurso.isEmpty()
				|| semana.isEmpty()) {

			taDisciplinaLista.setText("Todos os campos devem ser preenchidos.");
			return;
		}

		if (codigoJaExiste(codigo)) {

			taDisciplinaLista.setText("Código de disciplina já existente.");
			return;
		}

		Disciplina disciplina = new Disciplina();

		disciplina.codigo = codigo;
		disciplina.nome = nome;
		disciplina.diasemana = semana;
		disciplina.horarioinicial = horainicio;
		disciplina.qtdhorasdiarias = qtdhoras;
		disciplina.codigocurso = codcurso;

		insereDisciplina(disciplina.toString());

		taDisciplinaLista.setText("Disciplina cadastrada com sucesso!");

		tfDisciplinaCodigo.setText("");
		tfDisciplinaNome.setText("");
		tfDisciplinaSemana.setText("");
		tfDisciplinaHora.setText("");
		tfDisciplinaQtdHoras.setText("");
		tfDisciplinaCodCurso.setText("");
	}
	
	private boolean codigoJaExiste(String codigo) throws IOException {

	    ListaEncadeada<Disciplina> lista = carregarListaDisciplinas();

	   No<Disciplina> aux = lista.getPrimeiro();

	    while(aux != null) {

	        Disciplina p = aux.getDado();

	        if(p.codigo.equals(codigo)) {
	            return true;
	        }

	        aux = aux.getProximo();
	    }

	    return false;
	}

}

