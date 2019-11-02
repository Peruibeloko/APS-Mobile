package com.example.aps.categoriaLeitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aps.CriaBanco;

import java.lang.reflect.Field;

public class CategoriaLeitorDAO {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private String table = CategoriaLeitor.getTable();

    public CategoriaLeitorDAO(Context context) {
        this.banco = new CriaBanco(context);
    }

    public String insereDado(CategoriaLeitor obj) {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();

        valores.put("prazoDev", obj.getPrazoDev());
        valores.put("descricao", obj.getDescricao());

        long resultado = db.insert(table, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public void alteraRegistro(CategoriaLeitor obj, int id) {
        ContentValues valores = new ContentValues();
        String where = "_id = " + id;

        db = banco.getWritableDatabase();

        valores.put("prazoDev", obj.getPrazoDev());
        valores.put("descricao", obj.getDescricao());

        db.update(table, valores, where, null);
        db.close();
    }

    public Cursor carregaDados() {
        Field[] fl = CategoriaLeitor.class.getDeclaredFields();
        String[] campos = new String[fl.length];

        for (int i = 0; i < fl.length; i++) {
            campos[i] = fl[i].getName();
        }

        db = banco.getReadableDatabase();
        Cursor cursor = db.query(table, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id) {
        Field[] fl = CategoriaLeitor.class.getDeclaredFields();
        String[] campos = new String[fl.length];

        for (int i = 0; i < fl.length; i++) {
            campos[i] = fl[i].getName();
        }

        String where = "_id =" + id;
        db = banco.getReadableDatabase();
        Cursor cursor = db.query(table, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void deletaRegistro(int id) {
        String where = "_id = " + id;
        db = banco.getReadableDatabase();
        db.delete(table, where, null);
        db.close();
    }
}

