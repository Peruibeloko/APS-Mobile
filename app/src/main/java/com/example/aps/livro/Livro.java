package com.example.aps.livro;

import android.database.sqlite.SQLiteDatabase;

public class Livro {

    private int _id;
    private int codCat;
    private int edicao;
    private int paginas;
    private int cliente;
    private String dtSaida;
    private String dtRetorno;
    private String dtPublicacao;
    private String isbn;
    private String titulo;
    private String autores;
    private String keywords;
    private String editora;

    public Livro() {
    }

    public Livro(int _id, int codCat, int edicao, int paginas, String dtPublicacao, String isbn, String titulo, String autores, String keywords, String editora) {
        this._id = _id;
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

    public static String getTable() {
        return "livro";
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getCodCat() {
        return codCat;
    }

    public void setCodCat(int codCat) {
        this.codCat = codCat;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(String dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + getTable() + "(" +
                "_id integer primary key autoincrement," +
                "codCat integer," +
                "edicao integer," +
                "paginas integer," +
                "dtSaida text," +
                "dtRetorno text," +
                "dtPublicacao text," +
                "isbn text," +
                "titulo text," +
                "autores text," +
                "keywords text," +
                "editora text, " +
                "cliente integer)";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTable());
    }
}
