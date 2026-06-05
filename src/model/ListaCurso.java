package model;

public class ListaCurso {

    private NoCurso primeiro;

    public boolean listaVazia() {
        return primeiro == null;
    }

    public NoCurso getPrimeiro() {
        return primeiro;
    }

    public void addFirst(Curso curso) {
        NoCurso novo = new NoCurso();
        novo.setDado(curso);
        novo.setProximo(primeiro);
        primeiro = novo;
    }

    public void addLast(Curso curso) {
        if (listaVazia()) {
            addFirst(curso);
        } else {
            NoCurso novo = new NoCurso();
            novo.setDado(curso);
            novo.setProximo(null);

            NoCurso aux = primeiro;

            while (aux.getProximo() != null) {
                aux = aux.getProximo();
            }

            aux.setProximo(novo);
        }
    }

    public void remove(int pos) throws Exception {

        if (listaVazia()) {
            throw new Exception("Lista vazia");
        }

        if (pos == 0) {
            primeiro = primeiro.getProximo();
        } else {
            NoCurso anterior = primeiro;

            for (int i = 0; i < pos - 1; i++) {
                anterior = anterior.getProximo();
            }

            NoCurso atual = anterior.getProximo();
            anterior.setProximo(atual.getProximo());
        }
    }
}