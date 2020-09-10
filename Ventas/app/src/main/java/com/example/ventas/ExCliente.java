package com.example.ventas;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExCliente {

        @SerializedName("clientes")
        @Expose
        private List<Cliente> clientes = null;

        public List<Cliente> getClientes() {
            return clientes;
        }

        public void setClientes(List<Cliente> clientes) {
            this.clientes = clientes;
        }

        public List<String> getNombres(){
            List<String> r=new ArrayList<>();
            for (Cliente c:clientes){
                r.add("Nombre: "+c.getNombre());
            }
            return r;
        }
        public List<String> getApelldios(){
            List<String> r=new ArrayList<>();
            for (Cliente c:clientes){
                r.add("Apelldio: "+c.getApellido());
            }
            return r;
        }
        public List<String> getEmails(){
            List<String> r=new ArrayList<>();
            for (Cliente c:clientes){
                r.add("Correo Email: "+c.getEmail());
            }
            return r;
        }

}
