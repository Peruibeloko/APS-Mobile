package com.example.aps.livro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aps.categoriaLivro.AlteraDadosCategoriaLivro;
import com.example.aps.categoriaLivro.CategoriaLivro;
import com.example.aps.categoriaLivro.CategoriaLivroDAO;
import com.example.crudud.R;

public class AlteraDadosLivro extends AppCompatActivity {

    // Lista de campos da tela
    EditText titulo;
    EditText autores;
    Spinner codCat;
    EditText edicao;
    EditText paginas;
    EditText dtPublicacao;
    EditText isbn;
    EditText keywords;
    EditText editora;

    CatLivroAdapter spinAdapter;
    CategoriaLivro[] catList;

    Button btnCodCat;
    Button alterar;
    Button deletar;
    Button consultar;
    Button cadastrar;
    Cursor cursorLivro;
    Cursor cursorCat;
    LivroDAO daoLivro;
    CategoriaLivroDAO daoCat;
    Livro obj = new Livro();

    String codigo;

    boolean hasExtra = false;

    @Override
    protected void onStart(){
        super.onStart();

        OnItemSelectedListener listener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoriaLivro cat = (CategoriaLivro) parent.getItemAtPosition(position);
                obj.setCodCat(cat.getCodigo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        try {
            codCat = (Spinner) findViewById(R.id.fieldCodCat);
            daoCat = new CategoriaLivroDAO(getBaseContext());
            cursorCat = daoCat.carregaDados();
            catList = new CategoriaLivro[cursorCat.getCount()];

            System.out.println("COMPRIMENTO: " + catList.length);

            int i = 0;

            do {
                catList[i] = new CategoriaLivro(
                        cursorCat.getInt(cursorCat.getColumnIndexOrThrow("_id")),
                        cursorCat.getInt(cursorCat.getColumnIndexOrThrow("prazoEmp")),
                        cursorCat.getString(cursorCat.getColumnIndexOrThrow("descricao")),
                        cursorCat.getDouble(cursorCat.getColumnIndexOrThrow("taxaAtraso"))
                );
                System.out.println(catList[i].getDescricao());
                i++;
            } while (cursorCat.moveToNext());

            spinAdapter = new CatLivroAdapter(AlteraDadosLivro.this, android.R.layout.simple_spinner_item, catList);
            codCat.setAdapter(spinAdapter);
            codCat.setOnItemSelectedListener(listener);

            if (hasExtra) {

                int pos = spinAdapter.getPosition(cursorLivro.getInt(cursorLivro.getColumnIndexOrThrow("codCat")));
                codCat.setSelection(pos);
            }

        } catch (Exception e) {

            System.err.println("Deu merda carregando as categorias: \n\n");
            e.printStackTrace();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_altera_dados_livro);
        codigo = this.getIntent().getStringExtra("_id");
        daoLivro = new LivroDAO(getBaseContext());

        try {
            cursorLivro = daoLivro.carregaDadoById(Integer.parseInt(codigo));
            hasExtra = true;
        } catch (Exception e) {
            System.err.println("Deu merda carregando os dados: \n\n");
            e.printStackTrace();
        }

        titulo = (EditText) findViewById(R.id.fieldTitulo);
        autores = (EditText) findViewById(R.id.fieldAutores);
        edicao = (EditText) findViewById(R.id.fieldEdicao);
        paginas = (EditText) findViewById(R.id.fieldPaginas);
        dtPublicacao = (EditText) findViewById(R.id.fieldDtPublicacao);
        isbn = (EditText) findViewById(R.id.fieldIsbn);
        keywords = (EditText) findViewById(R.id.fieldKeywords);
        editora = (EditText) findViewById(R.id.fieldEditora);

        if (hasExtra) {
            titulo.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("titulo")));
            autores.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("autores")));
            edicao.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("edicao")));
            paginas.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("paginas")));
            dtPublicacao.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("dtPublicacao")));
            isbn.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("isbn")));
            keywords.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("keywords")));
            editora.setText(cursorLivro.getString(cursorLivro.getColumnIndexOrThrow("editora")));

            updateObject();
        }


        btnCodCat = (Button) findViewById(R.id.btnCodCat);
        btnCodCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraDadosLivro.this, AlteraDadosCategoriaLivro.class);
                startActivity(intent);
            }
        });

        alterar = (Button) findViewById(R.id.btnAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                daoLivro.alteraRegistro(obj, Integer.parseInt(codigo));
            }
        });

        deletar = (Button) findViewById(R.id.btnDeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoLivro.deletaRegistro(Integer.parseInt(codigo));
                clearFields();
            }
        });

        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                daoLivro.insereDado(obj);
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
        obj.setEdicao(Integer.parseInt(edicao.getText().toString()));
        obj.setPaginas(Integer.parseInt(paginas.getText().toString()));
        obj.setDtPublicacao(dtPublicacao.getText().toString());
        obj.setIsbn(isbn.getText().toString());
        obj.setKeywords(keywords.getText().toString());
        obj.setEditora(editora.getText().toString());
    }
}
