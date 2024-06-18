package com.jmfragallo.ceep_android

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.jmfragallo.ceep_android.dao.NotaDAO
import com.jmfragallo.ceep_android.model.Nota
import com.jmfragallo.ceep_android.ui.activity.FormularioNotaActivity
import com.jmfragallo.ceep_android.ui.adapter.ListaNotasAdapter


class ListaNotasActivity : AppCompatActivity() {
    private var adapter: ListaNotasAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)
        val todasNotas = pegaTodasNotas()
        configuraRecyclerView(todasNotas)
        configuraBotaoInsereNota()
    }

    private fun configuraBotaoInsereNota() {
        val botaoInsereNota = findViewById<TextView>(R.id.lista_notas_insere_nota)
        botaoInsereNota.setOnClickListener { vaiParaFormularioNotaActivity() }
    }

    private fun vaiParaFormularioNotaActivity() {
        val iniciaFormularioNota = Intent(
            this@ListaNotasActivity,
            FormularioNotaActivity::class.java
        )
        startActivityForResult(
            iniciaFormularioNota,
            CODIGO_REQUISICAO_INSERE_NOTA
        )
    }

    private fun pegaTodasNotas(): List<Nota> {
        val dao = NotaDAO()
        return dao.todos()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ehResultadoComNota(requestCode, resultCode, data)) {
            val notaRecebida = data!!.getSerializableExtra(CHAVE_NOTA) as Nota?
            adiciona(notaRecebida)
        }
    }

    private fun adiciona(nota: Nota?) {
        NotaDAO().insere(nota)
        adapter.adiciona(nota)
    }

    private fun ehResultadoComNota(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return ehCodigoRequisicaoInsereNota(requestCode) &&
                ehCodigoResultadoNotaCriada(resultCode) &&
                temNota(data)
    }

    private fun temNota(data: Intent?): Boolean {
        return data!!.hasExtra(CHAVE_NOTA)
    }

    private fun ehCodigoResultadoNotaCriada(resultCode: Int): Boolean {
        return resultCode == CODIGO_RESULTADO_NOTA_CRIADA
    }

    private fun ehCodigoRequisicaoInsereNota(requestCode: Int): Boolean {
        return requestCode == CODIGO_REQUISICAO_INSERE_NOTA
    }

    private fun configuraRecyclerView(todasNotas: List<Nota>) {
        val listaNotas = findViewById<RecyclerView>(R.id.lista_notas_recyclerview)
        configuraAdapter(todasNotas, listaNotas)
    }

    private fun configuraAdapter(todasNotas: List<Nota>, listaNotas: RecyclerView) {
        adapter = ListaNotasAdapter(this, todasNotas)
        listaNotas.setAdapter(adapter)
    }
}