'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ClienteSchema = Schema({
    nombre: String,
    apellido: String,
    email: String,
    image: String
});

module.exports = mongoose.model('Cliente', ClienteSchema);
//en la tabla de datos se guardara con clientes