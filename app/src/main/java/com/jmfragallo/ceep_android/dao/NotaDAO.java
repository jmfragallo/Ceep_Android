package com.jmfragallo.ceep_android.dao;

import com.jmfragallo.ceep_android.model.Nota;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class NotaDAO {

    private final static ArrayList<Nota> notas = new ArrayList<>();

    public List<Nota> todos() {
        return (List<Nota>) notas.clone();
    }

    public void insere(Nota... notas) {
        NotaDAO.notas.addAll(Arrays.asList(notas));
    }

    public void altera(int posicao, Nota nota) {
        notas.set(posicao, nota);
    }

    public void remove(int posicao) {
        notas.remove(posicao);
    }

    public void troca(int posicaoInicio, int posicaoFim) {
        Collections.swap(notas, posicaoInicio, posicaoFim);
    }

    public void removeTodos() {
        notas.clear();
    }

    public void insere(@NotNull String s) {

    }
}
