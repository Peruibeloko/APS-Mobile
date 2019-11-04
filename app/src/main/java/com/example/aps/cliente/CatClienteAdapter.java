package com.example.aps.cliente;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aps.categoriaLeitor.CategoriaLeitor;
import com.example.aps.categoriaLivro.CategoriaLivro;

public class CatClienteAdapter extends ArrayAdapter<CategoriaLeitor> {

    private Context context;
    private CategoriaLeitor[] myObjs;

    public CatClienteAdapter(Context context, int textViewResourceId,
                             CategoriaLeitor[] myObjs) {
        super(context, textViewResourceId, myObjs);
        this.context = context;
        this.myObjs = myObjs;
    }

    public int getCount(){
        return myObjs.length;
    }

    public CategoriaLeitor getItem(int position){
        return myObjs[position];
    }

    public long getItemId(int position){
        return myObjs[position].getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        if(myObjs.length > 0)
            label.setText(myObjs[position].getDescricao());
        else
            label.setText("Nenhuma categoria");
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        if(myObjs.length > 0)
            label.setText(myObjs[position].getDescricao());
        else
            label.setText("Nenhuma categoria");
        return label;
    }

    public int getPosition(int cod) {
        int i = 0;
        for (CategoriaLeitor cat : myObjs) {
            if (cat.getCodigo() == cod)
                return i;
            i++;
        }
        return -1;
    }
}
