package com.jmfragallo.ceep_android.ui.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jmfragallo.ceep_android.model.Nota


class FormularioNotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_nota_salva, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (ehMenuSalvaNota(item)) {
            val notaCriada = criaNota()
            retornaNota(notaCriada)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun retornaNota(nota: Nota) {
        val resultadoInsercao = Intent()
        resultadoInsercao.putExtra(CHAVE_NOTA, nota)
        setResult(CODIGO_RESULTADO_NOTA_CRIADA, resultadoInsercao)
    }

    private fun criaNota(): Nota {
        val titulo = findViewById<EditText>(com.jmfragallo.ceep_android.R.id.formulario_nota_titulo)
        val descricao = findViewById<EditText>(R.id.formulario_nota_descricao)
        return Nota(
            titulo.text.toString(),
            descricao.text.toString()
        )
    }

    private fun ehMenuSalvaNota(item: MenuItem): Boolean {
        return item.itemId == R.id.menu_formulario_nota_ic_salva
    }
}