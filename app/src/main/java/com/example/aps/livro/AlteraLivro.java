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

import com.example.aps.categoriaLivro.AlteraCategoriaLivro;
import com.example.aps.categoriaLivro.CategoriaLivro;
import com.example.aps.categoriaLivro.CategoriaLivroDAO;
import com.example.crudud.R;

public class AlteraLivro extends AppCompatActivity {

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
    Button emprestar;
    Cursor cursorLivro;
    Cursor cursorCat;
    LivroDAO daoLivro;
    CategoriaLivroDAO daoCat;
    Livro obj = new Livro();

    int codigo;

    boolean hasExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_altera_livro);
        codigo = this.getIntent().getIntExtra("_id", 0);
        daoLivro = new LivroDAO(getBaseContext());

        hasExtra = (codigo != 0);

        titulo = (EditText) findViewById(R.id.fieldTitulo);
        autores = (EditText) findViewById(R.id.fieldAutores);
        edicao = (EditText) findViewById(R.id.fieldEdicao);
        paginas = (EditText) findViewById(R.id.fieldPaginas);
        dtPublicacao = (EditText) findViewById(R.id.fieldDtPublicacao);
        isbn = (EditText) findViewById(R.id.fieldIsbn);
        keywords = (EditText) findViewById(R.id.fieldKeywords);
        editora = (EditText) findViewById(R.id.fieldEditora);

        btnCodCat = (Button) findViewById(R.id.btnCodCat);
        alterar = (Button) findViewById(R.id.btnAlterar);
        deletar = (Button) findViewById(R.id.btnDeletar);
        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        consultar = (Button) findViewById(R.id.btnConsultar);
        emprestar = (Button) findViewById(R.id.btnEmprestar);

        if (hasExtra) {

            cursorLivro = daoLivro.carregaDadoById(codigo);

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

        emprestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraLivro.this, EmprestaLivro.class);
                intent.putExtra("livroId", codigo);
                intent.putExtra("livroTitle", titulo.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        btnCodCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraLivro.this, AlteraCategoriaLivro.class);
                startActivity(intent);
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                daoLivro.alteraRegistro(obj, codigo);
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoLivro.deletaRegistro(codigo);
                clearFields();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                daoLivro.insereDado(obj);
                clearFields();
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraLivro.this, FiltraConsultaLivro.class);
                startActivity(intent);
                finish();
            }
        });
    }

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

            int i = 0;

            do {
                catList[i] = new CategoriaLivro(
                        cursorCat.getInt(cursorCat.getColumnIndexOrThrow("_id")),
                        cursorCat.getInt(cursorCat.getColumnIndexOrThrow("prazoEmp")),
                        cursorCat.getString(cursorCat.getColumnIndexOrThrow("descricao")),
                        cursorCat.getDouble(cursorCat.getColumnIndexOrThrow("taxaAtraso"))
                );
                i++;
            } while (cursorCat.moveToNext());

            spinAdapter = new CatLivroAdapter(AlteraLivro.this, android.R.layout.simple_spinner_item, catList);
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

        alterar.setEnabled(hasExtra);
        deletar.setEnabled(hasExtra);
        emprestar.setEnabled(hasExtra);
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

        if (edicao.getText().toString().equals("")) obj.setEdicao(0);
        else obj.setEdicao(Integer.parseInt(edicao.getText().toString()));

        if (paginas.getText().toString().equals("")) obj.setPaginas(0);
        else obj.setPaginas(Integer.parseInt(paginas.getText().toString()));

        obj.setDtPublicacao(dtPublicacao.getText().toString());
        obj.setIsbn(isbn.getText().toString());
        obj.setKeywords(keywords.getText().toString());
        obj.setEditora(editora.getText().toString());
    }
}
