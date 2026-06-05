package view;

import controller.*;
import model.*;

public class Principal {

    public static void main(String[] args) throws Exception {

        CursoController controller = new CursoController();

        controller.adicionar(new Curso(4, "ADS", "TI"), "BancoDados/cursos.csv");
        controller.adicionar(new Curso(5, "Engenharia", "Exatas"), "BancoDados/cursos.csv");
        controller.adicionar(new Curso(6, "Enfermagem", "Humanas"), "BancoDados/cursos.csv");

        Curso c = controller.buscar(1, "BancoDados/cursos.csv");

        System.out.println(c);
        
        controller.atualizar(1, new Curso(1, "ADS Atualizado", "TI"), "BancoDados/cursos.csv");

        controller.remover(2, "BancoDados/cursos.csv");

        c = controller.buscar(1, "BancoDados/cursos.csv");

        System.out.println(c);
    }
}