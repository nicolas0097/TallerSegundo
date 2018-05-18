create database tallersegundo;
use tallersegundo;

create table clientes(
id_cliente integer(30),
alias varchar(30) not null,
apellidos varchar(30)not null,
nombre varchar(30)not null,
direccion varchar(50)not null,
poblacion varchar(30)not null,
telefono varchar(30)not null,
movil varchar(30)not null,
email varchar(50)not null,
observaciones varchar(30)not null,
sexo varchar(30)not null,
fecha_nacimiento date not null,
estado_civil varchar(30)not null,
desempleado boolean not null,
fumador boolean not null,
primary key(id_cliente)
);



create table proveedores(
id_proveedor integer(30),
nombre varchar (100)not null,
direccion varchar(300)not null,
telefono varchar(100)not null,
fax varchar(100)not null,
rfc varchar (50)not null,
correo varchar(100)not null,
web varchar(100)not null,
contacto varchar(100)not null,
puesto_contacto varchar(100)not null,
telefono_contacto varchar(100)not null,
movil_contacto varchar (100)not null,
correo_contacto varchar(100)not null,
telefono_alterno1 varchar(100),
telefono_alterno2 varchar(100),
cantidad_comprada integer (100)not null,
cantidad_$ double not null,
primary key(id_proveedor)
);


create table productos(
id_producto integer(11),
nombre varchar(50)not null,
id_proveedor integer(30)not null,
categoria varchar(50)not null,
cantidad_unidad integer(10)not null,
precio_unidad double not null,
unidades_existencia integer(10)not null,
unidades_pedido integer(10)not null,
suspendido boolean not null,
primary key(id_producto),
foreign key(id_proveedor)
references proveedores(id_proveedor)
on delete cascade
on update cascade
 );
 
 create table pedidos(
 id_pedido integer(11)  AUTO_INCREMENT,
 fecha date not null,
 precio double not null,
 primary key(id_pedido)

);

create table detalle_pedidos(
id_producto integer(11)not null,
id_pedido integer(11)not null,
cantidad integer(5)not null,
foreign key(id_producto)
references productos(id_producto)
on delete cascade
on update cascade,
foreign key(id_pedido)
references pedidos(id_pedido)
on delete cascade
on update cascade
);

CREATE TABLE tallerSegundo.ventas(
idVenta int(11),
fecha_venta varchar(13),
hora_enta varchar(13),
valor_venta int(10),
cliente int(11),
primary key(idVenta),
foreign key(cliente) references tallerSegundo.clientes(id_cliente)
);
ALTER TABLE tallerSegundo.ventas
CHANGE COLUMN idVenta idVenta INT(11) NOT NULL AUTO_INCREMENT ;

create table detalle_ventas(
id_producto integer(11)not null,
cantidad integer(5)not null,
id_venta integer(11)not null,
foreign key(id_producto)
references productos(id_producto)
on delete cascade
on update cascade,
foreign key(id_venta)
references ventas(id_venta)
on delete cascade
on update cascade
);


