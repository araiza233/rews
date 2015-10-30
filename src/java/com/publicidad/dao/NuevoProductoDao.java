package com.publicidad.dao;
import com.publicidad.entities.DetalleVentas;
import com.publicidad.entities.Producto;
import com.publicidad.entities.Ventas;
import java.util.List;
public interface NuevoProductoDao {
    public String guardar(Producto producto);
    public List buscarProducto(String text, int op);
    public String editar(Producto producto);
    public String guardarVenta(Ventas ventas);
    public String guardarDetalleVentas(String detalles);
    public Producto getProductoById(int idProducto);
    public String editarVenta(Producto producto);
}