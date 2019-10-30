package com.example.aps.livro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    Button alterar;
    Button deletar;
    Button consultar;
    Button cadastrar;
    Cursor cursor;
    LivroDAO dao;
    Livro obj;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_altera_dados_livro);
        codigo = this.getIntent().getStringExtra("codigo");
        dao = new LivroDAO(getBaseContext());
        cursor = dao.carregaDadoById(Integer.parseInt(codigo));
        obj = new Livro();

        // Um pra cada campo
        titulo = (EditText)findViewById(R.id.fieldTitulo);
        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        titulo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setTitulo(titulo.getText().toString());
                }
            }
        });

        autores = (EditText)findViewById(R.id.fieldAutores);
        autores.setText(cursor.getString(cursor.getColumnIndexOrThrow("autores")));
        autores.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setAutores(autores.getText().toString());
                }
            }
        });

        codCat = (EditText)findViewById(R.id.fieldCodCat);
        codCat.setText(cursor.getString(cursor.getColumnIndexOrThrow("codCat")));
        codCat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setCodCat(Integer.parseInt(codCat.getText().toString()));
                }
            }
        });

        edicao = (EditText)findViewById(R.id.fieldEdicao);
        edicao.setText(cursor.getString(cursor.getColumnIndexOrThrow("edicao")));
        edicao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setEdicao(Integer.parseInt(edicao.getText().toString()));
                }
            }
        });

        paginas = (EditText)findViewById(R.id.fieldPaginas);
        paginas.setText(cursor.getString(cursor.getColumnIndexOrThrow("paginas")));
        paginas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setPaginas(Integer.parseInt(paginas.getText().toString()));
                }
            }
        });

        dtPublicacao = (EditText)findViewById(R.id.fieldDtPublicacao);
        dtPublicacao.setText(cursor.getString(cursor.getColumnIndexOrThrow("dtPublicacao")));
        dtPublicacao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setDtPublicacao(dtPublicacao.getText().toString());
                }
            }
        });

        isbn = (EditText)findViewById(R.id.fieldIsbn);
        isbn.setText(cursor.getString(cursor.getColumnIndexOrThrow("isbn")));
        isbn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setIsbn(isbn.getText().toString());
                }
            }
        });

        keywords = (EditText)findViewById(R.id.fieldKeywords);
        keywords.setText(cursor.getString(cursor.getColumnIndexOrThrow("keywords")));
        keywords.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setKeywords(keywords.getText().toString());
                }
            }
        });

        editora = (EditText)findViewById(R.id.fieldEditora);
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow("editora")));
        editora.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    obj.setEditora(editora.getText().toString());
                }
            }
        });

        alterar = (Button) findViewById(R.id.btnAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void clearFields(){
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
}
