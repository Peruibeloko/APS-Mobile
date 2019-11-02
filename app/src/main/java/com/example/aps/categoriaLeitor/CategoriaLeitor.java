package com.example.aps.categoriaLeitor;

import android.database.sqlite.SQLiteDatabase;

public class CategoriaLeitor {

    private int _id;
    private int prazoDev; /* Prazo para devolucao */
    private String descricao;

    public CategoriaLeitor(int _id, int prazoDev, String descricao) {
        this._id = _id;
        this.prazoDev = prazoDev;
        this.descricao = descricao;
    }

    public CategoriaLeitor() {
    }

    public static String getTable() {
        return "catLeitor";
    }

    public int getCodigo() {
        return _id;
    }

    public void setCodigo(int codigo) {
        this._id = codigo;
    }

    public int getPrazoDev() {
        return prazoDev;
    }

    public void setPrazoDev(int prazoDev) {
        this.prazoDev = prazoDev;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + getTable() + "(" +
                "_id integer primary key autoincrement," +
                "prazoDev integer," +
                "descricao text " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTable());
    }
}
