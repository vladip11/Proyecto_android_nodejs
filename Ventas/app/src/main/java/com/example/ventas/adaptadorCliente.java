package com.example.ventas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adaptadorCliente extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> nombres;
    private List<String> apellidos;
    private List<String> emails;
    public adaptadorCliente(Context c,int layout,List<String> nombres,List<String> apellidos,List<String> emails){
        this.context=c;
        this.layout=layout;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.emails=emails;
    }
    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.cliente_item,null);
        try {
            //v = layoutInflater.inflate(R.layout.cliente_item,null);
            TextView nombre=v.findViewById(R.id.nombreItem);
            nombre.setText(nombres.get(position));
            TextView apellido=v.findViewById(R.id.apellidoItem);
            apellido.setText(apellidos.get(position));
            TextView correo=v.findViewById(R.id.correoItem);
            correo.setText(emails.get(position));
            ImageView img = v.findViewById(R.id.imageItem);
            img.setImageResource(R.drawable.img);
        }catch (Exception e){
        }
        return v;
    }
}
