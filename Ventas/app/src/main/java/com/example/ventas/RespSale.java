package com.example.ventas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespSale {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Sale")
    @Expose
    private Sale sale;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

}
