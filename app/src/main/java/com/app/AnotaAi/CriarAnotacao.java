package com.app.AnotaAi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.AnotaAi.TelaInicial;
import com.app.AnotaAi.bancodedados.BancoDeDados;
import com.app.anotaai.R;


public class CriarAnotacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_anotacao);
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Message?")
                .setMessage("Deseja sair do app?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        CriarAnotacao.super.onBackPressed();
                    }
                }).create().show();
    }

    public void voltar (View v) {
        Intent startNewActivity = new Intent(this, TelaInicial.class);
        startActivity(startNewActivity);
    }

    public void criarAnotacao (View v) {
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.campoTitulo);
        EditText conteudo = (EditText) findViewById(R.id.campoConteudo);

        boolean resultado = bancoDeDados.criarAnotacao(titulo.getText().toString(), conteudo.getText().toString());

        if (resultado) {
            Toast.makeText(getApplicationContext(), "Anotação criada com sucesso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Infelizmente ocorreu um erro, tente novamente!", Toast.LENGTH_LONG).show();
        }
        voltar(v);
    }
}