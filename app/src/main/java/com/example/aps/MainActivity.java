package com.example.aps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aps.cliente.AlteraCliente;
import com.example.aps.livro.AlteraLivro;
import com.example.crudud.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnObras = (Button) findViewById(R.id.btnObras);
        Button btnLeitores = (Button) findViewById(R.id.btnLeitores);

        btnObras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlteraLivro.class);
                startActivity(intent);
            }
        });

        btnLeitores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlteraCliente.class);
                startActivity(intent);
            }
        });
    }
}