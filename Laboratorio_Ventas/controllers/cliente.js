'use strict'

var validator = require('validator');
var Cliente = require('../models/cliente');

var controller = {
    save: function (req, res) {
        //Recoger los parametros de la peticion
        var params = req.body;
        //validar los datos
        var validate_nombre = !validator.isEmpty(params.nombre);
        var validate_apellido = !validator.isEmpty(params.apellido);
        var validate_email = !validator.isEmpty(params.email) && validator.isEmail(params.email);

        //console.log(validate_nombre,validate_apellido,validate_email);
        if (validate_apellido && validate_email && validate_nombre) {
            //crear objeto de usuario
            var cliente = new Cliente();
            //asignar valores al objeto
            cliente.nombre = params.nombre;
            cliente.apellido = params.apellido;
            cliente.email = params.email.toLowerCase();
            cliente.image = null;
            //comprobar si existe usuario
            Cliente.findOne({email: cliente.email}, (err, issetCliente) => {
                if (err) {
                    return res.status(500).send({
                        message: "error al comprobar duplicidad",
                        cliente: null
                    });
                }

                if (!issetCliente) {
                    //guardar usuarios
                    cliente.save((err, clienteStore) => {
                        if (err) {
                            return res.status(200).send({
                                message: "error el usuario non se guardo",
                                cliente: null
                            });
                        }
                        if (clienteStore) {
                            cliente.image = undefined; //hace q esa propiedad no se envie
                            return res.status(200).send({
                                message: "Los datos se guardaron correctamente",
                                cliente: clienteStore
                            });
                        } else {
                            return res.status(200).send({
                                message: "error al guardar los datos",
                                cliente: null
                            });
                        }
                    });
                } else {
                    return res.status(200).send({
                        message: "El cliente ya existe",
                        cliente: null
                    });
                }
            });
        } else {
            return res.status(200).send({
                message: "Datos ingresados de manera incorrecta",
                cliente: null
            });
        }
    },

    update: function (req, res) {
         // Recoger el id del articulo por la url
         // Recoger los datos que llegan por put
         var params = req.body;
         var clienteId = params.id;
        //validar los datos
         try{
            var validate_nombre = !validator.isEmpty(params.nombre);
            var validate_apellido = !validator.isEmpty(params.apellido);
            var validate_email = !validator.isEmpty(params.email) && validator.isEmail(params.email);
         }catch(err){
             return res.status(200).send({
                message: "error, datos mal introducidos ",
                cliente: null
             }); 
         }
 
         if(validate_nombre && validate_apellido && validate_email){
              // Find and update
              Cliente.findOneAndUpdate({_id: clienteId}, params, {new:true}, (err, clienteUpdated) => {
                 if(err){
                     return res.status(500).send({
                        message: "error al actualizar cliente",
                        cliente: null
                     });
                 }
 
                 if(!clienteUpdated){
                     return res.status(404).send({
                        message: "no existe el cliente",
                        cliente: null
                     });
                 }
 
                 return res.status(200).send({
                    message: "success, cliente actualizado",
                    cliente: clienteUpdated
                 });
              });
         }else{
              // Devolver respuesta
             return res.status(200).send({
                message: "error, datos mal introducidos",
                cliente: null
             });
         }
    },
    getClientes: (req, res) => {

        var query = Cliente.find({});

        // Find
        query.sort('-_id').exec((err, clientes) => {

            if (err) {
                return res.status(500).send({
                    status: 'error',
                    message: 'Error al devolver los clientes !!!'
                });
            }

            if (!clientes) {
                return res.status(404).send({
                    status: 'error',
                    message: 'No hay clientes para mostrar !!!'
                });
            }

            return res.status(200).send({
                clientes
            });

        });
    },
    getCliente: (req, res) => {

        // Recoger el id de la url
        var clienteId = req.params.id;

        // Buscar el articulo
        Cliente.findById(clienteId, (err, cliente) => {

            if (err || !cliente) {
                return res.status(404).send({
                    status: 'error',
                    message: 'No existe el cliente !!!'
                });
            } else {
                // Devolverlo en json
                return res.status(200).send({
                    status: 'success',
                    cliente
                });
            }
        });
    },
    delete: (req, res) => {
        // Recoger el id de la url
         var clienteId = req.params.id;

        // Find and delete
        Cliente.findOneAndDelete({_id: clienteId}, (err, clienteRemoved) => {
            if(err){
                return res.status(500).send({
                    message: "error ",
                    cliente: null
                });
            }

            if(!clienteRemoved){
                return res.status(404).send({
                    message: 'No se ha borrado el cliente, posiblemente no exista !!!',
                    cliente: null
                });
            }

            return res.status(200).send({
                message: 'Cliente borrado satisfactoriamente',
                cliente: clienteRemoved
            });

        }); 
    },
    getClienteByEmail: (req,res)=>{

        var clienteEmail = req.body.email;

        // Find and delete
        Cliente.findOne({email: clienteEmail}, (err, cliente_s) => {
            if (err) {
                return res.status(500).send({
                    message: "error al buscar cliente",
                    cliente: null
                });
            }

            if (!cliente_s) {
                return res.status(200).send({
                    message: "El cliente no existe",
                    cliente: cliente_s
                });
                
            } else {
                return res.status(200).send({
                    message: "cliente devuelto",
                    cliente: cliente_s
                });
            }
        });
    }

};

module.exports = controller;