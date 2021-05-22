package com.app.AnotaAi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.app.AnotaAi.bancodedados.BancoDeDados;
import com.app.anotaai.R;


public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        final Cursor cursor = bancoDeDados.obterAnotacoes();

        String[] nomeCampos = new String[]{"_id", "titulo" };
        int[] idViews = new int[]{R.id.labelId, R.id.labelTitulo};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.modelo_lista, cursor, nomeCampos, idViews, 0);

        ListView lista = (ListView) findViewById(R.id.listaDeNotas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(TelaInicial.this, com.app.AnotaAi.EditarAnotacao.class);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Deseja sair do app?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        TelaInicial.super.onBackPressed();
                    }
                }).create().show();
    }
    public void abrirTelaCriarNovaAnotacao(View v) {
        Intent startNewActivity = new Intent(this, CriarAnotacao.class);
        startActivity(startNewActivity);
    }


}

