package com.publicidad.entities;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
@Entity
@Table(name = "ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idVentas;
    private String vendidoPor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeVenta;
    private float totalVenta;
    private float cambio;
    private float pago;
    public Date getFechaDeVenta() {
        return fechaDeVenta;
    }

    public void setFechaDeVenta(Date fechaDeVenta) {
        this.fechaDeVenta = fechaDeVenta;
    }
    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getVendidoPor() {
        return vendidoPor;
    }
    public void setVendidoPor(String vendidoPor) {
        this.vendidoPor = vendidoPor;
    }
    public float getCambio() {
        return cambio;
    }
    public void setCambio(float cambio) {
        this.cambio = cambio;
    }
    public float getPago() {
        return pago;
    }
    public void setPago(float pago) {
        this.pago = pago;
    }
}