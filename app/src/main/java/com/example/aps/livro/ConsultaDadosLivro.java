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

import java.lang.reflect.Field;

public class ConsultaDadosLivro extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dados_livro);
        LivroDAO crud = new LivroDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();
        String[] nomeCampos = new String[Livro.class.getDeclaredFields().length];

        int i = 0;
        for (Field f : Livro.class.getDeclaredFields()){

            nomeCampos[i] = f.getName();
            i++;
        }

        int[] idViews = {R.id.idLivro, R.id.nomeLivro};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.activity_consulta_dados_livro,
                cursor, nomeCampos, idViews, 0
        );

        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);

                String codigo = cursor.getString(
                        cursor.getColumnIndexOrThrow("id")
                );

                Intent intent = new Intent(ConsultaDadosLivro.this, AlteraDadosLivro.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
