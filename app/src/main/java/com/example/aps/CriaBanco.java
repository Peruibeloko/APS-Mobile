package com.example.aps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final int VERSAO = 1;

    public static String getNomeBanco() {
        return NOME_BANCO;
    }
    public static int getVERSAO() {
        return VERSAO;
    }

    public CriaBanco(Context context) {

        super(context, getNomeBanco(), null, getVERSAO());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABELA + "(" +
                ID + " integer primary key autoincrement," +
                TITULO + " text," +
                AUTOR + " text," +
                EDITORA + " text " + ")";

                sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }
}