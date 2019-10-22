package com.example.aps.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aps.CriaBanco;
import com.example.aps.models.Livro;

import java.lang.reflect.Field;

public class LivroDAO {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private String table = Livro.getTable();

    public LivroDAO(CriaBanco banco) {
        this.banco = banco;
    }

    public String insereDado(Livro lv) {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();

        valores.put("id", lv.getId());
        valores.put("codCat", lv.getCodCat());
        valores.put("edicao", lv.getEdicao());
        valores.put("paginas", lv.getPaginas());
        valores.put("dtPublicacao", lv.getDtPublicacao());
        valores.put("isbn", lv.getIsbn());
        valores.put("titulo", lv.getTitulo());
        valores.put("autores", lv.getAutores());
        valores.put("keywords", lv.getKeywords());
        valores.put("editora", lv.getEditora());

        long resultado = db.insert(table, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public void alteraRegistro(Livro lv, int id){
        ContentValues valores = new ContentValues();
        String where = "id = " + id;

        db = banco.getWritableDatabase();

        valores.put("id", lv.getId());
        valores.put("codCat", lv.getCodCat());
        valores.put("edicao", lv.getEdicao());
        valores.put("paginas", lv.getPaginas());
        valores.put("dtPublicacao", lv.getDtPublicacao());
        valores.put("isbn", lv.getIsbn());
        valores.put("titulo", lv.getTitulo());
        valores.put("autores", lv.getAutores());
        valores.put("keywords", lv.getKeywords());
        valores.put("editora", lv.getEditora());

        db.update(table, valores, where,null);
        db.close();
    }

    public Cursor carregaDados(Livro obj) {
        Field[] fl = obj.getClass().getDeclaredFields();
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

    public Cursor carregaDadoById(Livro obj, int id){
        Field[] fl = obj.getClass().getDeclaredFields();
        String[] campos = new String[fl.length];

        for(int i = 0; i < fl.length; i++){
            campos[i] = fl[i].getName();
        }

        String where = "id =" + id;
        db = banco.getReadableDatabase();
        Cursor cursor = db.query(table, campos, where, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void deletaRegistro(int id){
        String where = "id = " + id;
        db = banco.getReadableDatabase();
        db.delete(table, where,null);
        db.close();
    }
}

