'use strict'

var mongoose=require('mongoose');
var app = require('./app');
var port = process.env.PORT || 3999;

mongoose.set( 'useUnifiedTopology' ,true )
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/ventas_db', {useNewUrlParser:true})
        .then(()=>{
            console.log('la conexion a la base de datos de mongo se ha realizado correctamente')

            //Crear el servidor
            app.listen(port, ()=>{
                console.log('El servidor esta corriendo en http://localhost:3999');
            });
        })
        .catch(error => console.log(error));