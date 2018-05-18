/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author nicolasgarcia
 */
public class Venta {
    int idVenta;
    String fecha_venta, hora_venta;
    int valor_venta, cliente;

    public Venta() {
    }

    public Venta(int idVenta, String fecha_venta, String hora_venta, int valor_venta, int cliente) {
        this.idVenta = idVenta;
        this.fecha_venta = fecha_venta;
        this.hora_venta = hora_venta;
        this.valor_venta = valor_venta;
        this.cliente = cliente;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getHora_venta() {
        return hora_venta;
    }

    public void setHora_venta(String hora_venta) {
        this.hora_venta = hora_venta;
    }

    public int getValor_venta() {
        return valor_venta;
    }

    public void setValor_venta(int valor_venta) {
        this.valor_venta = valor_venta;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

   
    
    
}
