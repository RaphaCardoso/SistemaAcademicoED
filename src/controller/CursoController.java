package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.estruturaDados.fila.Fila;
import br.edu.estruturaDados.listaEncadeada.ListaEncadeada;
import br.edu.estruturaDados.listaEncadeada.No;
import model.Curso;
import model.Disciplina;

public class CursoController implements ActionListener {

    private JTextField tfCodigo;
    private JTextField tfNome;
    private JTextField tfArea;
    private JTextArea taLista;

    public CursoController(JTextField tfCodigo, JTextField tfNome,
                           JTextField tfArea, JTextArea taLista) {

        this.tfCodigo = tfCodigo;
        this.tfNome = tfNome;
        this.tfArea = tfArea;
        this.taLista = taLista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        try {

            if (cmd.equals("Cadastrar Curso")) {
                cadastrar();
            }

            if (cmd.equals("Buscar Curso")) {
                consultar();
            }

            if (cmd.equals("Editar Curso")) {
                atualizar();
            }

            if (cmd.equals("Deletar Curso")) {
                remover();
            }

        } catch (Exception ex) {
            taLista.setText("Erro: " + ex.getMessage());
        }
    }


    private File getArquivoCursos() {

        String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";

        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        return new File(path, "cursos.csv");
    }


    public ListaEncadeada<Curso> carregarLista() throws IOException {

        ListaEncadeada<Curso> lista = new ListaEncadeada<>();

        File arq = getArquivoCursos();

        if (arq.exists()) {

            BufferedReader br = new BufferedReader(new FileReader(arq));

            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");

                if (dados.length < 3) continue;

                Curso c = new Curso(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2]
                );

                lista.addLast(c);
            }

            br.close();
        }

        return lista;
    }


    private void salvarLista(ListaEncadeada<Curso> lista) throws IOException {

        File arq = getArquivoCursos();

        FileWriter fw = new FileWriter(arq, false);
        PrintWriter pw = new PrintWriter(fw);

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {
            pw.println(aux.getDado().toString());
            aux = aux.getProximo();
        }

        pw.close();
        fw.close();
    }


    private void cadastrar() throws Exception {

        String codigo = tfCodigo.getText().trim();
        String nome = tfNome.getText().trim();
        String area = tfArea.getText().trim();

        if (codigo.isEmpty() || nome.isEmpty() || area.isEmpty()) {
            taLista.setText("Preencha todos os campos!");
            return;
        }

        if (codigoJaExiste(codigo)) {
            taLista.setText("Código já existe!");
            return;
        }

        File arq = getArquivoCursos();

        FileWriter fw = new FileWriter(arq, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(codigo + ";" + nome + ";" + area);

        pw.close();
        fw.close();

        taLista.setText("Curso cadastrado com sucesso!");

        limpar();
    }

  
    private void consultar() throws Exception {

        String codigo = tfCodigo.getText().trim();

        Fila<Curso> fila = carregarFila();

        StringBuilder sb = new StringBuilder();

        if (codigo.isEmpty()) {

            while (!fila.isEmpty()) {
                sb.append(fila.dequeue().toString()).append("\n");
            }

            taLista.setText(sb.toString());
            return;
        }

        while (!fila.isEmpty()) {

            Curso c = fila.dequeue();

            if (String.valueOf(c.getCodigoCurso()).equals(codigo)) {
                taLista.setText(c.toString());
                return;
            }
        }

        taLista.setText("Curso não encontrado.");
    }

    private Fila<Curso> carregarFila() throws Exception {

        Fila<Curso> fila = new Fila<>();

        ListaEncadeada<Curso> lista = carregarLista();

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {
            fila.enqueue(aux.getDado());
            aux = aux.getProximo();
        }

        return fila;
    }


    private void atualizar() throws Exception {

        String codigo = tfCodigo.getText().trim();
        String nome = tfNome.getText().trim();
        String area = tfArea.getText().trim();

        if (codigo.isEmpty() || nome.isEmpty() || area.isEmpty()) {
            taLista.setText("Preencha todos os campos!");
            return;
        }
        
        

        ListaEncadeada<Curso> lista = carregarLista();

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {

            Curso c = aux.getDado();

            if (String.valueOf(c.getCodigoCurso()).equals(codigo)) {

                c.setNomeCurso(nome);
                c.setAreaConhecimento(area);

                salvarLista(lista);

                taLista.setText("Curso atualizado com sucesso!");
                limpar();
                return;
            }

            aux = aux.getProximo();
        }

        taLista.setText("Curso não encontrado.");
    }
    
    


    private void remover() throws Exception {

        String codigo = tfCodigo.getText().trim();

        if (codigo.isEmpty()) {
            taLista.setText("Informe o código para remover!");
            return;
        }

        ListaEncadeada<Curso> lista = carregarLista();

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {

            Curso c = aux.getDado();

            if (String.valueOf(c.getCodigoCurso()).equals(codigo)) {

                DisciplinaController dc =
                        new DisciplinaController(
                                null, null, null,
                                null, null, null, null);

                dc.removerDisciplinasPorCurso(codigo);

                lista.remove(c);

                salvarLista(lista);

                taLista.setText(
                    "Curso removido com sucesso! "
                    + "Disciplinas vinculadas removidas."
                );

                limpar();
                return;
            }

            aux = aux.getProximo();
        }

        taLista.setText("Curso não encontrado.");
    }

   
    public boolean codigoJaExiste(String codigo) throws Exception {

        ListaEncadeada<Curso> lista = carregarLista();

        No<Curso> aux = lista.getPrimeiro();

        while (aux != null) {

            if (String.valueOf(aux.getDado().getCodigoCurso()).equals(codigo)) {
                return true;
            }

            aux = aux.getProximo();
        }

        return false;
    }

    private void limpar() {
        tfCodigo.setText("");
        tfNome.setText("");
        tfArea.setText("");
    }
}