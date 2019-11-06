package com.example.aps.cliente;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aps.categoriaLeitor.AlteraCategoriaLeitor;
import com.example.aps.categoriaLeitor.CategoriaLeitor;
import com.example.aps.categoriaLeitor.CategoriaLeitorDAO;
import com.example.crudud.R;


public class AlteraCliente extends AppCompatActivity {

    // Lista de campos da tela
    EditText nome;
    Spinner codCat;
    EditText endereco;
    EditText celular;
    EditText email;
    EditText cpf;
    EditText dtNascimento;

    CatClienteAdapter spinAdapter;
    CategoriaLeitor[] catList;

    Button btnCodCat;
    Button alterar;
    Button deletar;
    Button consultar;
    Button cadastrar;
    Cursor cursorCliente;
    Cursor cursorCat;
    ClienteDAO daoCliente;
    CategoriaLeitorDAO daoCat;
    Cliente obj = new Cliente();;

    String codigo;

    boolean hasExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_cliente);
        codigo = this.getIntent().getStringExtra("_id");
        daoCliente = new ClienteDAO(getBaseContext());

        try {
            cursorCliente = daoCliente.carregaDadoById(Integer.parseInt(codigo));
            hasExtra = true;
        } catch (Exception e) {
            System.err.println("Deu merda carregando os dados: \n\n");
            e.printStackTrace();
        }

        nome = (EditText) findViewById(R.id.fieldNome);
        endereco = (EditText) findViewById(R.id.fieldEndereco);
        celular = (EditText) findViewById(R.id.fieldCelular);
        email = (EditText) findViewById(R.id.fieldEmail);
        cpf = (EditText) findViewById(R.id.fieldCpf);
        dtNascimento = (EditText) findViewById(R.id.fieldDtNascimento);

        if (hasExtra) {
            nome.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("nome")));
            endereco.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("endereco")));
            celular.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("celular")));
            email.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("email")));
            cpf.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("cpf")));
            dtNascimento.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("dtNascimento")));

            updateObject();
        }

        btnCodCat = (Button) findViewById(R.id.btnCodCat);
        btnCodCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraCliente.this, AlteraCategoriaLeitor.class);
                startActivity(intent);
            }
        });

        alterar = (Button) findViewById(R.id.btnAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                daoCliente.alteraRegistro(obj, Integer.parseInt(codigo));
            }
        });

        deletar = (Button) findViewById(R.id.btnDeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoCliente.deletaRegistro(Integer.parseInt(codigo));
                clearFields();
                alterar.setEnabled(hasExtra);
                deletar.setEnabled(hasExtra);
            }
        });

        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                daoCliente.insereDado(obj);
                clearFields();
            }
        });

        consultar = (Button) findViewById(R.id.btnConsultar);
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraCliente.this, ConsultaCliente.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        OnItemSelectedListener listener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoriaLeitor cat = (CategoriaLeitor) parent.getItemAtPosition(position);
                obj.setCodCat(cat.getCodigo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        try {
            codCat = (Spinner) findViewById(R.id.fieldCodCat);
            daoCat = new CategoriaLeitorDAO(getBaseContext());
            cursorCat = daoCat.carregaDados();
            catList = new CategoriaLeitor[cursorCat.getCount()];

            int i = 0;

            do {
                catList[i] = new CategoriaLeitor(
                        cursorCat.getInt(cursorCat.getColumnIndexOrThrow("_id")),
                        cursorCat.getInt(cursorCat.getColumnIndexOrThrow("prazoDev")),
                        cursorCat.getString(cursorCat.getColumnIndexOrThrow("descricao"))
                );
                i++;
            } while (cursorCat.moveToNext());

            spinAdapter = new CatClienteAdapter(AlteraCliente.this, android.R.layout.simple_spinner_item, catList);
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            codCat.setAdapter(spinAdapter);
            codCat.setOnItemSelectedListener(listener);

            if (hasExtra) {

                int pos = spinAdapter.getPosition(cursorCliente.getInt(cursorCliente.getColumnIndexOrThrow("codCat")));
                codCat.setSelection(pos);
            }

        } catch (Exception e) {

            System.err.println("Deu merda carregando as categorias: \n\n");
            e.printStackTrace();

        }

        alterar.setEnabled(hasExtra);
        deletar.setEnabled(hasExtra);
    }

    private void clearFields() {
        nome.setText("");
        endereco.setText("");
        celular.setText("");
        email.setText("");
        cpf.setText("");
        dtNascimento.setText("");
    }

    private void updateObject() {
        obj.setNome(nome.getText().toString());
        obj.setEndereco(endereco.getText().toString());
        obj.setCelular(celular.getText().toString());
        obj.setEmail(email.getText().toString());
        obj.setCpf(cpf.getText().toString());
        obj.setDtNascimento(dtNascimento.getText().toString());
    }
}
