package com.example.aps.categoriaLivro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudud.R;


public class AlteraDadosCategoriaLivro extends AppCompatActivity {

    // Lista de campos da tela
    EditText prazoEmp;
    EditText descricao;
    EditText taxaAtraso;

    TextView txtId;

    Button alterar;
    Button deletar;
    Button cadastrar;
    Button consultar;
    Cursor cursor;
    CategoriaLivroDAO dao;
    CategoriaLivro obj;
    String codigo;

    boolean hasExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_categoria_livro);
        codigo = this.getIntent().getStringExtra("_id");
        dao = new CategoriaLivroDAO(getBaseContext());
        obj = new CategoriaLivro();

        try {
            cursor = dao.carregaDadoById(Integer.parseInt(codigo));
            hasExtra = true;
        } catch (Exception e) {
            System.err.println("Deu merda carregando os dados: \n\n");
            e.printStackTrace();
        }

        prazoEmp = (EditText) findViewById(R.id.fieldPrazoDev);
        descricao = (EditText) findViewById(R.id.fieldDescricao);
        taxaAtraso = (EditText) findViewById(R.id.fieldTaxaAtraso);
        txtId = (TextView) findViewById(R.id.txtId);

        if (hasExtra) {
            prazoEmp.setText(cursor.getString(cursor.getColumnIndexOrThrow("prazoEmp")));
            descricao.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
            taxaAtraso.setText(cursor.getString(cursor.getColumnIndexOrThrow("taxaAtraso")));
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
                Intent intent = new Intent(AlteraDadosCategoriaLivro.this, ConsultaDadosCategoriaLivro.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearFields() {
        prazoEmp.setText("");
        descricao.setText("");
        taxaAtraso.setText("");
    }

    private void updateObject() {
        obj.setPrazoEmp(Integer.parseInt(prazoEmp.getText().toString()));
        obj.setDescricao(descricao.getText().toString());
        obj.setTaxaAtraso(Double.parseDouble(taxaAtraso.getText().toString()));
    }
}
