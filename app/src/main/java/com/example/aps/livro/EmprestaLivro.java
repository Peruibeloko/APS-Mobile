package com.example.aps.livro;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aps.cliente.Cliente;
import com.example.aps.cliente.ClienteDAO;
import com.example.crudud.R;

public class EmprestaLivro extends AppCompatActivity {

    TextView txtTituloEmp;
    EditText fieldFiltro;
    ListView lista;
    Button btnFiltro;
    int livroId;
    Cursor cursor;
    SimpleCursorAdapter adaptador;

    String livroTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresta_livro);

        txtTituloEmp = (TextView) findViewById(R.id.txtTituloEmp);
        fieldFiltro = (EditText) findViewById(R.id.fieldFiltro);
        lista = (ListView) findViewById(R.id.listClientes);
        btnFiltro = (Button) findViewById(R.id.btnFiltrar);

        livroId = getIntent().getIntExtra("livroId", 0);
        livroTitle = getIntent().getStringExtra("livroTitle");

        txtTituloEmp.setText(livroTitle);

        final LivroDAO daoLivro = new LivroDAO(getBaseContext());
        final ClienteDAO daoCliente = new ClienteDAO(getBaseContext());
        cursor = daoCliente.carregaDados();
        final String[] nomeCampos = {"_id", "nome", "codCat"};
        final int[] idViews = {R.id.clienteId, R.id.clienteNome};

        adaptador = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.activity_consulta_cliente,
                cursor, nomeCampos, idViews, 0
        );

        lista.setAdapter(adaptador);

        btnFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor newCursor = daoCliente.carregaDados(fieldFiltro.getText().toString());
                adaptador.swapCursor(newCursor);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);

                Cliente c = new Cliente();

                c.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                c.setCodCat(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));

                daoLivro.emprestaLivro(livroId, c);
                finish();
            }
        });
    }
}
