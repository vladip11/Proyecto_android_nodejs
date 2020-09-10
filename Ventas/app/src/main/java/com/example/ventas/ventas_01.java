package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ventas_01 extends AppCompatActivity {

    Button vender,listar,salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_01);

        vender = (Button)findViewById(R.id.vender);
        listar = (Button)findViewById(R.id.listarVentas);
        salir = (Button)findViewById(R.id.salirV);

        vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ventas_01.this,ventaNew.class);
                startActivity(intent);
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ventas_01.this, venta_list.class);
                startActivity(intent);
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(ventas_01.this,MainActivity.class);
                startActivity(i);*/
                finish();
            }
        });
    }
}
