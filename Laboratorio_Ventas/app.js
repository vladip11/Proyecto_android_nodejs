'use strict'

//Requires
var express = require('express')
var bodyParser = require('body-parser')

//Ejecutar express
var app = express();

//Cargar archivos de rutas
var cliente_routes = require('./routes/cliente');
var producto_routes = require('./routes/producto');
var sale_routes = require('./routes/sale');

//añadir middlewares
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

//configurar el cors

//Reescribir rutas
app.use('/api',cliente_routes);
app.use('/api',producto_routes);
app.use('/api',sale_routes);

//Exportar el modulo
module.exports = app;