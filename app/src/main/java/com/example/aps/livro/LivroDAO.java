package com.example.aps.livro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aps.CriaBanco;

import java.lang.reflect.Field;

public class LivroDAO {

    private SQLiteDatabase db;
    private CriaBanco banco;
    private String table = Livro.getTable();

    public LivroDAO(Context context) {
        this.banco = new CriaBanco(context);
    }

    public String insereDado(Livro obj) {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();

        valores.put("codCat", obj.getCodCat());
        valores.put("edicao", obj.getEdicao());
        valores.put("paginas", obj.getPaginas());
        valores.put("dtPublicacao", obj.getDtPublicacao());
        valores.put("isbn", obj.getIsbn());
        valores.put("titulo", obj.getTitulo());
        valores.put("autores", obj.getAutores());
        valores.put("keywords", obj.getKeywords());
        valores.put("editora", obj.getEditora());

        long resultado = db.insert(table, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public void alteraRegistro(Livro obj, int id){
        ContentValues valores = new ContentValues();
        String where = "_id = " + id;

        db = banco.getWritableDatabase();

        valores.put("codCat", obj.getCodCat());
        valores.put("edicao", obj.getEdicao());
        valores.put("paginas", obj.getPaginas());
        valores.put("dtPublicacao", obj.getDtPublicacao());
        valores.put("isbn", obj.getIsbn());
        valores.put("titulo", obj.getTitulo());
        valores.put("autores", obj.getAutores());
        valores.put("keywords", obj.getKeywords());
        valores.put("editora", obj.getEditora());

        db.update(table, valores, where,null);
        db.close();
    }

    public Cursor carregaDados() {
        Field[] fl = Livro.class.getDeclaredFields();
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
        Field[] fl = Livro.class.getDeclaredFields();
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

