package com.example.aps.cliente;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudud.R;


public class AlteraDadosCliente extends AppCompatActivity {

    // Lista de campos da tela
    EditText nome;
    EditText codCat;
    EditText endereco;
    EditText celular;
    EditText email;
    EditText cpf;
    EditText dtNascimento;

    TextView txtId;

    Button alterar;
    Button deletar;
    Button consultar;
    Button cadastrar;
    Cursor cursor;
    ClienteDAO dao;
    Cliente obj;
    String codigo;

    boolean hasExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_dados_cliente);
        codigo = this.getIntent().getStringExtra("_id");
        dao = new ClienteDAO(getBaseContext());
        obj = new Cliente();

        try {
            cursor = dao.carregaDadoById(Integer.parseInt(codigo));
            hasExtra = true;
        } catch (Exception e) {
            System.err.println("Deu merda carregando os dados: \n\n");
            e.printStackTrace();
        }

        nome = (EditText) findViewById(R.id.fieldNome);
        codCat = (EditText) findViewById(R.id.fieldCodCat);
        endereco = (EditText) findViewById(R.id.fieldEndereco);
        celular = (EditText) findViewById(R.id.fieldCelular);
        email = (EditText) findViewById(R.id.fieldEmail);
        cpf = (EditText) findViewById(R.id.fieldCpf);
        dtNascimento = (EditText) findViewById(R.id.fieldDtNascimento);
        txtId = (TextView) findViewById(R.id.txtId);

        if (hasExtra) {
            nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            codCat.setText(cursor.getString(cursor.getColumnIndexOrThrow("codCat")));
            endereco.setText(cursor.getString(cursor.getColumnIndexOrThrow("endereco")));
            celular.setText(cursor.getString(cursor.getColumnIndexOrThrow("celular")));
            email.setText(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            cpf.setText(cursor.getString(cursor.getColumnIndexOrThrow("cpf")));
            dtNascimento.setText(cursor.getString(cursor.getColumnIndexOrThrow("dtNascimento")));
            txtId.setText(cursor.getString(cursor.getColumnIndexOrThrow("_id")));

            updateObject();
        }

        alterar = (Button) findViewById(R.id.btnAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                dao.alteraRegistro(obj, Integer.parseInt(codigo));
            }
        });

        deletar = (Button) findViewById(R.id.btnDeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deletaRegistro(Integer.parseInt(codigo));
                clearFields();
            }
        });

        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateObject();
                dao.insereDado(obj);
                clearFields();
            }
        });

        consultar = (Button) findViewById(R.id.btnConsultar);
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraDadosCliente.this, ConsultaDadosCliente.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearFields() {
        nome.setText("");
        codCat.setText("");
        endereco.setText("");
        celular.setText("");
        email.setText("");
        cpf.setText("");
        dtNascimento.setText("");
    }

    private void updateObject() {
        obj.setNome(nome.getText().toString());
        obj.setCodCat(Integer.parseInt(codCat.getText().toString()));
        obj.setEndereco(endereco.getText().toString());
        obj.setCelular(celular.getText().toString());
        obj.setEmail(email.getText().toString());
        obj.setCpf(cpf.getText().toString());
        obj.setDtNascimento(dtNascimento.getText().toString());
    }
}
