package com.jmfragallo.ceep_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmfragallo.ceep_android.dao.NotaDAO
import com.jmfragallo.ceep_android.model.Nota
import com.jmfragallo.ceep_android.ui.recyclerview.adapter.ListaNotaAdapter


class ActivityListaNotas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)

        var listaNotas: RecyclerView = findViewById(R.id.lista_notas_recyclerview)

        var dao: NotaDAO = NotaDAO();
        for (i in 1..10000) {
            dao.insere(Nota("Titulo: $i", "Descrição $i"))
        }

        val todasNotas: List<Nota> = dao.todos()
        listaNotas.adapter = ListaNotaAdapter(this, todasNotas)
        val l: LinearLayoutManager = LinearLayoutManager(this)
        listaNotas.layoutManager = l

        dao.insere("Primeira Nota")

    }

}