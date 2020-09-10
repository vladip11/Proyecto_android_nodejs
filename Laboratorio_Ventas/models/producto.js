'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ProductoSchema = Schema({
    nombre: String,
    descripcion: String,
    cantidad: Number,
    precio: Number,
    date: { type: Date, default: Date.now}
});

module.exports = mongoose.model('Producto', ProductoSchema);
//en la tabla de datos se guardara con clientes