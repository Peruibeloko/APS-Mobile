package com.example.aps;

import android.database.sqlite.SQLiteDatabase;

public class CategoriaLivro {
    private static final String TABELA_CAT_LIVRO = "catLivro";

    private int codigo;
    private int prazoEmp; /* Prazo de emprestimo */
    private String descricao;
    private double taxaAtraso;

    public CategoriaLivro(int prazoEmp, String descricao, double taxaAtraso) {
        this.prazoEmp = prazoEmp;
        this.descricao = descricao;
        this.taxaAtraso = taxaAtraso;
    }

    public static String getTabelaCatLivro() {
        return TABELA_CAT_LIVRO;
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

        String sql = "CREATE TABLE " + TABELA_CAT_LIVRO + "(" +
                "codigo integer primary key autoincrement," +
                "prazoEmp integer," +
                "descricao text, " +
                "taxaAtraso double " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_CAT_LIVRO);
    }
}
