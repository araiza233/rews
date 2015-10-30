/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.publicidad.logic;
import com.publicidad.dao.NuevoProductoDao;
import com.publicidad.entities.DetalleVentas;
import com.publicidad.entities.Producto;
import com.publicidad.entities.Ventas;
import java.util.Collections;
import java.util.List;
public class NuevoProductoLogic implements NuevoProductoLogicInterface{
    private NuevoProductoDao nuevoProductoDao;
    @Override
    public String guardar(Producto producto){
        return nuevoProductoDao.guardar(producto);
    }
    public NuevoProductoDao getNuevoProductoDao() {
        return nuevoProductoDao;
    }
    public void setNuevoProductoDao(NuevoProductoDao nuevoProductoDao) {
        this.nuevoProductoDao = nuevoProductoDao;
    }
    @Override
    public List buscarProducto(String text, int op) {
        List listaOrdenada = nuevoProductoDao.buscarProducto(text, op);
        Collections.sort(listaOrdenada);
        return listaOrdenada;
    }
    @Override
    public String editar(Producto producto){
        return nuevoProductoDao.editar(producto);
    }
    @Override
    public String guardarVenta(Ventas ventas){
        return nuevoProductoDao.guardarVenta(ventas);
    }
    @Override
    public String guardarDetalleVentas(String detalles){
        return nuevoProductoDao.guardarDetalleVentas(detalles);
    }

    @Override
    public Producto getProductoById(int idProducto) {
        return nuevoProductoDao.getProductoById(idProducto);
    }
    @Override
    public String editarVenta(Producto producto){
        return nuevoProductoDao.editarVenta(producto);
    }
}