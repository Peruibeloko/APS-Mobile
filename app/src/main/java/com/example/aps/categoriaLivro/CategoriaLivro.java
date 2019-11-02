package com.example.aps.categoriaLivro;

import android.database.sqlite.SQLiteDatabase;

public class CategoriaLivro {

    private int _id;
    private int prazoEmp; /* Prazo de emprestimo */
    private String descricao;
    private double taxaAtraso;

    public CategoriaLivro(int _id, int prazoEmp, String descricao, double taxaAtraso) {
        this._id = _id;
        this.prazoEmp = prazoEmp;
        this.descricao = descricao;
        this.taxaAtraso = taxaAtraso;
    }

    public CategoriaLivro() {
    }

    public static String getTable() {
        return "catLivro";
    }

    public int getCodigo() {
        return _id;
    }

    public void setCodigo(int codigo) {
        this._id = codigo;
    }

    public int getPrazoEmp() {
        return prazoEmp;
    }

    public void setPrazoEmp(int prazoEmp) {
        this.prazoEmp = prazoEmp;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getTaxaAtraso() {
        return taxaAtraso;
    }

    public void setTaxaAtraso(double taxaAtraso) {
        this.taxaAtraso = taxaAtraso;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + getTable() + "(" +
                "_id integer primary key autoincrement," +
                "prazoEmp integer," +
                "descricao text, " +
                "taxaAtraso double " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTable());
    }
}
