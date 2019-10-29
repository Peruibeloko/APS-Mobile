package com.example.aps.categoriaLivro;

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

public class ConsultaDadosCategoriaLivro extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dados_categoria_livro);
        CategoriaLivroDAO crud = new CategoriaLivroDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();
        String[] nomeCampos = new String[CategoriaLivro.class.getDeclaredFields().length];

        int i = 0;
        for (Field f : CategoriaLivro.class.getDeclaredFields()){

            nomeCampos[i] = f.getName();
            i++;
        }

        int[] idViews = {R.id.idCategoriaLivro, R.id.nomeCategoriaLivro};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.activity_consulta_dados_cliente,
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

                Intent intent = new Intent(ConsultaDadosCategoriaLivro.this, AlteraDadosCategoriaLivro.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
