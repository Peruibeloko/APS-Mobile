package com.example.aps;

import android.database.sqlite.SQLiteDatabase;

public class Livro {

    private static final String TABELA_LIVRO = "livro";

    private int codCat;
    private int edicao;
    private int paginas;
    private String dtPublicacao;
    private String isbn;
    private String titulo;
    private String autores;
    private String keywords;
    private String editora;

    public static void createTabelaLivro(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABELA_LIVRO + "(" +
                "codCat integer primary key autoincrement," +
                "edicao integer," +
                "paginas integer," +
                "titulo text," +
                "autores text," +
                "editora text " + ")";

        sqLiteDatabase.execSQL(sql);
    }
}
