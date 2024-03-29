package com.example.aps.categoriaLeitor;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudud.R;


public class AlteraCategoriaLeitor extends AppCompatActivity {

    // Lista de campos da tela
    EditText prazoDev;
    EditText descricao;

    Button alterar;
    Button deletar;
    Button consultar;
    Button cadastrar;
    Cursor cursor;
    CategoriaLeitorDAO dao;
    CategoriaLeitor obj;
    String codigo;

    boolean hasExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_categoria_leitor);
        codigo = this.getIntent().getStringExtra("_id");
        dao = new CategoriaLeitorDAO(getBaseContext());
        obj = new CategoriaLeitor();

        try {
            cursor = dao.carregaDadoById(Integer.parseInt(codigo));
            hasExtra = true;
        } catch (Exception e) {
            System.err.println("Deu merda carregando os dados: \n\n");
            e.printStackTrace();
        }

        prazoDev = (EditText) findViewById(R.id.fieldPrazoDev);
        descricao = (EditText) findViewById(R.id.fieldDescricao);

        if (hasExtra){
            prazoDev.setText(cursor.getString(cursor.getColumnIndexOrThrow("prazoDev")));
            descricao.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));

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
                Intent intent = new Intent(AlteraCategoriaLeitor.this, ConsultaCategoriaLeitor.class);
                startActivity(intent);
                finish();
            }
        });

        alterar.setEnabled(hasExtra);
        deletar.setEnabled(hasExtra);
    }

    private void clearFields() {
        prazoDev.setText("");
        descricao.setText("");
    }

    private void updateObject(){

        if (prazoDev.getText().toString().equals("")) obj.setPrazoDev(0);
        else obj.setPrazoDev(Integer.parseInt(prazoDev.getText().toString()));

        obj.setDescricao(descricao.getText().toString());
    }
}
