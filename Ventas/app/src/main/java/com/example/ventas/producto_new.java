package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class producto_new extends AppCompatActivity {

    Button guardar,salir;
    EditText nombre,desc,cantidad,precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_new);

        guardar = (Button)findViewById(R.id.guardarProducto);
        salir = (Button)findViewById(R.id.salirNewProd);

        nombre = (EditText)findViewById(R.id.txtNombre);
        desc = (EditText)findViewById(R.id.txtDescripcion);
        cantidad = (EditText)findViewById(R.id.txtCant);
        precio = (EditText)findViewById(R.id.precioPnew);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<RespProd> call = wsHubService.saveProducto(nombre.getText().toString(), desc.getText().toString(), Integer.parseInt(cantidad.getText().toString()),Integer.parseInt(precio.getText().toString()));

                call.enqueue(new Callback<RespProd>() {
                    @Override
                    public void onResponse(Call<RespProd> call, Response<RespProd> response) {
                        if(response.isSuccessful()){
                            String result = response.body().getMessage();
                            Toast.makeText(producto_new.this,"Respuesta: " + result, Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(producto_new.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<RespProd> call, Throwable t) {
                        Toast.makeText(producto_new.this,"Error en el Web Service", Toast.LENGTH_SHORT).show();
                    }
                });
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
