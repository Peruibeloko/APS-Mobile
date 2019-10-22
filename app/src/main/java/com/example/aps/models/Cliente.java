package com.example.aps.models;

import android.database.sqlite.SQLiteDatabase;

public class Cliente {
    private static final String TABELA_CLIENTES = "cliente";

    private int id;
    private int codCat;
    private String nome;
    private String endereco;
    private String celular;
    private String email;
    private String cpf;
    private String dtNascimento;

    public Cliente(int codCat, String nome, String endereco, String celular, String email, String cpf, String dtNascimento) {
        this.codCat = codCat;
        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
        this.email = email;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
    }

    public static String getTabelaClientes() {
        return TABELA_CLIENTES;
    }

    public int getId() {
        return id;
    }

    public int getCodCat() {
        return codCat;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public static void createTabela(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABELA_CLIENTES + "(" +
                "id integer primary key autoincrement," +
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

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_CLIENTES);
    }
}
