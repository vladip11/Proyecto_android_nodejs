'use strict'

var validator = require('validator');
var Producto = require('../models/producto');

var controller = {

    save: function (req, res) {
        //Recoger los parametros de la peticion
        var params = req.body;
        //validar los datos
        try {
            var validate_nombre = !validator.isEmpty(params.nombre);
            var validate_descipcion = !validator.isEmpty(params.descripcion);
            var validate_cantidad = !validator.isEmpty(params.cantidad);
            var validate_precio = !validator.isEmpty(params.precio);
        } catch (err) {
            return res.status(200).send({
                status: 'error',
                message: 'Error al llenar los datos !!!'
            });
        }

        if (validate_cantidad && validate_descipcion && validate_nombre && validate_precio) {
            //crear objeto de usuario
            var producto = new Producto();
            //asignar valores al objeto
            producto.nombre = params.nombre;
            producto.descripcion = params.descripcion;
            producto.cantidad = params.cantidad;
            producto.precio = params.precio;
            //guardar usuarios
            producto.save((err, productoStore) => {
                if (err) {
                    return res.status(200).send({
                        message: "error, el producto no se guardo",
                        producto: null
                    });
                }
                if (productoStore) {
                    return res.status(200).send({
                        message: "Los datos se guardaron correctamente",
                        Producto: productoStore
                    });
                } else {
                    return res.status(200).send({
                        message: "error al guardar los datos",
                        producto: null

                    });
                }
            });
        } else {
            return res.status(200).send({
                message: "Datos ingresados de manera incorrecta",
                producto: null
            });
        }
    },

    update: function (req, res) {
        // Recoger el id del articulo por la url
        var productoId = req.params.id;

        // Recoger los datos que llegan por put
        var params = req.body;
        //validar los datos
        try {
            var validate_nombre = !validator.isEmpty(params.nombre);
            var validate_descipcion = !validator.isEmpty(params.descripcion);
            var validate_cantidad = !validator.isEmpty(params.cantidad);
            var validate_precio = !validator.isEmpty(params.precio);
        } catch (err) {
            return res.status(200).send({
                message: 'Error al llenar los datos !!!',
                Producto: null
            });
        }

        if (validate_nombre && validate_descipcion && validate_cantidad && validate_precio) {
            // Find and update
            Producto.findOneAndUpdate({_id: productoId}, params, {new: true}, (err, productoUpdated) => {
                if (err) {
                    return res.status(500).send({
                        message: 'Error al actualizar !!!',
                        Producto: null
                    });
                }
                if (!productoUpdated) {
                    return res.status(404).send({
                        message: 'No existe el producto !!!',
                        Producto: null
                    });
                }
                return res.status(200).send({
                    message: 'success',
                    Producto: productoUpdated
                });
            });
        } else {
            // Devolver respuesta
            return res.status(200).send({
                message: 'La validación no es correcta !!!',
                Producto: null
            });
        }
    },
    getProductos: (req, res) => {

        var query = Producto.find({});

        // Find
        query.sort('-_id').exec((err, productos) => {

            if (err) {
                return res.status(500).send({
                    message: 'Error al devolver los productos !!!',
                    Productos: null
                });
            }

            if (!productos) {
                return res.status(404).send({
                    message: 'No hay productos para mostrar !!!',
                    Productos: null
                });
            }

            return res.status(200).send({
                message: 'success',
                productos
            });

        });
    },
    getProducto: (req, res) => {

        // Recoger el id de la url
        var productoId = req.params.id;

        // Buscar el articulo
        Producto.findById(productoId, (err, producto) => {

            if (err || !producto) {
                return res.status(404).send({
                    message: 'No existe el cliente !!!',
                    Producto: null
                });
            } else {
                // Devolverlo en json
                return res.status(200).send({
                    message: 'success',
                    Producto: producto
                });
            }
        });
    },
    delete: (req, res) => {
        // Recoger el id de la url
        var productoId = req.params.id;
        // Find and delete
        Producto.findOneAndDelete({_id: productoId}, (err, productoRemoved) => {
            if (err) {
                return res.status(500).send({
                    message: 'Error al borrar !!!',
                    Producto: null
                });
            }

            if (!productoRemoved) {
                return res.status(404).send({
                    message: 'No se ha borrado el producto, posiblemente no exista !!!',
                    Producto: null
                });
            }

            return res.status(200).send({
                message: 'success',
                producto: productoRemoved
            });

        });
    },
    getProductoByName: (req,res)=>{

        var productoNombre = req.body.nombre;

        // Find and delete
        Producto.findOne({nombre: productoNombre}, (err, prod) => {
            if (err) {
                return res.status(500).send({
                    message: "error al buscar cliente",
                    Producto: null
                });
            }

            if (!prod) {
                return res.status(200).send({
                    message: "El producto no existe",
                    Producto: prod
                });
                
            } else {
                return res.status(200).send({
                    message: "Producto devuelto",
                    Producto: prod
                });
            }
        });
    }

};

module.exports = controller;