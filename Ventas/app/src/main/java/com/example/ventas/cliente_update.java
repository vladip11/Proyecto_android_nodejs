package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class cliente_update extends AppCompatActivity {

    Button buscar,salir,actualizar;
    EditText ingEmail,nombre,apellido,email;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_update);

        buscar = (Button)findViewById(R.id.buttonBuscar);
        actualizar = (Button)findViewById(R.id.actualizar);
        salir = (Button)findViewById(R.id.borrarProddel);
        ingEmail = (EditText)findViewById(R.id.ingTextEmail);

        apellido = (EditText)findViewById(R.id.editApellido);
        email = (EditText)findViewById(R.id.editCorreoEmail);
        nombre = (EditText)findViewById(R.id.editNombre);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<Respuesta> call = wsHubService.getCliente(ingEmail.getText().toString());
                call.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            Respuesta r=response.body();
                            Cliente c=r.getCliente();
                            String result = response.body().getMessage();
                            try {
                                id= c.getId();
                                nombre.setText(c.getNombre(),TextView.BufferType.NORMAL);
                                apellido.setText(c.getApellido(),TextView.BufferType.NORMAL);
                                email.setText( c.getEmail() , TextView.BufferType.NORMAL);
                            }catch (Exception e){}
                            Toast.makeText(cliente_update.this,"Respuesta: " + result+"\n", Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(cliente_update.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(cliente_update.this,"Error en el webService", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apellido = (EditText)findViewById(R.id.editApellido);
                email = (EditText)findViewById(R.id.editCorreoEmail);
                nombre = (EditText)findViewById(R.id.editNombre);
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<Respuesta> call = wsHubService.updatePost(id,nombre.getText().toString(),apellido.getText().toString(),email.getText().toString());
                call.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            String r = response.body().getMessage();
                            Toast.makeText(cliente_update.this,"Respuesta: "+r, Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(cliente_update.this,"Error al actualizar", Toast.LENGTH_SHORT).show();}
                    }
                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(cliente_update.this,"Error en el webService", Toast.LENGTH_SHORT).show();
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
