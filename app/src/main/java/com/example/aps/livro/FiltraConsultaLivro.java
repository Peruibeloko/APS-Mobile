package com.example.aps.livro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudud.R;

public class FiltraConsultaLivro extends AppCompatActivity {

    EditText titulo;
    EditText autores;
    EditText editora;

    Button btnFiltrar;
    Button btnEmprestimos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtra_consulta_livro);

        titulo = (EditText) findViewById(R.id.fieldTitulo);
        autores = (EditText) findViewById(R.id.fieldAutores);
        editora = (EditText) findViewById(R.id.fieldEditora);

        btnFiltrar = (Button) findViewById(R.id.btnFiltrar);
        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] filtro = {
                        titulo.getText().toString(),
                        autores.getText().toString(),
                        editora.getText().toString()
                };

                Intent intent = new Intent(FiltraConsultaLivro.this, ConsultaLivroAcervo.class);
                intent.putExtra("filtro", filtro);
                startActivity(intent);
            }
        });

        btnEmprestimos = (Button) findViewById(R.id.btnEmprestados);
        btnEmprestimos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiltraConsultaLivro.this, ConsultaLivroEmprestado.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
