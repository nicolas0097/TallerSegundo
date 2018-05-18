package Modelo;


public class ClsProveedor {

    private int id_proveedor;
    private String nombre;
    private String direccion;
    private String telefono;
    private String fax;
    private String rfc;
    private String correo;
    private String web;
    private String contacto;
    private String puesto_contacto;
    private String telefono_contacto;
    private String movil_contacto;
    private String correo_contacto;
    private String telefono_alt1;
    private String telefono_alt2;
    private int cantidad_comprada;
    private double cantidad_$;

    public ClsProveedor(int id_proveedor, String nombre, String direccion, String telefono, String fax, String rfc, String correo, String web, String contacto, String puesto_contacto, String telefono_contacto, String movil_contacto, String correo_contacto, String telefono_alt1, String telefono_alt2, int cantidad_comprada, double cantidad_$) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fax = fax;
        this.rfc = rfc;
        this.correo = correo;
        this.web = web;
        this.contacto = contacto;
        this.puesto_contacto = puesto_contacto;
        this.telefono_contacto = telefono_contacto;
        this.movil_contacto = movil_contacto;
        this.correo_contacto = correo_contacto;
        this.telefono_alt1 = telefono_alt1;
        this.telefono_alt2 = telefono_alt2;
        this.cantidad_comprada = cantidad_comprada;
        this.cantidad_$ = cantidad_$;
    }

    public void setMovil_contacto(String movil_contacto) {
        this.movil_contacto = movil_contacto;
    }

    public String getTelefono_alt1() {
        return telefono_alt1;
    }

    public void setTelefono_alt1(String telefono_alt1) {
        this.telefono_alt1 = telefono_alt1;
    }

    public String getTelefono_alt2() {
        return telefono_alt2;
    }

    public void setTelefono_alt2(String telefono_alt2) {
        this.telefono_alt2 = telefono_alt2;
    }

    public ClsProveedor() {
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getPuesto_contacto() {
        return puesto_contacto;
    }

    public void setPuesto_contacto(String puesto_contacto) {
        this.puesto_contacto = puesto_contacto;
    }

    public String getCorreo_contacto() {
        return correo_contacto;
    }

    public void setCorreo_contacto(String correo_contacto) {
        this.correo_contacto = correo_contacto;
    }

    public int getCantidad_comprada() {
        return cantidad_comprada;
    }

    public void setCantidad_comprada(int cantidad_comprada) {
        this.cantidad_comprada = cantidad_comprada;
    }

    public double getCantidad_$() {
        return cantidad_$;
    }

    public void setCantidad_$(double cantidad_$) {
        this.cantidad_$ = cantidad_$;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public String getMovil_contacto() {
        return movil_contacto;
    }

}
