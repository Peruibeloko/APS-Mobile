package com.example.aps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aps.dao.LivroDAO;
import com.example.crudud.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); 
        setContentView(R.layout.activity_main);
        Button botaoRegistro = (Button) findViewById(R.id.btnCadastrar);
        Button botaoConsulta = (Button) findViewById(R.id.btnConsultar);
        botaoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivroDAO crud = new LivroDAO(getBaseContext());
                EditText titulo = (EditText) findViewById(R.id.fieldTitulo);
                EditText autor = (EditText) findViewById((R.id.fieldAutor));
                EditText editora = (EditText) findViewById(R.id.fieldEditora);
                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraString = editora.getText().toString();
                String resultado;
                resultado = crud.insereDado(tituloString, autorString, editoraString);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                titulo.setText("");
                autor.setText("");
                editora.setText("");
            }
        });
        botaoConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultaDados.class);
                startActivity(intent);
            }
        });
    }
}