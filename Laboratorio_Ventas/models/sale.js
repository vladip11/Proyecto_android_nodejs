'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var SaleSchema = Schema({
    observacion : String,
    date: {type: Date, default: Date.now },
    cantidad_vendida: Number,
    cliente: { type: Schema.ObjectId, ref: 'Cliente'},
    producto: { type: Schema.ObjectId, ref: 'Producto'},
    nombreCliente: String,
    nombreProducto: String,
    total: Number
})

module.exports = mongoose.model('Sale', SaleSchema);