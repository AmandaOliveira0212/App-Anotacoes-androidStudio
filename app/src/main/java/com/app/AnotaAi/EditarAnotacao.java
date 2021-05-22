package com.app.AnotaAi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.AnotaAi.bancodedados.BancoDeDados;
import com.app.anotaai.R;


public class EditarAnotacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_anotacao);

        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        final Cursor cursor = bancoDeDados.consultarAnotacaoPeloId(this.getIntent().getIntExtra("id", 0));

        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoConteudo);

        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        conteudo.setText(cursor.getString(cursor.getColumnIndexOrThrow("conteudo")));
    }

    public void voltar(View v) {
        Intent starNewActivity = new Intent(this, TelaInicial.class);
        startActivity(starNewActivity);
    }

    public void atualizarAnotacao(View v) {
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoConteudo);

        try {
            bancoDeDados.atualizarAnotacao(this.getIntent().getIntExtra("id", 0), titulo.getText().toString(), conteudo.getText().toString());
            Toast.makeText(getApplicationContext(), "Anotação atualizada com sucesso!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Não foi possível atualizar a anotação, tente novamente!", Toast.LENGTH_LONG).show();
        }

        voltar(v);
    }

    public void excluirAnotacao(View v) {
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoConteudo);

        try {
            bancoDeDados.excluirAnotacao(this.getIntent().getIntExtra("id", 0));
            Toast.makeText(getApplicationContext(), "Anotação excluida com sucesso!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Não foi possível excluir a anotação, tente novamente!", Toast.LENGTH_LONG).show();
        }

        voltar(v);
    }
}