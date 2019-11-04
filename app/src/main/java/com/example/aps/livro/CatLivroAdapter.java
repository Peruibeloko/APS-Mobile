package com.example.aps.livro;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aps.categoriaLivro.CategoriaLivro;

public class CatLivroAdapter extends ArrayAdapter<CategoriaLivro> {

    private Context context;
    private CategoriaLivro[] myObjs;

    public CatLivroAdapter(Context context, int textViewResourceId,
                            CategoriaLivro[] myObjs) {
        super(context, textViewResourceId, myObjs);
        this.context = context;
        this.myObjs = myObjs;
    }

    public int getCount(){
        return myObjs.length;
    }

    public CategoriaLivro getItem(int position){
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
}
