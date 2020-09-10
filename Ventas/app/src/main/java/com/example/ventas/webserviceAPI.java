package com.example.ventas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface webserviceAPI {
    @GET("clientes")
    Call<ExCliente> getClientes();

    @POST("cliente")
    @FormUrlEncoded
    Call<Respuesta> getCliente(@Field("email") String email);

    @POST("clientes")
    @FormUrlEncoded
    Call<Respuesta> enviarPost(@Field("nombre") String nombre, @Field("apellido") String apellido, @Field("email") String email);

    @PUT("clientes")
    @FormUrlEncoded
    Call<Respuesta> updatePost(@Field("id") String id,@Field("nombre") String nombre, @Field("apellido") String apellido, @Field("email") String email);

    @DELETE("clientes/{id}")
    Call<Respuesta> deleteCliente(@Path("id") String id);

    @POST("productos")
    @FormUrlEncoded
    Call<RespProd> saveProducto(@Field("nombre") String nombre, @Field("descripcion") String descripcion, @Field("cantidad") int cantidad, @Field("precio") int precio);

    @GET("productos")
    Call<RespProds> getProductos();

    @POST("producto")
    @FormUrlEncoded
    Call<RespProd> getProducto(@Field("nombre") String nombre);

    @PUT("productos/{id}")
    @FormUrlEncoded
    Call<RespProd> updateProducto(@Path("id") String id, @Field("nombre") String nombre, @Field("descripcion") String descripcion, @Field("cantidad") int cantidad, @Field("precio") int precio);

    @DELETE("productos/{id}")
    Call<RespProd> deleteProducto(@Path("id") String id);

    @POST("ventas")
    @FormUrlEncoded
    Call<RespSale> saveVenta(@Field("nombre") String nombre,@Field("email") String email,@Field("observacion") String observacion,@Field("cantidad") int cantidad);

    @GET("ventas")
    Call<Ventas> getVentas();
}