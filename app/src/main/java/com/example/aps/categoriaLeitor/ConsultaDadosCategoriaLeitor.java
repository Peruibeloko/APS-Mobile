package com.example.aps.categoriaLeitor;

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

public class ConsultaDadosCategoriaLeitor extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dados_categoria_leitor);
        CategoriaLeitorDAO crud = new CategoriaLeitorDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();
        String[] nomeCampos = new String[CategoriaLeitor.class.getDeclaredFields().length];

        int i = 0;
        for (Field f : CategoriaLeitor.class.getDeclaredFields()){

            nomeCampos[i] = f.getName();
            i++;
        }

        int[] idViews = {R.id.idCategoriaLeitor, R.id.nomeCategoriaLeitor};
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

                Intent intent = new Intent(ConsultaDadosCategoriaLeitor.this, AlteraDadosCategoriaLeitor.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
