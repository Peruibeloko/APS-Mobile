package com.example.aps.categoriaLivro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aps.CriaBanco;

import java.lang.reflect.Field;

public class CategoriaLivroDAO {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private String table = CategoriaLivro.getTable();

    public CategoriaLivroDAO(Context context) {
        this.banco = new CriaBanco(context);
    }

    public String insereDado(CategoriaLivro obj) {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();

        valores.put("prazoEmp", obj.getPrazoEmp());
        valores.put("descricao", obj.getDescricao());
        valores.put("taxaAtraso", obj.getTaxaAtraso());

        long resultado = db.insert(table, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public void alteraRegistro(CategoriaLivro obj, int id){
        ContentValues valores = new ContentValues();
        String where = "_id = " + id;

        db = banco.getWritableDatabase();

        valores.put("prazoEmp", obj.getPrazoEmp());
        valores.put("descricao", obj.getDescricao());
        valores.put("taxaAtraso", obj.getTaxaAtraso());

        db.update(table, valores, where,null);
        db.close();
    }

    public Cursor carregaDados() {
        Field[] fl = CategoriaLivro.class.getDeclaredFields();
        String[] campos = new String[fl.length];

        for(int i = 0; i < fl.length; i++){
            campos[i] = fl[i].getName();
        }

        db = banco.getReadableDatabase();
        Cursor cursor = db.query(table, campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Field[] fl = CategoriaLivro.class.getDeclaredFields();
        String[] campos = new String[fl.length];

        for(int i = 0; i < fl.length; i++){
            campos[i] = fl[i].getName();
        }

        String where = "_id =" + id;
        db = banco.getReadableDatabase();
        Cursor cursor = db.query(table, campos, where, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void deletaRegistro(int id){
        String where = "_id = " + id;
        db = banco.getReadableDatabase();
        db.delete(table, where,null);
        db.close();
    }
}

