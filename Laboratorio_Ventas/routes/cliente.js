'use strict'

var express = require('express');
var ClienteController = require('../controllers/cliente');

var router = express.Router();
//rutas de usuarios
router.post('/clientes',ClienteController.save);
router.put('/clientes/',ClienteController.update);
router.get('/clientes',ClienteController.getClientes);
router.get('/clientes/:id',ClienteController.getCliente);
router.delete('/clientes/:id',ClienteController.delete);
router.post('/cliente/',ClienteController.getClienteByEmail);

//exportar el modulo
module.exports = router;