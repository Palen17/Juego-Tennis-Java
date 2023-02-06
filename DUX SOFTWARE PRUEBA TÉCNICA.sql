--Punto 1

select producto.nombre
from producto
inner join rubro on producto.id_rubro = rubro.id_rubro
where rubro.rubro = 'libreria'
and producto.fecha_creacion = sysdate

--Punto 2

select cliente.cliente, cliente.apellido, venta.cantiddad * venta.precio_unitario
from cliente, venta
where cliente.id_cliente = venta.id_cliente

--Punto 3

select producto.nombre, venta.cantidad
from producto, venta
where producto.codigo = venta.codigo_producto

--Punto 4

select cliente.id_cliente, venta.cantidad
from cliente, venta
where cliente.id_cliente = venta.id_cliente
and datepart(mm, venta.fecha) = datepart(mm, getdate())

--Punto 5

select venta.id_venta
from venta
inner join producto on venta.codigo_producto = producto.codigo
inner join rubro on producto.id_rubro = rubro.id_rubro
where rubro.rubro = 'bazar'

--Punto 6

select rubro.rubro
from rubro
inner join producto on rubro.id_rubro = producto.id_rubro
inner join venta on producto.codigo = venta.codigo_producto
WHERE venta.fecha < NOW() - INTERVAL 2 MONTH