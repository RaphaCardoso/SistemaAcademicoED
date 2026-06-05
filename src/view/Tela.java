package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CursoControllerSwing;
import controller.DisciplinaController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JTextField tfDisciplinaHora;
	private JTextField tfDisciplinaQtdHoras;
	private JTextField tfDisciplinaCodCurso;
	private JTextField tfDisciplinaSemana;
	private JTextField TFCursoNome;
	private JTextField TFCursoCodigo;
	private JTextField TFCursoArea;

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

		// curso

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

		JButton btnCadastrarCurso = new JButton("Cadastrar Curso");
		btnCadastrarCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCadastrarCurso.setBounds(411, 10, 166, 20);
		TelaCurso.add(btnCadastrarCurso);

		JButton btnBuscarCurso = new JButton("Buscar Curso");
		btnBuscarCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarCurso.setBounds(411, 42, 166, 20);
		TelaCurso.add(btnBuscarCurso);
		
		JButton btnEditarCurso = new JButton("Editar Curso");
		btnEditarCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditarCurso.setBounds(411, 72, 166, 20);
		TelaCurso.add(btnEditarCurso);
		
		JButton btnDeletarCurso = new JButton("Deletar Curso");
		btnDeletarCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeletarCurso.setBounds(411, 106, 166, 20);
		TelaCurso.add(btnDeletarCurso);
	

		JTextArea TAListaCurso = new JTextArea();
		TAListaCurso.setFont(new Font("Monospaced", Font.PLAIN, 13));
		TAListaCurso.setBounds(23, 137, 568, 249);
		TelaCurso.add(TAListaCurso);
		
		TFCursoCodigo = new JTextField();
		TFCursoCodigo.setBounds(182, 37, 180, 18);
		TelaCurso.add(TFCursoCodigo);
		TFCursoCodigo.setColumns(10);
		
		TFCursoNome = new JTextField();
		TFCursoNome.setBounds(182, 72, 180, 18);
		TelaCurso.add(TFCursoNome);
		TFCursoNome.setColumns(10);
		
		TFCursoArea = new JTextField();
		TFCursoArea.setBounds(182, 107, 180, 18);
		TelaCurso.add(TFCursoArea);


		CursoControllerSwing cCont = new CursoControllerSwing(
			    TFCursoCodigo, TFCursoNome, TFCursoArea, TAListaCurso
			);
		
		btnCadastrarCurso.addActionListener(cCont);
		btnBuscarCurso.addActionListener(cCont);
		btnEditarCurso.addActionListener(cCont);
		btnDeletarCurso.addActionListener(cCont);
		

		// Tela Professor

		JPanel TelaProfessor = new JPanel();
		tabbedPane.addTab("Professor", null, TelaProfessor, null);
		TelaProfessor.setLayout(null);

		// Campos Professor

		// Buscar Professor
		JLabel lblBuscarProfessor = new JLabel("Buscar professor");
		lblBuscarProfessor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscarProfessor.setBounds(30, 9, 117, 17);
		TelaProfessor.add(lblBuscarProfessor);

		JLabel lblBuscarProfessorCPF = new JLabel("CPF");
		lblBuscarProfessorCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscarProfessorCPF.setBounds(179, 9, 24, 17);
		TelaProfessor.add(lblBuscarProfessorCPF);

		JButton btnBuscarProfessor = new JButton("Buscar Professor");
		btnBuscarProfessor.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarProfessor.setBounds(193, 170, 149, 25);
		TelaProfessor.add(btnBuscarProfessor);

		// Cadastrar Professor

		JButton btncadastrarProfessor = new JButton("Cadastrar Professor");
		btncadastrarProfessor.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncadastrarProfessor.setBounds(365, 130, 171, 25);
		TelaProfessor.add(btncadastrarProfessor);

		// Tela Buscar Incrição
		JPanel TelaInscrição = new JPanel();
		tabbedPane.addTab("Inscrição", null, TelaInscrição, null);
		TelaInscrição.setLayout(null);

		// Buscar Inscrição

		JLabel lblBuscarInscricao = new JLabel("Buscar inscrição");
		lblBuscarInscricao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscarInscricao.setBounds(59, 9, 110, 17);
		TelaInscrição.add(lblBuscarInscricao);

		JLabel lblNumDaInscrição = new JLabel("Numero da inscrição");
		lblNumDaInscrição.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumDaInscrição.setBounds(174, 9, 124, 17);
		TelaInscrição.add(lblNumDaInscrição);

		JButton btnBuscarInscrição = new JButton("Buscar Inscrição");
		btnBuscarInscrição.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscarInscrição.setBounds(394, 5, 147, 25);
		TelaInscrição.add(btnBuscarInscrição);

		tfDisciplinaCodigo = new JTextField();
		tfDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaCodigo.setBounds(173, 7, 86, 25);

		tfDisciplinaCodigo.setColumns(10);

		// Consulta de Inscritos da disciplina	

		JPanel TelaDisciplinas = new JPanel();
		tabbedPane.addTab("Disciplinas", null, TelaDisciplinas, null);
		TelaDisciplinas.setLayout(null);

		// Cod disciplinas

		JLabel lblDisciplinaCodigo = new JLabel("Codigo da disciplina:");
		lblDisciplinaCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCodigo.setBounds(10, 11, 129, 20);
		TelaDisciplinas.add(lblDisciplinaCodigo);
		TelaDisciplinas.add(tfDisciplinaCodigo);

		// Cadastrar Disciplinas
		JButton btnDisciplinasInserir = new JButton("Inserir");
		btnDisciplinasInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDisciplinasInserir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDisciplinasInserir.setBounds(476, 43, 115, 25);
		TelaDisciplinas.add(btnDisciplinasInserir);

		JLabel lblDisciplinaNome = new JLabel("Nome:");
		lblDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaNome.setBounds(10, 45, 142, 20);
		TelaDisciplinas.add(lblDisciplinaNome);

		JLabel lblDisciplnaSemana = new JLabel("Dia da Semana:");
		lblDisciplnaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplnaSemana.setBounds(10, 76, 142, 20);
		TelaDisciplinas.add(lblDisciplnaSemana);

		JLabel lblDisciplinaHora = new JLabel("Hora de início:");
		lblDisciplinaHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaHora.setBounds(10, 107, 142, 20);
		TelaDisciplinas.add(lblDisciplinaHora);

		JLabel lblDisciplinaQtdHora = new JLabel("Quantidade de horas:");
		lblDisciplinaQtdHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaQtdHora.setBounds(10, 138, 142, 20);
		TelaDisciplinas.add(lblDisciplinaQtdHora);

		JLabel lblDisciplinaCodCurso = new JLabel("Código do Curso:");
		lblDisciplinaCodCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplinaCodCurso.setBounds(10, 169, 142, 20);
		TelaDisciplinas.add(lblDisciplinaCodCurso);

		tfDisciplinaNome = new JTextField();
		tfDisciplinaNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaNome.setBounds(173, 43, 270, 25);
		TelaDisciplinas.add(tfDisciplinaNome);
		tfDisciplinaNome.setColumns(10);

		tfDisciplinaHora = new JTextField();
		tfDisciplinaHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaHora.setColumns(10);
		tfDisciplinaHora.setBounds(173, 105, 86, 25);
		TelaDisciplinas.add(tfDisciplinaHora);

		tfDisciplinaQtdHoras = new JTextField();
		tfDisciplinaQtdHoras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaQtdHoras.setColumns(10);
		tfDisciplinaQtdHoras.setBounds(173, 136, 86, 25);
		TelaDisciplinas.add(tfDisciplinaQtdHoras);

		tfDisciplinaCodCurso = new JTextField();
		tfDisciplinaCodCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaCodCurso.setColumns(10);
		tfDisciplinaCodCurso.setBounds(173, 167, 86, 25);
		TelaDisciplinas.add(tfDisciplinaCodCurso);

		JButton btnDisciplinasConsultar = new JButton("Consultar");
		btnDisciplinasConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDisciplinasConsultar.setBounds(476, 79, 115, 25);
		TelaDisciplinas.add(btnDisciplinasConsultar);

		JButton btnDisciplinasAtualizar = new JButton("Atualizar");
		btnDisciplinasAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDisciplinasAtualizar.setBounds(476, 115, 115, 25);
		TelaDisciplinas.add(btnDisciplinasAtualizar);

		JButton btnDisciplinasRemover = new JButton("Remover");
		btnDisciplinasRemover.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDisciplinasRemover.setBounds(476, 151, 115, 25);
		TelaDisciplinas.add(btnDisciplinasRemover);

		JScrollPane scrollPaneDisciplina = new JScrollPane();
		scrollPaneDisciplina.setBounds(10, 200, 581, 184);
		TelaDisciplinas.add(scrollPaneDisciplina);

		JTextArea taDisciplinaLista = new JTextArea();
		scrollPaneDisciplina.setViewportView(taDisciplinaLista);

		tfDisciplinaSemana = new JTextField();
		tfDisciplinaSemana.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDisciplinaSemana.setColumns(10);
		tfDisciplinaSemana.setBounds(173, 74, 86, 25);
		TelaDisciplinas.add(tfDisciplinaSemana);
		
		
		
		//Ações com botão
		DisciplinaController dCont = new DisciplinaController(tfDisciplinaCodigo, tfDisciplinaNome, tfDisciplinaHora, 
																tfDisciplinaQtdHoras, tfDisciplinaCodCurso, tfDisciplinaSemana, taDisciplinaLista);
		
		btnDisciplinasInserir.addActionListener(dCont);
		btnDisciplinasConsultar.addActionListener(dCont);
		btnDisciplinasAtualizar.addActionListener(dCont);
		btnDisciplinasRemover.addActionListener(dCont);
		

	}
}