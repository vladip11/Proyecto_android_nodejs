package com.example.ventas;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespProds {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("productos")
    @Expose
    private List<Producto> productos = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<String> devString(){
        List<String> r=new ArrayList<>();
        for (Producto p: productos){
            r.add(p.devCad());
        }
        return r;
    }
}
