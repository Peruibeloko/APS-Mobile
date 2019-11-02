package com.example.aps.livro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crudud.R;


public class AlteraDadosLivro extends AppCompatActivity {

    // Lista de campos da tela
    EditText titulo;
    EditText autores;
    EditText codCat;
    EditText edicao;
    EditText paginas;
    EditText dtPublicacao;
    EditText isbn;
    EditText keywords;
    EditText editora;

    TextView txtId;

    Button alterar;
    Button deletar;
    Button consultar;
    Button cadastrar;
    Cursor cursor;
    LivroDAO dao;
    Livro obj;
    String codigo;

    boolean hasExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_altera_dados_livro);
        codigo = this.getIntent().getStringExtra("_id");
        dao = new LivroDAO(getBaseContext());

        try {
            cursor = dao.carregaDadoById(Integer.parseInt(codigo));
            hasExtra = true;
        } catch (Exception e) {
            System.err.println("Deu merda carregando os dados: \n\n");
            e.printStackTrace();
        }

        obj = new Livro();

        titulo = (EditText) findViewById(R.id.fieldTitulo);
        autores = (EditText) findViewById(R.id.fieldAutores);
        codCat = (EditText) findViewById(R.id.fieldCodCat);
        edicao = (EditText) findViewById(R.id.fieldEdicao);
        paginas = (EditText) findViewById(R.id.fieldPaginas);
        dtPublicacao = (EditText) findViewById(R.id.fieldDtPublicacao);
        isbn = (EditText) findViewById(R.id.fieldIsbn);
        keywords = (EditText) findViewById(R.id.fieldKeywords);
        editora = (EditText) findViewById(R.id.fieldEditora);
        txtId = (TextView) findViewById(R.id.txtId);

        if (hasExtra) {
            titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
            autores.setText(cursor.getString(cursor.getColumnIndexOrThrow("autores")));
            codCat.setText(cursor.getString(cursor.getColumnIndexOrThrow("codCat")));
            edicao.setText(cursor.getString(cursor.getColumnIndexOrThrow("edicao")));
            paginas.setText(cursor.getString(cursor.getColumnIndexOrThrow("paginas")));
            dtPublicacao.setText(cursor.getString(cursor.getColumnIndexOrThrow("dtPublicacao")));
            isbn.setText(cursor.getString(cursor.getColumnIndexOrThrow("isbn")));
            keywords.setText(cursor.getString(cursor.getColumnIndexOrThrow("keywords")));
            editora.setText(cursor.getString(cursor.getColumnIndexOrThrow("editora")));
            txtId.setText(cursor.getString(cursor.getColumnIndexOrThrow("_id")));

            updateObject();
        }

        alterar = (Button) findViewById(R.id.btnAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                dao.alteraRegistro(obj, Integer.parseInt(codigo));
            }
        });

        deletar = (Button) findViewById(R.id.btnDeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deletaRegistro(Integer.parseInt(codigo));
                clearFields();
            }
        });

        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                dao.insereDado(obj);
                clearFields();
            }
        });

        consultar = (Button) findViewById(R.id.btnConsultar);
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraDadosLivro.this, ConsultaDadosLivro.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearFields() {
        titulo.setText("");
        autores.setText("");
        codCat.setText("");
        edicao.setText("");
        paginas.setText("");
        dtPublicacao.setText("");
        isbn.setText("");
        keywords.setText("");
        editora.setText("");
    }

    private void updateObject() {
        obj.setTitulo(titulo.getText().toString());
        obj.setAutores(autores.getText().toString());
        obj.setCodCat(Integer.parseInt(codCat.getText().toString()));
        obj.setEdicao(Integer.parseInt(edicao.getText().toString()));
        obj.setPaginas(Integer.parseInt(paginas.getText().toString()));
        obj.setDtPublicacao(dtPublicacao.getText().toString());
        obj.setIsbn(isbn.getText().toString());
        obj.setKeywords(keywords.getText().toString());
        obj.setEditora(editora.getText().toString());
    }
}
