package com.app.AnotaAi.bancodedados;
/*Create by: Amanda.Oliveira 19/05/2021*/

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class BancoDeDados {

    public SQLiteDatabase banco;
    public GerenciarBanco gerenciarBanco;

    public BancoDeDados(Context context) {
        gerenciarBanco = new GerenciarBanco(context);
    }

    public boolean criarAnotacao(String titulo, String conteudo) {
        banco = gerenciarBanco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);

        long resultado = banco.insert("anotacoes", null, valores);
        banco.close();

        return resultado > 0;
    }

    public Cursor obterAnotacoes() {
        String[] campos = {"_id", "titulo" };
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        Cursor cursor = db.query("anotacoes", campos, null, null, null, null, "titulo ASC");

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor consultarAnotacaoPeloId(int notaId) {
        Cursor cursor;
        String[] campos = {"_id", "titulo", "conteudo" };
        String where = "_id =" + notaId;
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        cursor = db.query("anotacoes", campos, where, null, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
    }

    public void atualizarAnotacao(int id, String titulo, String conteudo) {
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = " + id;

        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);

        db.update("anotacoes", valores, where, null);
        db.close();
    }

    public void excluirAnotacao(int id) {
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        String where = "_id = " + id;

        db.delete("anotacoes", where, null);
        db.close();
    }
}
