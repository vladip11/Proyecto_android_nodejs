'use strict'

var express = require('express');
var SaleController = require('../controllers/sale');

var router = express.Router();

//rutas de usuarios
router.post('/prueba',SaleController.prueba);
router.post('/ventas',SaleController.save);
router.get('/ventas',SaleController.getVentas);



//exportar el modulo
module.exports = router;