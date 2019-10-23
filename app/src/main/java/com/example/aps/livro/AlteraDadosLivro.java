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

    Button alterar;
    Button deletar;
    Cursor cursor;
    LivroDAO dao;
    Livro lv;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_livro);
        codigo = this.getIntent().getStringExtra("codigo");
        dao = new LivroDAO(getBaseContext());
        cursor = dao.carregaDadoById(Integer.parseInt(codigo));
        lv = new Livro();

        // Um pra cada campo
        titulo = (EditText)findViewById(R.id.fieldTitulo);
        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        titulo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lv.setTitulo(titulo.getText().toString());
                }
            }
        });

        alterar = (Button) findViewById(R.id.btnAlterar);
        deletar = (Button) findViewById(R.id.btnDeletar);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.alteraRegistro(lv, Integer.parseInt(codigo));

                Intent intent = new Intent(AlteraDadosLivro.this, ConsultaDadosLivro.class);
                startActivity(intent);
                finish();
            }
        });

        //Código Antes
        deletar = (Button)findViewById(R.id.btnDeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlteraDadosLivro.this, ConsultaDadosLivro.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
