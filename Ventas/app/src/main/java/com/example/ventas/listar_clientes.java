package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listar_clientes extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);

        //Llamada al api-rest
        String URL = "http://192.168.100.11:3999/api/";
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webserviceAPI serviceHubService = retrofit.create(webserviceAPI.class);
        final Call<ExCliente> call = serviceHubService.getClientes();
        call.enqueue(new Callback<ExCliente>() {
            @Override
            public void onResponse(Call<ExCliente> call, Response<ExCliente> response) {
                if(response.isSuccessful()){
                    lista=findViewById(R.id.listClientes);
                    ExCliente list = response.body();
                    List<String> nombres = list.getNombres();
                    List<String> apellidos = list.getApelldios();
                    List<String> emails = list.getEmails();
                    //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(listar_clientes.this,android.R.layout.simple_list_item_1, clientes);
                    adaptadorCliente adaptador = new adaptadorCliente(listar_clientes.this,R.layout.cliente_item,nombres,apellidos,emails);
                    lista.setAdapter(adaptador);
                    Toast.makeText(listar_clientes.this,"Datos recuperados satisfactoriamente  \n" , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(listar_clientes.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ExCliente> call, Throwable t) {
                Toast.makeText(listar_clientes.this,"Error en el Web Service"+t+" ---\n"+call, Toast.LENGTH_LONG).show();
            }
        });
    }
}
