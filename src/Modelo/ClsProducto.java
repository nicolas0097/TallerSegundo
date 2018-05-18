package Modelo;

/**
 *
 * @author Cristian Sabogal L
 */

public class ClsProducto {

    private int id_proroducto;
    private String nombre;
    private int id_proveedor;
    private String categoria;
    private int cantidad_unidad;
    private double precio;
    private int unidades_existencia;
    private int unidades_pedido;
    private boolean suspendido;

    public ClsProducto(int id_proroducto, String nombre, int id_proveedor, String categoria, int cantidad_unidad, double precio, int unidades_existencia, int unidades_pedido, boolean suspendido) {
        this.id_proroducto = id_proroducto;
        this.nombre = nombre;
        this.id_proveedor = id_proveedor;
        this.categoria = categoria;
        this.cantidad_unidad = cantidad_unidad;
        this.precio = precio;
        this.unidades_existencia = unidades_existencia;
        this.unidades_pedido = unidades_pedido;
        this.suspendido = suspendido;
    }

    public ClsProducto() {
    }

    public int getId_proroducto() {
        return id_proroducto;
    }

    public void setId_proroducto(int id_proroducto) {
        this.id_proroducto = id_proroducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad_unidad() {
        return cantidad_unidad;
    }

    public void setCantidad_unidad(int cantidad_unidad) {
        this.cantidad_unidad = cantidad_unidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidades_existencia() {
        return unidades_existencia;
    }

    public void setUnidades_existencia(int unidades_existencia) {
        this.unidades_existencia = unidades_existencia;
    }

    public int getUnidades_pedido() {
        return unidades_pedido;
    }

    public void setUnidades_pedido(int unidades_pedido) {
        this.unidades_pedido = unidades_pedido;
    }

    public boolean isSuspendido() {
        return suspendido;
    }

    public void setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
    }

}
