package com.example.aps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aps.livro.ConsultaDadosLivro;
import com.example.aps.livro.LivroDAO;
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

            }
        });
        botaoConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultaDadosLivro.class);
                startActivity(intent);
            }
        });
    }
}