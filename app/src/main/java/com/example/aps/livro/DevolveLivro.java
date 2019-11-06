package com.example.aps.livro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudud.R;

public class DevolveLivro extends AppCompatActivity {

    TextView txtTitleVal;
    TextView txtNomeVal;
    TextView txtDtSaidaVal;
    TextView txtDtRetornoVal;
    LivroDAO dao;
    int id;
    Button btnDevolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devolve_livro);

        dao = new LivroDAO(getBaseContext());

        id = getIntent().getIntExtra("id", 0);
        String nome = getIntent().getStringExtra("nome");
        String titulo = getIntent().getStringExtra("titulo");
        String dtSaida = getIntent().getStringExtra("dtSaida");
        String dtRetorno = getIntent().getStringExtra("dtRetorno");

        btnDevolver = (Button) findViewById(R.id.btnDevolver);

        txtTitleVal = (TextView) findViewById(R.id.txtTitleVal);
        txtNomeVal = (TextView) findViewById(R.id.txtNomeVal);
        txtDtSaidaVal = (TextView) findViewById(R.id.txtDtSaidaVal);
        txtDtRetornoVal = (TextView) findViewById(R.id.txtDtRetornoVal);

        txtTitleVal.setText(nome);
        txtNomeVal.setText(titulo);
        txtDtSaidaVal.setText(dtSaida);
        txtDtRetornoVal.setText(dtRetorno);

        btnDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.devolveLivro(id);
                finish();
            }
        });
    }
}
