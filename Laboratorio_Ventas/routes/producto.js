'use strict'

var express = require('express');
var ProductoController = require('../controllers/producto');

var router = express.Router();


//rutas de usuarios
router.post('/productos',ProductoController.save);
router.get('/productos',ProductoController.getProductos);
router.get('/productos/:id',ProductoController.getProducto);
router.put('/productos/:id',ProductoController.update);
router.delete('/productos/:id',ProductoController.delete);
router.post('/producto',ProductoController.getProductoByName);
//exportar el modulo
module.exports = router;