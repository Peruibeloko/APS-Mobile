package com.example.aps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.aps.dao.LivroDAO;
import com.example.crudud.R;

public class ConsultaDados extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dados);
        LivroDAO crud = new LivroDAO(getBaseContext());
            final Cursor cursor = crud.carregaDados();
        String[] nomeCampos = new String[]{CriaBanco.getID(), CriaBanco.getTITULO()};
        int[] idViews = new int[]{R.id.idLivro, R.id.nomeLivro};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_consulta_dados, cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);

                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.getID()));
                Intent intent = new Intent(ConsultaDados.this, AlteraDados.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
