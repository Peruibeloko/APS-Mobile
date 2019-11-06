package com.example.aps.livro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudud.R;

public class ConsultaLivroEmprestado extends AppCompatActivity {

    ListView lista;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_livro_emprestado);

        LivroDAO crud = new LivroDAO(getBaseContext());
        cursor = crud.carregaLivrosEmprestados();

        String[] nomeCampos = {"_id", "titulo", "cliente"};
        int[] idViews = {R.id.livroId, R.id.livroNome, R.id.livroCliente};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.activity_consulta_livro_acervo,
                cursor, nomeCampos, idViews, 0
        );

        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);

                int _id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("cliente"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String dtSaida = cursor.getString(cursor.getColumnIndexOrThrow("dtSaida"));
                String dtRetorno = cursor.getString(cursor.getColumnIndexOrThrow("dtRetorno"));

                Intent intent = new Intent(ConsultaLivroEmprestado.this, DevolveLivro.class);

                intent.putExtra("id", _id);
                intent.putExtra("nome", nome);
                intent.putExtra("titulo", titulo);
                intent.putExtra("dtSaida", dtSaida);
                intent.putExtra("dtRetorno", dtRetorno);
                startActivity(intent);
                finish();
            }
        });
    }
}
