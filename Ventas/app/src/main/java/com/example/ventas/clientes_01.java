package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clientes_01 extends AppCompatActivity {

    Button listar,nuevo,actualizar,borrar,salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_01);

        listar = (Button)findViewById(R.id.listarCliente);
        nuevo = (Button)findViewById(R.id.nuevoCliente);
        actualizar = (Button)findViewById(R.id.actualizarCliente);
        borrar = (Button)findViewById(R.id.borrarCliente);
        salir = (Button)findViewById(R.id.salirCliente);

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clientes_01.this,listar_clientes.class);
                startActivity(intent);
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clientes_01.this,cliente_nuevo.class);
                startActivity(intent);
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clientes_01.this,cliente_update.class);
                startActivity(intent);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clientes_01.this, cliente_delete.class );
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
