package com.publicidad.entities;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
@Entity
@Table(name = "producto")
public class Producto implements Comparable<Producto>{
    @Id
    private int idProducto;
    private String nombre;
    private float precio;
    private String descripcion;
    private String codigo;
    private String unidadDeVenta;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    private String creadoPor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActualizacion;
    private String actualizadoPor;
    private String fileName;
    private Float piezas;
    private Float precio_mayoreo;
    private Float precio_especial;
    public Producto() {
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public String getUnidadDeVenta() {
        return unidadDeVenta;
    }
    public void setUnidadDeVenta(String unidadDeVenta) {
        this.unidadDeVenta = unidadDeVenta;
    }

    public String getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(String actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Override
    public int compareTo(Producto o) {
        return nombre.compareToIgnoreCase(o.getNombre());
    }
    public Float getPiezas() {
        return piezas;
    }
    public void setPiezas(Float piezas) {
        this.piezas = piezas;
    }
    public Float getPrecio_especial() {
        return precio_especial;
    }
    public void setPrecio_especial(Float precio_especial) {
        this.precio_especial = precio_especial;
    }
    public Float getPrecio_mayoreo() {
        return precio_mayoreo;
    }
    public void setPrecio_mayoreo(Float precio_mayoreo) {
        this.precio_mayoreo = precio_mayoreo;
    }
}