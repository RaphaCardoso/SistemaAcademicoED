package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ProfessorController;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TFCodCurso;
	private JTextField TFNomeCurso;
	private JTextField TFAreaCon;
	private JTextField TFbuscarProfessor;
	private JTextField TFbuscarInscrição;
	private JTextField TFbuscarDisciplina;
	private JTextField TFProfessorCpf;
	private JTextField TFProfessorNome;
	private JTextField TFProfessorAreaInscricao;
	private JTextField TFProfessorPontos;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Tela() {
		setTitle("SistemaAcademicoED");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 606, 423);
		contentPane.add(tabbedPane);
		
		//CURSO


		JPanel TelaCurso = new JPanel();
		tabbedPane.addTab("Curso", null, TelaCurso, "Cadastro de Cursos");
		TelaCurso.setLayout(null);
		
		JLabel lblCursoCodigo = new JLabel("Código do Curso");
		lblCursoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoCodigo.setBounds(23, 32, 117, 25);
		TelaCurso.add(lblCursoCodigo);
		
		JLabel lblCursoNome = new JLabel("Nome do Curso");
		lblCursoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoNome.setBounds(23, 67, 117, 25);
		TelaCurso.add(lblCursoNome);
		
		JLabel lblCursoArea = new JLabel("Área do Conhecimento");
		lblCursoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCursoArea.setBounds(23, 102, 149, 25);
		TelaCurso.add(lblCursoArea);
		
		TFCodCurso = new JTextField();
		TFCodCurso.setBounds(182, 37, 154, 18);
		TelaCurso.add(TFCodCurso);
		TFCodCurso.setColumns(10);
		
		TFNomeCurso = new JTextField();
		TFNomeCurso.setBounds(182, 72, 154, 18);
		TelaCurso.add(TFNomeCurso);
		TFNomeCurso.setColumns(10);
		
		TFAreaCon = new JTextField();
		TFAreaCon.setBounds(182, 107, 154, 18);
		TelaCurso.add(TFAreaCon);
		TFAreaCon.setColumns(10);
		
		JButton btnCadastrarCurso = new JButton("Cadastrar Curso");
		btnCadastrarCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCadastrarCurso.setBounds(411, 37, 166, 20);
		TelaCurso.add(btnCadastrarCurso);
		
		JButton btnBuscarCurso = new JButton("Buscar Curso");
		btnBuscarCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarCurso.setBounds(411, 69, 166, 20);
		TelaCurso.add(btnBuscarCurso);
		
		JTextArea TAListaCurso = new JTextArea();
		TAListaCurso.setFont(new Font("Monospaced", Font.PLAIN, 13));
		TAListaCurso.setBounds(23, 137, 568, 249);
		TelaCurso.add(TAListaCurso);

		//Tela Professor
		
		JPanel TelaProfessor = new JPanel();
		tabbedPane.addTab("Professor", null, TelaProfessor, null);
		TelaProfessor.setLayout(null);
		
		TFbuscarProfessor = new JTextField();
		TFbuscarProfessor.setBounds(182, 72, 154, 18);
		
		TFbuscarProfessor.setColumns(10);	
		
		JLabel lblCpfProfessor = new JLabel("CPF do Professor");
		lblCpfProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpfProfessor.setBounds(29, 37, 105, 25);
		TelaProfessor.add(lblCpfProfessor);
		
		JLabel lblNomeProfessor = new JLabel("Nome do Professor");
		lblNomeProfessor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeProfessor.setBounds(17, 73, 117, 25);
		TelaProfessor.add(lblNomeProfessor);
		
		JLabel lblAreaInscricao = new JLabel("Área de Inscrição");
		lblAreaInscricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAreaInscricao.setBounds(30, 109, 104, 25);
		TelaProfessor.add(lblAreaInscricao);
		
		JLabel lblPontos = new JLabel("Pontos");
		lblPontos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPontos.setBounds(91, 145, 43, 25);
		TelaProfessor.add(lblPontos);

		JButton btnBuscarProfessor = new JButton("Buscar");
		btnBuscarProfessor.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarProfessor.setBounds(420, 73, 171, 25);
		TelaProfessor.add(btnBuscarProfessor);

		//Cadastrar Professor

		JButton btnCadastrarProfessor = new JButton("Cadastrar");
		btnCadastrarProfessor.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCadastrarProfessor.setBounds(420, 37, 171, 25);
		TelaProfessor.add(btnCadastrarProfessor);
		
		TFProfessorCpf = new JTextField();
		TFProfessorCpf.setColumns(10);
		TFProfessorCpf.setBounds(144, 42, 154, 18);
		TelaProfessor.add(TFProfessorCpf);
		
		TFProfessorNome = new JTextField();
		TFProfessorNome.setColumns(10);
		TFProfessorNome.setBounds(144, 80, 154, 18);
		TelaProfessor.add(TFProfessorNome);
		
		TFProfessorAreaInscricao = new JTextField();
		TFProfessorAreaInscricao.setColumns(10);
		TFProfessorAreaInscricao.setBounds(144, 117, 154, 18);
		TelaProfessor.add(TFProfessorAreaInscricao);
		
		TFProfessorPontos = new JTextField();
		TFProfessorPontos.setColumns(10);
		TFProfessorPontos.setBounds(144, 149, 154, 18);
		TelaProfessor.add(TFProfessorPontos);
		
		JTextArea taProfessorLista = new JTextArea();
		taProfessorLista.setFont(new Font("Monospaced", Font.PLAIN, 13));
		taProfessorLista.setBounds(17, 181, 574, 203);
		TelaProfessor.add(taProfessorLista);

		//Consulta de Inscritos da disciplina 

		JPanel TelaDisciplinas = new JPanel();
		tabbedPane.addTab("Disciplinas", null, TelaDisciplinas, null);
		
		//Campos Disciplinas

		JLabel lblBuscarDisciplinas = new JLabel("Buscar disciplinas");
		lblBuscarDisciplinas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscarDisciplinas.setBounds(23, 32, 117, 25);
		TelaDisciplinas.add(lblBuscarDisciplinas);

		//Cod disciplinas 

		JLabel lblCodDisciplinas = new JLabel("Codigo da disciplina");
		lblCodDisciplinas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodDisciplinas.setBounds(23, 32, 117, 25);
		TelaDisciplinas.add(lblCodDisciplinas);
			
		TFbuscarDisciplina = new JTextField();
		TFbuscarDisciplina.setBounds(182, 37, 154, 18);
		TelaDisciplinas.add(TFbuscarProfessor);
		TFbuscarDisciplina.setColumns(10);			

		JButton btnBuscarDisciplina = new JButton("Buscar disciplinas");
		btnBuscarDisciplina.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarDisciplina.setBounds(411, 37, 166, 20);
		TelaDisciplinas.add(btnBuscarDisciplina);

		//Cadastrar Disciplinas
		JButton btncadastrarDisciplinas = new JButton("Cadastrar Disciplinas");
		btncadastrarDisciplinas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncadastrarDisciplinas.setBounds(411, 37, 166, 20);
		TelaDisciplinas.add(btncadastrarDisciplinas);
		

		// Tela Buscar Incrição
		JPanel TelaInscrição = new JPanel();
		tabbedPane.addTab("Inscrição", null, TelaInscrição, null);

		//Buscar Inscrição

		JLabel lblBuscarInscricao = new JLabel("Buscar inscrição");
		lblBuscarInscricao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscarInscricao.setBounds(23, 32, 117, 25);
		TelaInscrição.add(lblBuscarInscricao);

		JLabel lblNumDaInscrição = new JLabel("Numero da inscrição");
		lblNumDaInscrição.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumDaInscrição.setBounds(23, 32, 117, 25);
		TelaInscrição.add(lblNumDaInscrição);

		TFbuscarInscrição = new JTextField();
		TFbuscarInscrição.setBounds(182, 37, 154, 18);
		TelaInscrição.add(TFbuscarInscrição);
		TFbuscarInscrição.setColumns(10);			

		JButton btnBuscarInscrição = new JButton("Buscar Inscrição");
		btnBuscarInscrição.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarInscrição.setBounds(411, 37, 166, 20);
		TelaInscrição.add(btnBuscarInscrição);
		
		
		ProfessorController pCont = new ProfessorController(TFProfessorCpf, TFProfessorNome, TFProfessorAreaInscricao, TFProfessorPontos, taProfessorLista);
		
		btnCadastrarProfessor.addActionListener(pCont);
		btnBuscarProfessor.addActionListener(pCont);
		
		
		

	}
}