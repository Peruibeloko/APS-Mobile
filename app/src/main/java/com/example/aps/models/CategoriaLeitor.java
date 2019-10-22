package com.example.aps.models;

import android.database.sqlite.SQLiteDatabase;

public class CategoriaLeitor {
    private static final String TABELA_CAT_LEITORES = "catLeitor";

    private int codigo;
    private int prazoDev; /* Prazo para devolucao */
    private String descricao;

    public CategoriaLeitor(int prazoDev, String descricao) {
        this.prazoDev = prazoDev;
        this.descricao = descricao;
    }

    public static String getTabelaCatLeitores() {
        return TABELA_CAT_LEITORES;
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

        String sql = "CREATE TABLE " + TABELA_CAT_LEITORES + "(" +
                "codigo integer primary key autoincrement," +
                "prazoDev integer," +
                "descricao text " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_CAT_LEITORES);
    }
}
