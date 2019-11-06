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

public class ConsultaLivroAcervo extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_livro_acervo);
        LivroDAO crud = new LivroDAO(getBaseContext());
        final Cursor cursor;

        String[] filtro = this.getIntent().getStringArrayExtra("filtro");
        StringBuilder where = new StringBuilder();

        if (filtro != null) {

            if (!filtro[0].equals("")) {
                where.append("titulo LIKE '%").append(filtro[0]).append("%'");
                if (!filtro[1].equals(""))
                    where.append(" AND ");
            }
            if (!filtro[1].equals("")) {
                where.append("autores LIKE '%").append(filtro[1]).append("%'");
                if (!filtro[2].equals(""))
                    where.append(" AND ");
            }
            if (!filtro[2].equals("")) {
                where.append("editora LIKE '%").append(filtro[2]).append("%' ");
            }

            cursor = crud.carregaDados(where.toString());

        } else cursor = crud.carregaDados();

        String[] nomeCampos = {"_id", "titulo", "autores"};
        int[] idViews = {R.id.livroId, R.id.livroNome, R.id.livroAutores};
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

                int codigo = cursor.getInt(
                        cursor.getColumnIndexOrThrow("_id")
                );

                Intent intent = new Intent(ConsultaLivroAcervo.this, AlteraLivro.class);
                intent.putExtra("_id", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
