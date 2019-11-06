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

public class ConsultaCliente extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);
        ClienteDAO crud = new ClienteDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();
        String[] nomeCampos = {"_id", "nome"};

        int[] idViews = {R.id.clienteId, R.id.clienteNome};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.activity_consulta_cliente,
                cursor, nomeCampos, idViews, 0
        );

        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);

                String codigo = cursor.getString(
                        cursor.getColumnIndexOrThrow("_id")
                );

                Intent intent = new Intent(ConsultaCliente.this, AlteraCliente.class);
                intent.putExtra("_id", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
