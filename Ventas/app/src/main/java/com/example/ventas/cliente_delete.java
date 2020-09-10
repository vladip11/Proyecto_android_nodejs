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

public class cliente_delete extends AppCompatActivity {

    EditText ingEmail,id,nombre,apellido,email;
    Button buscar,borrar,salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_delete);

        buscar = (Button)findViewById(R.id.buscarDel);
        borrar = (Button)findViewById(R.id.borrarDel);
        salir = (Button)findViewById(R.id.salirDelProd);

        ingEmail = (EditText)findViewById((R.id.ingEmailDel));
        id = (EditText)findViewById((R.id.idDel));
        nombre = (EditText)findViewById((R.id.nombreDel));
        apellido = (EditText)findViewById((R.id.apellidoDel));
        email = (EditText)findViewById((R.id.emailDel));

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
                                id.setText(c.getId(), TextView.BufferType.NORMAL);
                                nombre.setText(c.getNombre(),TextView.BufferType.NORMAL);
                                apellido.setText(c.getApellido(),TextView.BufferType.NORMAL);
                                email.setText( c.getEmail() , TextView.BufferType.NORMAL);
                            }catch (Exception e){}
                            Toast.makeText(cliente_delete.this,"Respuesta: "+result+"\n", Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(cliente_delete.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(cliente_delete.this,"Error en el webService", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = (EditText)findViewById((R.id.idDel));
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<Respuesta> call = wsHubService.deleteCliente(id.getText().toString()+"");
                call.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            String r = response.body().getMessage();
                            Toast.makeText(cliente_delete.this,"Respuesta: "+r, Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(cliente_delete.this,"Error al borrar cliente", Toast.LENGTH_SHORT).show();}
                    }
                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(cliente_delete.this,"Error en el webService", Toast.LENGTH_SHORT).show();
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
