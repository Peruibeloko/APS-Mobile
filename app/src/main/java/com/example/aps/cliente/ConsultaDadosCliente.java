package com.example.aps.cliente;

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

public class ConsultaDadosCliente extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dados_cliente);
        ClienteDAO crud = new ClienteDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();
        String[] nomeCampos = new String[Cliente.class.getDeclaredFields().length];

        int i = 0;
        for (Field f : Cliente.class.getDeclaredFields()){

            nomeCampos[i] = f.getName();
            i++;
        }

        int[] idViews = {R.id.idCliente, R.id.nomeCliente};
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

                Intent intent = new Intent(ConsultaDadosCliente.this, AlteraDadosCliente.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
