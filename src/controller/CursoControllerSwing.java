	package controller;
	
	import java.awt.event.*;
	import javax.swing.*;
	import model.*;
	
	public class CursoControllerSwing implements ActionListener {
		
		private JTextField TFCursoCodigo;
		private JTextField TFCursoNome;
		private JTextField TFCursoArea;
		private JTextArea taLista;
	
		private CursoController controller;
		private String caminho = "BancoDados/cursos.csv";
		
		public CursoControllerSwing(JTextField TFCursoCodigo, JTextField TFCursoNome,
	            JTextField TFCursoArea, JTextArea taLista) {
				
				this.TFCursoCodigo = TFCursoCodigo;
				this.TFCursoNome = TFCursoNome;
				this.TFCursoArea = TFCursoArea;
				this.taLista = taLista;
	
				controller = new CursoController();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
	
		    String cmd = e.getActionCommand();
	
		    try {
		        if (cmd.equals("Cadastrar Curso")) {
		            cadastrar();
		        }
		        if (cmd.equals("Buscar Curso")) {
		            buscar();
		        }
		        if (cmd.equals("Editar Curso")) {
		            editar();
		        }
		        if (cmd.equals("Deletar Curso")) {
		            deletar();
		        }
		    } catch (Exception ex) {
		        taLista.setText("Erro: " + ex.getMessage());
		    }
		}
		
		private void cadastrar() throws Exception {
	
		    int codigo = Integer.parseInt(TFCursoCodigo.getText());
		    String nome = TFCursoNome.getText();
		    String area = TFCursoArea.getText();
	
		    Curso c = new Curso(codigo, nome, area);
	
		    controller.adicionar(c, caminho);
	
		    taLista.setText("Curso cadastrado com sucesso!");
	
		    limparCampos();
		}	
		
		private void buscar() throws Exception {

		    String textoCodigo = TFCursoCodigo.getText();

		    // 👉 Se estiver vazio → listar tudo
		    if (textoCodigo.isEmpty()) {

		        String lista = controller.listarTodos(caminho);
		        taLista.setText(lista);

		        return;
		    }

		    // 👉 Se tiver código → busca normal
		    int codigo = Integer.parseInt(textoCodigo);

		    Curso c = controller.buscar(codigo, caminho);

		    if (c != null) {
		        taLista.setText(c.toString());
		    } else {
		        taLista.setText("Curso não encontrado!");
		    }
		    
		    limparCampos();

		}	
		
		private void limparCampos() {
		    TFCursoCodigo.setText("");
		    TFCursoNome.setText("");
		    TFCursoArea.setText("");
		}
		
		
		
		private void editar() throws Exception {

		    String textoCodigo = TFCursoCodigo.getText().trim();
		    String nome = TFCursoNome.getText().trim();
		    String area = TFCursoArea.getText().trim();

		    // 🔒 validação
		    if (textoCodigo.isEmpty()) {
		        throw new Exception("Informe o código do curso para editar!");
		    }

		    if (nome.isEmpty()) {
		        throw new Exception("Informe o nome do curso!");
		    }

		    if (area.isEmpty()) {
		        throw new Exception("Informe a área do conhecimento!");
		    }

		    int codigo = Integer.parseInt(textoCodigo);

		    Curso novoCurso = new Curso(codigo, nome, area);

		    controller.atualizar(codigo, novoCurso, caminho);

		    taLista.setText("Curso atualizado com sucesso!");

		    limparCampos();
		}
		
		private void deletar() throws Exception {

		    int codigo = Integer.parseInt(TFCursoCodigo.getText());

		    controller.remover(codigo, caminho);

		    taLista.setText("Curso removido com sucesso!");

		    limparCampos();
		}
	}