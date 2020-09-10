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

public class producto_delete extends AppCompatActivity {

    Button borrar,buscar,salir;
    EditText ingNombre,nombre,desc,cant;
    String idProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_delete);

        borrar = (Button)findViewById(R.id.borrarProdDel);
        buscar = (Button)findViewById(R.id.buscarProdDel);
        salir = (Button)findViewById(R.id.salirDelProd);

        ingNombre = (EditText)findViewById(R.id.ingNameDelProd);
        nombre = (EditText)findViewById(R.id.nombreProdDel);
        desc = (EditText)findViewById(R.id.descProdDel);
        cant = (EditText)findViewById(R.id.cantProdDel);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<RespProd> call = wsHubService.getProducto(ingNombre.getText().toString());
                call.enqueue(new Callback<RespProd>() {
                    @Override
                    public void onResponse(Call<RespProd> call, Response<RespProd> response) {
                        if(response.isSuccessful()){
                            RespProd r=response.body();
                            Producto c=r.getProducto();
                            String result = response.body().getMessage();
                            try {
                                idProd=c.getId();
                                nombre.setText(c.getNombre(), TextView.BufferType.NORMAL);
                                desc.setText(c.getDescripcion(),TextView.BufferType.NORMAL);
                                cant.setText( c.getCantidad()+"" , TextView.BufferType.NORMAL);
                            }catch (Exception e){}
                            Toast.makeText(producto_delete.this,"Respuesta: " + result+"\n", Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(producto_delete.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<RespProd> call, Throwable t) {
                        Toast.makeText(producto_delete.this,"Error en el webService", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = (EditText)findViewById(R.id.nameEditProd);
                desc = (EditText)findViewById(R.id.descEditProd);
                cant = (EditText)findViewById(R.id.cantProd);
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<RespProd> call = wsHubService.deleteProducto(idProd);
                call.enqueue(new Callback<RespProd>() {
                    @Override
                    public void onResponse(Call<RespProd> call, Response<RespProd> response) {
                        if(response.isSuccessful()){
                            String r = response.body().getMessage();
                            Toast.makeText(producto_delete.this,"Respuesta: "+r, Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(producto_delete.this,"Error al actualizar", Toast.LENGTH_SHORT).show();}
                    }
                    @Override
                    public void onFailure(Call<RespProd> call, Throwable t) {
                        Toast.makeText(producto_delete.this,"Error en el webService", Toast.LENGTH_SHORT).show();
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
