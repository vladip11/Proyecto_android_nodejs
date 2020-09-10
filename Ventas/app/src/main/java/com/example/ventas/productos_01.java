package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class productos_01 extends AppCompatActivity {

    Button nuevo,listar,actualizar,borrar,salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_01);

        nuevo = (Button)findViewById(R.id.nuevoProducto);
        listar = (Button)findViewById(R.id.listarProductos);
        actualizar = (Button)findViewById(R.id.actualizarProducto);
        borrar = (Button)findViewById(R.id.borrarProddel);
        salir = (Button)findViewById(R.id.salirProdDele);

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productos_01.this,producto_new.class);
                startActivity(intent);
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productos_01.this,producto_list.class);
                startActivity(intent);
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productos_01.this,producto_update.class);
                startActivity(intent);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productos_01.this,producto_delete.class);
                startActivity(intent);
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
