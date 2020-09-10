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

public class producto_update extends AppCompatActivity {

    Button buscar,salir,actualizar;
    EditText nombre,desc,cant,ingProdName,precio;
    String idProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_update);

        buscar = (Button)findViewById(R.id.buscarProdByN);
        salir = (Button)findViewById(R.id.salirUpdateProd);
        actualizar = (Button)findViewById(R.id.actualizarProd);

        nombre = (EditText)findViewById(R.id.nameEditProd);
        desc = (EditText)findViewById(R.id.descEditProd);
        cant = (EditText)findViewById(R.id.cantProd);
        ingProdName = (EditText)findViewById(R.id.ingProductoName);
        precio = (EditText)findViewById(R.id.precioUpdate);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<RespProd> call = wsHubService.getProducto(ingProdName.getText().toString());
                call.enqueue(new Callback<RespProd>() {
                    @Override
                    public void onResponse(Call<RespProd> call, Response<RespProd> response) {
                        if(response.isSuccessful()){
                            RespProd r=response.body();
                            Producto c=r.getProducto();
                            String result = response.body().getMessage();
                            try {
                                idProd=c.getId();
                                nombre.setText(c.getNombre(),TextView.BufferType.NORMAL);
                                desc.setText(c.getDescripcion(),TextView.BufferType.NORMAL);
                                cant.setText( c.getCantidad()+"" , TextView.BufferType.NORMAL);
                                precio.setText(c.getPrecio()+"", TextView.BufferType.NORMAL);
                            }catch (Exception e){}
                            Toast.makeText(producto_update.this,"Respuesta: " + result+"\n", Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(producto_update.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<RespProd> call, Throwable t) {
                        Toast.makeText(producto_update.this,"Error en el webService", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = (EditText)findViewById(R.id.nameEditProd);
                desc = (EditText)findViewById(R.id.descEditProd);
                cant = (EditText)findViewById(R.id.cantProd);
                precio = (EditText)findViewById(R.id.precioUpdate);
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<RespProd> call = wsHubService.updateProducto(idProd,nombre.getText().toString(), desc.getText().toString(), Integer.parseInt(cant.getText().toString()), Integer.parseInt(precio.getText().toString()));
                call.enqueue(new Callback<RespProd>() {
                    @Override
                    public void onResponse(Call<RespProd> call, Response<RespProd> response) {
                        if(response.isSuccessful()){
                            String r = response.body().getMessage();
                            Toast.makeText(producto_update.this,"Respuesta: "+r, Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(producto_update.this,"Error al actualizar", Toast.LENGTH_SHORT).show();}
                    }
                    @Override
                    public void onFailure(Call<RespProd> call, Throwable t) {
                        Toast.makeText(producto_update.this,"Error en el webService", Toast.LENGTH_SHORT).show();
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
