package com.example.ventas;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ventas {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sales")
    @Expose
    private List<Venta> sales = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Venta> getSales() {
        return sales;
    }

    public void setSales(List<Venta> sales) {
        this.sales = sales;
    }

    public List<String> devString(){
        List<String> r=new ArrayList<>();
        for (Venta v:sales){
            r.add(v.devCad());
        }
        return r;
    }

}