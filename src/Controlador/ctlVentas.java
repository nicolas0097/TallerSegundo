/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ventasDAO;
import Modelo.Venta;

/**
 *
 * @author nicolasgarcia
 */
public class ctlVentas {
     public boolean SolicitudGuardar(int idVenta, String fecha_venta, String hora_venta, int valor_venta, int cliente){
      Venta venta = new Venta(idVenta, fecha_venta, hora_venta, valor_venta, cliente);
       ventasDAO pediDAO = new ventasDAO();
        return pediDAO.guardarVenta(venta);
    
}
    
}
