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

public class cliente_nuevo extends AppCompatActivity {

    Button guardar, salir;
    EditText nombre,apellido,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_nuevo);

        guardar = (Button)findViewById(R.id.guardarCliente);
        salir = (Button)findViewById(R.id.SalirNewClient);
        nombre = (EditText)findViewById(R.id.editTextNombre);
        apellido = (EditText)findViewById(R.id.editTextApellido);
        email = (EditText)findViewById(R.id.editTextEmail);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<Respuesta> call = wsHubService.enviarPost(nombre.getText().toString(),apellido.getText().toString(),email.getText().toString());

                call.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            String result = response.body().getMessage();
                            Toast.makeText(cliente_nuevo.this,"Respuesta: " + result, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(cliente_nuevo.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(cliente_nuevo.this,"Error en el Web Service", Toast.LENGTH_SHORT).show();
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
