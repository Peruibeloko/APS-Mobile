package com.example.aps.cliente;

import android.database.sqlite.SQLiteDatabase;

public class Cliente {

    private int _id;
    private int codCat;
    private String nome;
    private String endereco;
    private String celular;
    private String email;
    private String cpf;
    private String dtNascimento;

    public Cliente() {
    }
    public Cliente(int _id, int codCat, String nome, String endereco, String celular, String email, String cpf, String dtNascimento) {
        this._id = _id;
        this.codCat = codCat;
        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
        this.email = email;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
    }

    public static String getTable() {
        return "cliente";
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
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getDtNascimento() {
        return dtNascimento;
    }
    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + getTable() + "(" +
                "_id integer primary key autoincrement," +
                "codCat integer," +
                "nome text," +
                "endereco text," +
                "celular text," +
                "email text," +
                "cpf text," +
                "dtNascimento text " + ")";

        sqLiteDatabase.execSQL(sql);
    }

    public static void upgradeTabela(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTable());
    }
}
