package com.example.aps.categoriaLeitor;

import android.database.sqlite.SQLiteDatabase;

public class CategoriaLeitor {

    private int codigo;
    private int prazoDev; /* Prazo para devolucao */
    private String descricao;

    public CategoriaLeitor(int prazoDev, String descricao) {
        this.prazoDev = prazoDev;
        this.descricao = descricao;
    }

    public static String getTable() {
        return "catLeitor";
    }

    public int getCodigo() {
        return codigo;
    }

    public int getPrazoDev() {
        return prazoDev;
    }

    public String getDescricao() {
        return descricao;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + getTable() + "(" +
                "codigo integer primary key autoincrement," +
                "prazoDev integer," +
                "descricao text " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTable());
    }
}
