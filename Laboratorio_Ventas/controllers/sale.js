'use strict'

var validator = require('validator');
var Producto = require('../models/producto');
var Sale = require('../models/sale');
var Cliente = require('../models/cliente');

var controller = {

    prueba: (req,res)=>{
        var params = req.body;
        return res.status(200).send({
            message: 'este es el metodo prueba',
            params
        });
    },
    save:(req,res)=>{
        //recuperar datos
        var datos = req.body;
        var sale=new Sale();
        sale.observacion = datos.observacion;
        var newCantidad =datos.cantidad;
        
        Cliente.findOne({email: datos.email}, (err, cliente_s) => {
            sale.cliente = cliente_s;
            sale.nombreCliente = cliente_s.nombre+" "+cliente_s.apellido;
            Producto.findOne({nombre: datos.nombre}, (err, prod) => {

                
                prod.cantidad = prod.cantidad-newCantidad;
                Producto.findOneAndUpdate({_id: prod._id}, prod, {new: true}, (err, productoUpdated) => {
                    if (err) {
                        return res.status(200).send({
                            message: "error, al actualizar el producto ",
                            Sale: null
                        });
                    }else{
                        sale.producto = productoUpdated;
                        sale.cantidad_vendida = newCantidad;
                        sale.total = newCantidad*productoUpdated.precio;
                        sale.nombreProducto = productoUpdated.nombre;
                        sale.save((err, saleStore) => {
                            if (err) {
                                return res.status(200).send({
                                    message: "error, la venta no se guardo",
                                    Sale: null
                                });
                            }
                            if(saleStore) {
                                
                                
                                return res.status(200).send({
                                    message: "La venta se guardo correctamente",
                                    Sale: saleStore
                                });
                            } else {
                                return res.status(200).send({
                                    message: "error al guardar los datos",
                                    Sale: null
                                });
                            }
                        });
                    }
                });
            });
        });
    },
    getVentas:(req,res)=>{
        var query = Sale.find({});

        // Find
        query.sort('-_id').exec((err, sales) => {

            if (err) {
                return res.status(500).send({
                    message: 'Error al devolver las ventas !!!',
                    Sales: null
                });
            }

            if (!sales) {
                return res.status(404).send({
                    message: 'No hay ventas para mostrar !!!',
                    Sales: null
                });
            }

            return res.status(200).send({
                message: 'success',
                sales
            });

        });
    
    }
   
};

module.exports = controller;