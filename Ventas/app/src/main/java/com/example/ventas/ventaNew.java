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

public class ventaNew extends AppCompatActivity {

    Button buscarCliente,buscarProd,vender,salir;
    EditText ingEmail,obs,cantV,nombreProd;
    TextView nombreCliente,cantidadDisponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_new);

        buscarCliente = (Button)findViewById(R.id.regEmail);
        buscarProd = (Button)findViewById(R.id.regProd);
        vender = (Button)findViewById(R.id.guardarVenta);
        salir = (Button)findViewById(R.id.salirNewVenta);

        ingEmail = (EditText)findViewById(R.id.editText2);
        nombreCliente = (TextView) findViewById(R.id.nombreClienteV);
        cantidadDisponible = (TextView) findViewById(R.id.cantDisp);
        nombreProd = (EditText) findViewById(R.id.nomProdVent);
        cantV = (EditText) findViewById(R.id.cantVendida);
        obs = (EditText)findViewById(R.id.obsVenta);

        buscarCliente.setOnClickListener(new View.OnClickListener() {
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
                                nombreCliente.setText("Nombre del Cliente: "+c.getNombre()+" "+c.getApellido(),TextView.BufferType.NORMAL);
                            }catch (Exception e){}
                            Toast.makeText(ventaNew.this,"Respuesta: "+result+"\n", Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(ventaNew.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(ventaNew.this,"Error en el webService", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buscarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<RespProd> call = wsHubService.getProducto(nombreProd.getText().toString());
                call.enqueue(new Callback<RespProd>() {
                    @Override
                    public void onResponse(Call<RespProd> call, Response<RespProd> response) {
                        if(response.isSuccessful()){
                            RespProd r=response.body();
                            Producto c=r.getProducto();
                            String result = response.body().getMessage();
                            try {
                                cantidadDisponible.setText( c.getCantidad()+"--Precio/unidad: "+c.getPrecio() , TextView.BufferType.NORMAL);
                            }catch (Exception e){}
                            Toast.makeText(ventaNew.this,"Respuesta: " + result+"\n", Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(ventaNew.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<RespProd> call, Throwable t) {
                        Toast.makeText(ventaNew.this,"Error en el webService", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://192.168.100.11:3999/api/";
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                webserviceAPI wsHubService = retrofit.create(webserviceAPI.class);
                final Call<RespSale> call = wsHubService.saveVenta(nombreProd.getText().toString(), ingEmail.getText().toString(),obs.getText().toString(), Integer.parseInt(cantV.getText().toString()));

                call.enqueue(new Callback<RespSale>() {
                    @Override
                    public void onResponse(Call<RespSale> call, Response<RespSale> response) {
                        if(response.isSuccessful()){
                            String result = response.body().getMessage();
                            Producto p =response.body().getSale().getProducto();
                            cantidadDisponible.setText(p.getCantidad()+" precio/unidad: "+p.getPrecio(),TextView.BufferType.NORMAL);
                            Toast.makeText(ventaNew.this,"Respuesta: " + result, Toast.LENGTH_SHORT).show();
                        }else{ Toast.makeText(ventaNew.this,"Error al recuperar datos", Toast.LENGTH_SHORT).show(); }
                    }
                    @Override
                    public void onFailure(Call<RespSale> call, Throwable t) {
                        Toast.makeText(ventaNew.this,"Error en el Web Service", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(ventaNew.this,ventas_01.class);
                startActivity(i);*/
                finish();
            }
        });

    }
}
