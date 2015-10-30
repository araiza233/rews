select * from producto;
select * from ventas where idventas=33;
select * from detalleventas WHERE idventas=33;
SELECT count(DISTINCT idventas) FROM ventas;


delete from producto;
delete from ventas;
delete from detalleventas;