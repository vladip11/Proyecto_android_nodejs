package com.example.ventas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venta {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("observacion")
    @Expose
    private String observacion;
    @SerializedName("cliente")
    @Expose
    private String cliente;
    @SerializedName("nombreCliente")
    @Expose
    private String nombreCliente;
    @SerializedName("producto")
    @Expose
    private String producto;
    @SerializedName("cantidad_vendida")
    @Expose
    private Integer cantidadVendida;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("nombreProducto")
    @Expose
    private String nombreProducto;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }


    public String devCad(){
        return "ID venta: "+this.getId()+" \nNombre del Cliente: "+this.getNombreCliente()+" \nNombre del Producto: "+this.getNombreProducto()+
                "  \nObservaciones: "+this.getObservacion()+" \nCantidad Vendida: "+this.getCantidadVendida()+
                "  Total Pagado: "+this.getTotal()+"  \nFecha de venta: "+this.getDate();
    }

}