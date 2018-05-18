package Modelo;

/**
 *
 * @author Cristian Sabogal L
 */

import java.util.Date;

public class ClsCliente {

    private int id_cliente;
    private String alias;
    private String apellido;
    private String nombre;
    private String direccion;
    private String poblacion;
    private String telefono;
    private String movil;
    private String email;
    private String observacion;
    private String sexo;
    private Date fecha_nacimiento;
    private String estado_civil;
    private boolean desempleado;
    private boolean fumador;

    public ClsCliente(int id_cliente, String alias, String apellido, String nombre, String direccion, String poblacion,
            String telefono, String movil, String email, String observacion, String sexo, Date fecha_nacimiento, String estado_civil, int hijos, boolean desempleado,
            boolean fumador) {
        this.id_cliente = id_cliente;
        this.alias = alias;
        this.apellido = apellido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.telefono = telefono;
        this.movil = movil;
        this.email = email;
        this.observacion = observacion;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado_civil = estado_civil;

        this.desempleado = desempleado;

        this.fumador = fumador;

    }

    public ClsCliente() {
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_estudiante) {
        this.id_cliente = id_estudiante;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public boolean isDesempleado() {
        return desempleado;
    }

    public void setDesempleado(boolean desempleado) {
        this.desempleado = desempleado;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

}
