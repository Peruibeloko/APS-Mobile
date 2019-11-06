package com.example.aps.categoriaLivro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudud.R;


public class AlteraCategoriaLivro extends AppCompatActivity {

    // Lista de campos da tela
    EditText prazoEmp;
    EditText descricao;
    EditText taxaAtraso;

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
        setContentView(R.layout.activity_altera_categoria_livro);
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

        prazoEmp = (EditText) findViewById(R.id.fieldPrazoEmp);
        descricao = (EditText) findViewById(R.id.fieldDescricao);
        taxaAtraso = (EditText) findViewById(R.id.fieldTaxaAtraso);

        if (hasExtra) {
            prazoEmp.setText(cursor.getString(cursor.getColumnIndexOrThrow("prazoEmp")));
            descricao.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
            taxaAtraso.setText(cursor.getString(cursor.getColumnIndexOrThrow("taxaAtraso")));

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
                alterar.setEnabled(hasExtra);
                deletar.setEnabled(hasExtra);
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
                Intent intent = new Intent(AlteraCategoriaLivro.this, ConsultaCategoriaLivro.class);
                startActivity(intent);
                finish();
            }
        });

        alterar.setEnabled(hasExtra);
        deletar.setEnabled(hasExtra);
    }

    private void clearFields() {
        prazoEmp.setText("");
        descricao.setText("");
        taxaAtraso.setText("");
    }

    private void updateObject() {

        if (prazoEmp.getText().toString().equals("")) obj.setPrazoEmp(0);
        else obj.setPrazoEmp(Integer.parseInt(prazoEmp.getText().toString()));
        
        obj.setDescricao(descricao.getText().toString());

        if (taxaAtraso.getText().toString().equals("")) obj.setTaxaAtraso(0);
        else obj.setTaxaAtraso(Double.parseDouble(taxaAtraso.getText().toString()));
    }
}
