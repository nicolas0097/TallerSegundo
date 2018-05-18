/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Cristian Sabogal L
 */

import Modelo.ClsConexion;

import Modelo.ClsProducto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ProductoDAO extends ClsConexion {

    ClsProducto dao = new ClsProducto();

    public boolean guardarUsuario(ClsProducto produDAO) {
        String consulta = "insert into productos values("
                + produDAO.getId_proroducto() + ",'" + produDAO.getNombre() + "'," + produDAO.getId_proveedor() + ",'" + produDAO.getCategoria() + "'," + produDAO.getCantidad_unidad()
                + "," + produDAO.getPrecio() + "," + produDAO.getUnidades_existencia() + "," + produDAO.getUnidades_pedido() + "," + produDAO.isSuspendido() + ")";
        return super.ejecutar(consulta);
    }

    public ClsProducto buscarProveedor(int id) {
        String consulta = "select * from productos where id_producto=" + id + "";

        super.ejecutarRetorno(consulta);
        try {
            if (resultadoDB.next()) {
                dao.setId_proroducto(resultadoDB.getInt(1));
                dao.setNombre(resultadoDB.getString(2));
                dao.setId_proveedor(resultadoDB.getInt(3));
                dao.setCategoria(resultadoDB.getString(4));
                dao.setCantidad_unidad(resultadoDB.getInt(5));
                dao.setPrecio(resultadoDB.getDouble(6));
                dao.setUnidades_existencia(resultadoDB.getInt(7));
                dao.setUnidades_pedido(resultadoDB.getInt(8));
                dao.setSuspendido(resultadoDB.getBoolean(9));
            }
        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
            ex.printStackTrace();
        }
        return dao;
    }

    public DefaultTableModel buscarProductoPorNombre(String nombre) {
        DefaultTableModel modelTabla;

        String nombreColumnas[] = {"Id", "Nombre", "Proveedor",
            "Categoria", "Precio"};

        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);
        String consulta = "select * from productos where nombre like " + "'" + nombre + "_%'";

        super.ejecutarRetorno(consulta);

        try {
            while (resultadoDB.next()) {
                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("id_producto") + "",
                    resultadoDB.getString("nombre"),
                    resultadoDB.getInt("id_proveedor") + "",
                    resultadoDB.getString("categoria"),
                    resultadoDB.getDouble("precio_unidad") + ""
                });
            }
        } catch (Exception e) {
            System.out.println("esto se tosto");
            e.printStackTrace();
        }
        return modelTabla;

    }

    public DefaultTableModel buscarProductoPorProveedor(String nombre) {
        DefaultTableModel modelTabla;

        String nombreColumnas[] = {"Id", "Nombre", "Proveedor",
            "Categoria", "Precio"};

        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);
        String consulta = "SELECT *"
                + "  FROM productos JOIN proveedores ON productos.id_proveedor = proveedores.id_proveedor "
                + "WHERE proveedores.nombre=" + "'" + nombre + "'";

        super.ejecutarRetorno(consulta);

        try {
            while (resultadoDB.next()) {
                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("id_producto") + "",
                    resultadoDB.getString("nombre"),
                    resultadoDB.getInt("id_proveedor") + "",
                    resultadoDB.getString("categoria"),
                    resultadoDB.getDouble("precio_unidad") + ""
                });
            }
        } catch (Exception e) {
            System.out.println("esto se tosto");
            e.printStackTrace();
        }
        return modelTabla;

    }

    public DefaultTableModel buscarProductoCategoria(String nombre) {
        DefaultTableModel modelTabla;

        String nombreColumnas[] = {"Id", "Nombre", "Proveedor",
            "Categoria", "Precio"};

        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);
        String consulta = "SELECT *"
                + "  FROM productos  "
                + "WHERE categoria=" + "'" + nombre + "'";

        super.ejecutarRetorno(consulta);

        try {
            while (resultadoDB.next()) {
                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("id_producto") + "",
                    resultadoDB.getString("nombre"),
                    resultadoDB.getInt("id_proveedor") + "",
                    resultadoDB.getString("categoria"),
                    resultadoDB.getDouble("precio_unidad") + ""
                });
            }
        } catch (Exception e) {
            System.out.println("esto se tosto");
            e.printStackTrace();
        }
        return modelTabla;

    }

    public boolean modificarUsuario(ClsProducto produDAO) {
        String consulta = "update productos set nombre='" + produDAO.getNombre() + "',id_proveedor=" + produDAO.getId_proveedor()
                + "," + "categoria='" + produDAO.getCategoria() + "', cantidad_unidad=" + produDAO.getCantidad_unidad() + ", precio_unidad= "
                + produDAO.getPrecio() + ", unidades_existencia=" + produDAO.getUnidades_existencia() + ", unidades_pedido=" + produDAO.getUnidades_pedido()
                + ", suspendido=" + produDAO.isSuspendido()
                + " where id_producto= " + produDAO.getId_proroducto();
        return super.ejecutar(consulta);
    }

    public boolean eliminarProducto(int id_producto) {
        String consulta = "delete from productos where id_producto=" + id_producto;
        return super.ejecutar(consulta);
    }

    public DefaultTableModel listarUsuario() {

        DefaultTableModel modelTabla;
        String nombreColumnas[] = {"Id", "Nombre", "Proveedor", "Categoria", "Cantidad Unidad", "Precio", "Unidades en Existencia",
            "Unidades en pedido", "Suspendido"};
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);

        String consulta = "select * from productos";
        super.ejecutarRetorno(consulta);

        try {
            while (resultadoDB.next()) {

                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("id_producto") + "",
                    resultadoDB.getString("nombre"),
                    resultadoDB.getInt("id_proveedor") + "",
                    resultadoDB.getString("categoria"),
                    resultadoDB.getInt("cantidad_unidad") + "",
                    resultadoDB.getDouble("precio_unidad") + "",
                    resultadoDB.getInt("unidades_existencia") + "",
                    resultadoDB.getInt("unidades_pedido") + "",
                    resultadoDB.getBoolean("suspendido")
                });

            }

        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
            ex.printStackTrace();
        }
        return modelTabla;
    }

    public ArrayList<String> listarNombres() {
        ArrayList<String> lista = new ArrayList<>();
        String consulta = "select nombre_materia from materias";
        super.ejecutarRetorno(consulta);
        try {
            while (resultadoDB.next()) {
                lista.add(resultadoDB.getString("nombre_materia"));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int buscarMateriaPorNombre(String nombre) {
        String consulta = "select id_materia from materias where nombre_materia = '" + nombre + "'";
        super.ejecutarRetorno(consulta);
        try {
            while (resultadoDB.next()) {
                return resultadoDB.getInt("id_materia");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return 0;
    }
    public ClsProducto buscarPre(String nombre) {
        String consulta = "select precio_unidad from tallerExcel.productos where nombre='" + nombre + "'";
        super.ejecutarRetorno(consulta);
        try {
            if (resultadoDB.next()) {
                System.out.println("entro buscar");
               dao.setPrecio(resultadoDB.getInt("precio_unidad"));
                
               
                
                
            }
        } catch (SQLException ex) {
            System.out.println("Esto se tosto buscar");
        }
        return dao;
    }

}
