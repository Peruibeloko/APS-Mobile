package com.example.aps.categoriaLivro;

import android.database.sqlite.SQLiteDatabase;

public class CategoriaLivro {

    private int codigo;
    private int prazoEmp; /* Prazo de emprestimo */
    private String descricao;
    private double taxaAtraso;

    public CategoriaLivro(int prazoEmp, String descricao, double taxaAtraso) {
        this.prazoEmp = prazoEmp;
        this.descricao = descricao;
        this.taxaAtraso = taxaAtraso;
    }

    public static String getTable() {
        return "catLivro";
    }

    public int getCodigo() {
        return codigo;
    }

    public int getPrazoEmp() {
        return prazoEmp;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getTaxaAtraso() {
        return taxaAtraso;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + getTable() + "(" +
                "codigo integer primary key autoincrement," +
                "prazoEmp integer," +
                "descricao text, " +
                "taxaAtraso double " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTable());
    }
}
