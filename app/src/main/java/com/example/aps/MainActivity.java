package com.example.aps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aps.cliente.AlteraDadosCliente;
import com.example.aps.cliente.ConsultaDadosCliente;
import com.example.aps.livro.AlteraDadosLivro;
import com.example.aps.livro.ConsultaDadosLivro;
import com.example.aps.livro.LivroDAO;
import com.example.crudud.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); 
        setContentView(R.layout.activity_main);

        Button btnObras = (Button) findViewById(R.id.btnObras);
        Button btnLeitores = (Button) findViewById(R.id.btnLeitores);

        btnObras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlteraDadosLivro.class);
                startActivity(intent);
            }
        });

        btnLeitores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlteraDadosCliente.class);
                startActivity(intent);
            }
        });
    }
}