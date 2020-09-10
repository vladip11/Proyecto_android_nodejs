package com.example.ventas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespProd {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Producto")
    @Expose
    private Producto producto;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
