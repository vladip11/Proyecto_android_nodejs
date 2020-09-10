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

public class venta_list extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_list);

        String URL = "http://192.168.100.11:3999/api/";
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webserviceAPI serviceHubService = retrofit.create(webserviceAPI.class);
        final Call<Ventas> call = serviceHubService.getVentas();
        call.enqueue(new Callback<Ventas>() {
            @Override
            public void onResponse(Call<Ventas> call, Response<Ventas> response) {
                if(response.isSuccessful()){
                    lista=findViewById(R.id.listVentas);
                    Ventas list = response.body();
                    List<String> ventas=list.devString();
                    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(venta_list.this,android.R.layout.simple_list_item_1, ventas);
                    lista.setAdapter(adaptador);
                    Toast.makeText(venta_list.this,"Datos recuperados satisfactoriamente  \n" , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(venta_list.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Ventas> call, Throwable t) {
                Toast.makeText(venta_list.this,"Error en el Web Service"+t+" ---\n"+call, Toast.LENGTH_LONG).show();
            }
        });
    }
}
