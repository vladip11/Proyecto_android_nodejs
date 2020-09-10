package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class producto_list extends AppCompatActivity {

    ListView listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_list);

        String URL = "http://192.168.100.11:3999/api/";
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webserviceAPI serviceHubService = retrofit.create(webserviceAPI.class);
        final Call<RespProds> call = serviceHubService.getProductos();
        call.enqueue(new Callback<RespProds>() {
            @Override
            public void onResponse(Call<RespProds> call, Response<RespProds> response) {
                if(response.isSuccessful()){
                    listaProductos = (ListView)findViewById(R.id.listaProductos);
                    RespProds resp = response.body();
                    String r = resp.getMessage();
                    List<String> productos=resp.devString();
                    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(producto_list.this,android.R.layout.simple_list_item_1, productos);
                    listaProductos.setAdapter(adaptador);
                    Toast.makeText(producto_list.this,"Respuesta: "+r , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(producto_list.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RespProds> call, Throwable t) {
                Toast.makeText(producto_list.this,"Error en el Web Service"+t+" ---\n"+call, Toast.LENGTH_LONG).show();
            }
        });

    }
}
