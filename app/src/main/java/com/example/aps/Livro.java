package com.example.aps;

import android.database.sqlite.SQLiteDatabase;

public class Livro {

    private static final String TABELA_LIVRO = "livro";

    private int id;
    private int codCat;
    private int edicao;
    private int paginas;
    private String dtPublicacao;
    private String isbn;
    private String titulo;
    private String autores;
    private String keywords;
    private String editora;

    public Livro(int codCat, int edicao, int paginas, String dtPublicacao, String isbn, String titulo, String autores, String keywords, String editora) {
        this.codCat = codCat;
        this.edicao = edicao;
        this.paginas = paginas;
        this.dtPublicacao = dtPublicacao;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.keywords = keywords;
        this.editora = editora;
    }

    public static String getTabelaLivro() {
        return TABELA_LIVRO;
    }

    public int getId() {
        return id;
    }

    public int getCodCat() {
        return codCat;
    }

    public int getEdicao() {
        return edicao;
    }

    public int getPaginas() {
        return paginas;
    }

    public String getDtPublicacao() {
        return dtPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getEditora() {
        return editora;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABELA_LIVRO + "(" +
                "id integer primary key autoincrement," +
                "codCat integer," +
                "edicao integer," +
                "paginas integer," +
                "titulo text," +
                "autores text," +
                "editora text " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_LIVRO);
    }
}
