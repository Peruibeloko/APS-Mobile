package com.example.aps.categoriaLeitor;

import android.database.sqlite.SQLiteDatabase;

public class CategoriaLeitor {

    private int codigo;
    private int prazoDev; /* Prazo para devolucao */
    private String descricao;

    public CategoriaLeitor(int codigo, int prazoDev, String descricao) {
        this.codigo = codigo;
        this.prazoDev = prazoDev;
        this.descricao = descricao;
    }

    public CategoriaLeitor() {
    }

    public static String getTable() {
        return "catLeitor";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
                "codigo integer primary key autoincrement," +
                "prazoDev integer," +
                "descricao text " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTable());
    }
}
