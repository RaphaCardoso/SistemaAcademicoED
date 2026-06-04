package view;

import model.*;

public class Principal {

	public static void main(String[] args) throws Exception {

		ListaCurso lista = new ListaCurso();

		lista.addFirst(new Curso(1, "ADS", "TI"));
		lista.addLast(new Curso(2, "Engenharia", "Exatas"));

		System.out.println(lista.tamanho()); 

		
	}

}
