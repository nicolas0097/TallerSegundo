package Vista;

/**
 *
 * @author Cristian Sabogal L
 */


import Controlador.CtlCliente;
import Controlador.CtlPedido;
import Controlador.CtlProducto;
import Controlador.CtlProveedor;
import Modelo.ClsCliente;
import Modelo.ClsProducto;
import Modelo.ClsProveedor;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class DB extends javax.swing.JFrame {

    CtlCliente ctlCliente;
    CtlProducto ctlProducto;
    CtlProveedor ctlProveedor;
    CtlPedido ctlPedido;
    private static DefaultTableModel modeloProd;
    private static DefaultTableModel modeloCli;
    private static DefaultTableModel modeloProve;
    private JFileChooser FileChooser = new JFileChooser();
    private Vector columnaProd = new Vector();
    private Vector filasProd = new Vector();
    private static int tabla_anchoProd = 0;
    private static int tabla_altoProd = 0;
    private Vector columnaCli = new Vector();
    private Vector filasCli = new Vector();
    private static int tabla_anchoCli = 0;
    private static int tabla_altoCli = 0;
    private Vector columnaProve = new Vector();
    private Vector filasProve = new Vector();
    private static int tabla_anchoProve = 0;
    private static int tabla_altoProve = 0;

    /**
     * Creates new form FrmEmpresa
     */
    public DB() {
        initComponents();
        modeloProd = new DefaultTableModel();
        modeloCli = new DefaultTableModel();
        modeloProve = new DefaultTableModel();
        ctlCliente = new CtlCliente();
        ctlProducto = new CtlProducto();
        ctlProveedor = new CtlProveedor();
        ctlPedido = new CtlPedido();
        listarClientes();
        listarProveedores();
        LlenarComboQuemado();
        llenarComboProveedores();
        llenarComboProveedoresPedido();
        listarPedidos();
        listarProductos();
        ChckEditarCliente.setEnabled(false);
        ChckEditarProveedor.setEnabled(false);
        ChckEditarProducto.setEnabled(false);
        modeloProd.addColumn("id");
        modeloProd.addColumn("nombre");
        modeloProd.addColumn("proveedor");
        modeloProd.addColumn("categoria");
        modeloProd.addColumn("cantidadunid");
        modeloProd.addColumn("precio");
        modeloProd.addColumn("unidExis");
        modeloProd.addColumn("unidPedi");
        modeloProd.addColumn("nivel");
        modeloProd.addColumn("suspendido");
        modeloCli.addColumn("Id");
        modeloCli.addColumn("Alias");
        modeloCli.addColumn("Apellido");
        modeloCli.addColumn("Nombre");
        modeloCli.addColumn("Direccion");
        modeloCli.addColumn("Poblacion");
        modeloCli.addColumn("Telefono");
        modeloCli.addColumn("Movil");
        modeloCli.addColumn("Email");
        modeloCli.addColumn("Observacion");
        modeloCli.addColumn("Sexo");
        modeloCli.addColumn("Fecha Nacimiento");
        modeloCli.addColumn("Estado Civil");
        modeloCli.addColumn("Hijos");
        modeloCli.addColumn("Desempleado");
        modeloCli.addColumn("Profesion");
        modeloCli.addColumn("Nivel Academico");
        modeloCli.addColumn("Peso");
        modeloCli.addColumn("Altura");
        modeloCli.addColumn("Cabello");
        modeloCli.addColumn("Ojos");
        modeloCli.addColumn("Fumador");
        modeloCli.addColumn("Salud");
        modeloProve.addColumn("Id");
        modeloProve.addColumn("Nombre");
        modeloProve.addColumn("Direccion");
        modeloProve.addColumn("Telefono");
        modeloProve.addColumn("Fax");
        modeloProve.addColumn("RFC");
        modeloProve.addColumn("Correo");
        modeloProve.addColumn("Web");
        modeloProve.addColumn("Contacto");
        modeloProve.addColumn("Puesto Contacto");
        modeloProve.addColumn("Telefono Contacto");
        modeloProve.addColumn("Movil contacto");
        modeloProve.addColumn("Correo contacto");
        modeloProve.addColumn("Tel Alterno 1");
        modeloProve.addColumn("Tel Alterno 2");
        modeloProve.addColumn("Cantidad comprada");
        modeloProve.addColumn("Cantidad $");
        this.tableProveedores.setModel(modeloProve);
        this.tableProductos.setModel(modeloProd);
        this.tableClientes.setModel(modeloCli);

    }

//     public void Agrupar() {
//        btngDesempleado.add(rbtnSiDesempleado);
//        btngDesempleado.add(rbtnNoDesempleado);
//        btngfumador.add(rbtnSiFumador);
//        btngfumador.add(rbtnNoFumador);
//        btngSuspensionProducto.add(rbtnSiSuspendido);
//        btngSuspensionProducto.add(rbtnNoSuspendido);
//
//    }
    public void LlenarComboQuemado() {
        cbSexoCliente.removeAllItems();
        cbEstadocivil.removeAllItems();
        cbSexoCliente.addItem("Seleccione el genero");
        cbSexoCliente.addItem("Masculino");
        cbSexoCliente.addItem("Femenino");
        cbEstadocivil.removeAllItems();
        cbEstadocivil.addItem("Seleccione el estado civil");
        cbEstadocivil.addItem("Soltero/a");
        cbEstadocivil.addItem("Casado/a");
        cbEstadocivil.addItem("Separado/a");
        cbEstadocivil.addItem("Divorciado/a");
        cbEstadocivil.addItem("Otros");
        cbCategoriaProducto.removeAllItems();
        // cbCategoriaProducto.addItem("Seleccione una categoria");
        cbCategoriaProducto.addItem("Bebidas");
        cbCategoriaProducto.addItem("Carnes");
        cbCategoriaProducto.addItem("Condimentos");
        cbCategoriaProducto.addItem("Frutas/Verduras");
        cbCategoriaProducto.addItem("Lácteos");
        cbCategoriaProducto.addItem("Pescado/Marisco");
        cbCategoriaProducto.addItem("Repostería");
        cbCategoriaProducto.addItem("Granos/Cereales");
        // cbCategoriaPedido.addItem("Seleccione una categoria");
        cbCategoriaPedido.addItem("Bebidas");
        cbCategoriaPedido.addItem("Carnes");
        cbCategoriaPedido.addItem("Condimentos");
        cbCategoriaPedido.addItem("Frutas/Verduras");
        cbCategoriaPedido.addItem("Lácteos");
        cbCategoriaPedido.addItem("Pescado/Marisco");
        cbCategoriaPedido.addItem("Repostería");
        cbCategoriaPedido.addItem("Granos/Cereales");

    }

    public void llenarComboProveedores() {
        ArrayList<String> nombres = ctlProveedor.SolicitudNombres();
        for (int i = 0; i < nombres.size(); i++) {
            for (int j = 0; j < nombres.size(); j++) {
                if (nombres.get(i).equals(nombres.get(j)) && i != j) {
                    nombres.remove(i);
                }
            }
        }
        for (String nombre : nombres) {
            cbNombreProveedorProducto.addItem(nombre);
        }
    }

    public void llenarComboProveedoresPedido() {
        ArrayList<String> nombres = ctlProveedor.SolicitudNombres();
        for (int i = 0; i < nombres.size(); i++) {
            for (int j = 0; j < nombres.size(); j++) {
                if (nombres.get(i).equals(nombres.get(j)) && i != j) {
                    nombres.remove(i);
                }
            }
        }
        for (String nombre : nombres) {
            cbProveedoresPedido.addItem(nombre);
        }
    }

    public boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.matches();
    }

    public void exportarExcel(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                org.apache.poi.ss.usermodel.Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
                hoja.setDisplayGridlines(false);
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        org.apache.poi.ss.usermodel.Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(t.getColumnName(c));
                        }
                    }
                }
                int filaInicio = 1;
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        org.apache.poi.ss.usermodel.Cell celda = fila.createCell(c);
                        if (t.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        } else if (t.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                        } else {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        }
                    }
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public void CrearTabla(File file) throws IOException {
        jxl.Workbook workbook = null;
        try {
            workbook = jxl.Workbook.getWorkbook(file);

            jxl.Sheet sheet = workbook.getSheet(0);
            columnaProd.clear();

            for (int i = 0; i < sheet.getColumns(); i++) {
                jxl.Cell cell1 = sheet.getCell(i, 0);
                columnaProd.add(cell1.getContents());
            }
            filasProd.clear();
            for (int j = 1; j < sheet.getRows(); j++) {

                Vector d = new Vector();

                for (int i = 0; i < sheet.getColumns(); i++) {

                    jxl.Cell cell = sheet.getCell(i, j);
                    d.add(cell.getContents());
                }
                d.add("\n");
                //filas.add(d);
                modeloProd.addRow(d);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        BtnImportarClientes = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProveedores = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        BtnImportarProductos = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableClientes2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        btnGuardarCliente = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnModificarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        BtnListarClientes = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        txtPoblacionCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtMovilCliente = new javax.swing.JTextField();
        txtemailCliente = new javax.swing.JTextField();
        txtObservacionCliente = new javax.swing.JTextField();
        cbSexoCliente = new javax.swing.JComboBox<>();
        cbEstadocivil = new javax.swing.JComboBox<>();
        rbtnSiDesempleado = new javax.swing.JRadioButton();
        jdcFechaNacimientoCliente = new com.toedter.calendar.JDateChooser();
        rbtnNoDesempleado = new javax.swing.JRadioButton();
        rbtnSiFumador = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        rbtnNoFumador = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtAliasCliente = new javax.swing.JTextField();
        txtapellidoCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtnombreCliente = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        ChckEditarCliente = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableProductos2 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btnGuardarProducto = new javax.swing.JButton();
        btnBuscarProducto = new javax.swing.JButton();
        btnModificarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        rbtnSiSuspendido = new javax.swing.JRadioButton();
        rbtnNoSuspendido = new javax.swing.JRadioButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtNumProducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        cbNombreProveedorProducto = new javax.swing.JComboBox<>();
        cbCategoriaProducto = new javax.swing.JComboBox<>();
        txtCantidadUnidadProducto = new javax.swing.JTextField();
        txtPrecioUnidad = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtUnidadesExistencia = new javax.swing.JTextField();
        txtUnidadPedido = new javax.swing.JTextField();
        ChckEditarProducto = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        cbProveedoresPedido = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        BtnAgregarAlPedido = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablapedido = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        btnGuardarPedido = new javax.swing.JButton();
        btnBuscarPedido = new javax.swing.JButton();
        btnModificarPedido = new javax.swing.JButton();
        btnEliminarPedido = new javax.swing.JButton();
        txtValorPedido = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        cbCategoriaPedido = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablePedido1 = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablePedido2 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        btnCancelarPedido = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtRFCProveedor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtCorreoProveedor = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtWeb = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtContactoProveedor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPuestoContacto = new javax.swing.JTextField();
        txtTelefonoContactoProveedor = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtMovilContactoProveedor = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtCorreoContactoProveedor = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTelefonoAlternativoProveedor = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtSegundoTelefonoProveedor = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtCantidadCompradasProveedor = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtCantidadPesosProveedor = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        txtDireccionProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtFax = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfId_Proveedor = new javax.swing.JTextField();
        ChckEditarProveedor = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        btnGuardarProveedor = new javax.swing.JButton();
        btnBuscarProveedor = new javax.swing.JButton();
        btnModificarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        BtnListarProveedores = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableProveedores2 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tableClientes.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableClientes);

        BtnImportarClientes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        BtnImportarClientes.setText("Importar ");
        BtnImportarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImportarClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnImportarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1650, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(316, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(BtnImportarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(452, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes", jPanel3);

        tableProveedores.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableProveedores);

        jButton3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton3.setText("Importar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1636, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(421, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Proveedores", jPanel4);

        tableProductos.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableProductos);

        BtnImportarProductos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        BtnImportarProductos.setText("Importar");
        BtnImportarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImportarProductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnImportarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(329, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(BtnImportarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(378, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", jPanel2);

        tableClientes2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tableClientes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableClientes2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientes2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableClientes2);

        btnGuardarCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGuardarCliente.setText("Guardar");
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnModificarCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnModificarCliente.setText("Modificar");
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        BtnListarClientes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BtnListarClientes.setText("Listar");
        BtnListarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnListarClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnModificarCliente)
                .addGap(24, 24, 24)
                .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 24, Short.MAX_VALUE)
                .addComponent(BtnListarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCliente)
                    .addComponent(btnBuscar)
                    .addComponent(btnModificarCliente)
                    .addComponent(btnEliminarCliente)
                    .addComponent(BtnListarClientes))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(245, 245, 245));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPoblacionCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTelefonoCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtMovilCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtemailCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtObservacionCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbSexoCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbEstadocivil.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbEstadocivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        rbtnSiDesempleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnSiDesempleado.setText("Si");

        jdcFechaNacimientoCliente.setDateFormatString("yyyy-MM-d");
        jdcFechaNacimientoCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        rbtnNoDesempleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnNoDesempleado.setText("No");

        rbtnSiFumador.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnSiFumador.setText("Si");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Movil");

        rbtnNoFumador.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnNoFumador.setText("No");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Observacion:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Genero");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Fecha Nacimiento:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Estado Civil");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Desempleado");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Fumador");

        txtAliasCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtapellidoCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Apellido:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Direccion");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Poblacion:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Telefono:");

        jLabel44.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel44.setText("Cedula:");

        txtnombreCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtCedula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtDireccionCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        ChckEditarCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ChckEditarCliente.setText("Editar");
        ChckEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChckEditarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefonoCliente)
                                    .addComponent(txtemailCliente)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(11, 11, 11)
                                .addComponent(txtapellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAliasCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtnombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtPoblacionCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                                .addComponent(txtMovilCliente, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtObservacionCliente, javax.swing.GroupLayout.Alignment.LEADING)))))))
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(46, 46, 46)
                                .addComponent(rbtnSiFumador, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(rbtnNoFumador, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbEstadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbSexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ChckEditarCliente)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(rbtnSiDesempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(rbtnNoDesempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jdcFechaNacimientoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtAliasCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtapellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtnombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPoblacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtMovilCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtemailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtObservacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cbSexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(jdcFechaNacimientoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cbEstadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(rbtnSiDesempleado)
                        .addComponent(rbtnNoDesempleado)))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rbtnSiFumador)
                    .addComponent(rbtnNoFumador)
                    .addComponent(ChckEditarCliente))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1063, 1063, 1063))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1634, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        jTabbedPane1.addTab("Gestion Clientes", jPanel1);

        tableProductos2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tableProductos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProductos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductos2MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableProductos2);

        btnGuardarProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarProducto.setText("Guardar");
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });

        btnBuscarProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        btnModificarProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificarProducto.setText("Modificar");
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnGuardarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnModificarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarProducto)
                    .addComponent(btnBuscarProducto)
                    .addComponent(btnModificarProducto)
                    .addComponent(btnEliminarProducto))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1393, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(245, 245, 245));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rbtnSiSuspendido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnSiSuspendido.setText("Si");

        rbtnNoSuspendido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnNoSuspendido.setText("No");

        jLabel39.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel39.setText("unidades existencia:");

        jLabel40.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel40.setText("unidades en pedido:");

        jLabel42.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel42.setText("suspendido:");

        txtNumProducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtNombreProducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cbNombreProveedorProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbNombreProveedorProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Proveedor" }));
        cbNombreProveedorProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNombreProveedorProductoActionPerformed(evt);
            }
        });

        cbCategoriaProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbCategoriaProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Categoria" }));

        txtCantidadUnidadProducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtPrecioUnidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel33.setText("N° producto:");

        jLabel34.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel34.setText("nombre Producto:");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel35.setText("Nombre  proveedor:");

        jLabel36.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel36.setText("categoria:");

        jLabel37.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel37.setText("cantidad por unidad:");

        jLabel38.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel38.setText("precio unidad:");

        txtUnidadesExistencia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtUnidadPedido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        ChckEditarProducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ChckEditarProducto.setText("Editar");
        ChckEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChckEditarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(62, 62, 62)
                        .addComponent(txtNumProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel34)
                        .addGap(40, 40, 40)
                        .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 71, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(20, 20, 20)
                        .addComponent(cbNombreProveedorProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel36)
                        .addGap(29, 29, 29)
                        .addComponent(cbCategoriaProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(txtUnidadesExistencia)
                .addGap(44, 44, 44)
                .addComponent(jLabel40)
                .addGap(29, 29, 29)
                .addComponent(txtUnidadPedido))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(40, 40, 40)
                        .addComponent(rbtnSiSuspendido, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnNoSuspendido, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ChckEditarProducto))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidadUnidadProducto)))
                .addGap(44, 44, 44)
                .addComponent(jLabel38)
                .addGap(29, 29, 29)
                .addComponent(txtPrecioUnidad))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtNumProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(cbNombreProveedorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(cbCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtCantidadUnidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(txtPrecioUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtUnidadesExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txtUnidadPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(rbtnSiSuspendido)
                    .addComponent(rbtnNoSuspendido)
                    .addComponent(ChckEditarProducto))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setText("Listar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(653, 653, 653)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1301, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jButton1)
                .addContainerGap(342, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addContainerGap(266, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(94, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Gestion Productos", jPanel10);

        jLabel48.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel48.setText("Proveedor:");

        cbProveedoresPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbProveedoresPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione proveedor" }));
        cbProveedoresPedido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProveedoresPedidoItemStateChanged(evt);
            }
        });
        cbProveedoresPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedoresPedidoActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel49.setText("Producto");

        BtnAgregarAlPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnAgregarAlPedido.setText("Añadir");
        BtnAgregarAlPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarAlPedidoActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel50.setText("Producto A pedir");

        jButton6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton6.setText("Quitar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tablapedido.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tablapedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(tablapedido);

        jLabel45.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel45.setText("Total:");

        btnGuardarPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarPedido.setText("Guardar");
        btnGuardarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPedidoActionPerformed(evt);
            }
        });

        btnBuscarPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarPedido.setText("Buscar");
        btnBuscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPedidoActionPerformed(evt);
            }
        });

        btnModificarPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificarPedido.setText("Modificar");
        btnModificarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPedidoActionPerformed(evt);
            }
        });

        btnEliminarPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminarPedido.setText("Eliminar");
        btnEliminarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPedidoActionPerformed(evt);
            }
        });

        txtValorPedido.setEditable(false);
        txtValorPedido.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel46.setText("Categoria:");

        cbCategoriaPedido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCategoriaPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione categoria" }));
        cbCategoriaPedido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriaPedidoItemStateChanged(evt);
            }
        });

        tablePedido1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tablePedido1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane12.setViewportView(tablePedido1);

        tablePedido2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tablePedido2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Producto", "Nombre", "Proveedor", "Categoria", "Precio", "Cantidad"
            }
        ));
        jScrollPane13.setViewportView(tablePedido2);

        jLabel51.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel51.setText("Nombre");

        txtFiltro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel52.setText("Filtrar por:");

        btnCancelarPedido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelarPedido.setText("Cancelar");
        btnCancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnAgregarAlPedido)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel45)
                                        .addGap(29, 29, 29)
                                        .addComponent(txtValorPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(btnModificarPedido)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnEliminarPedido))
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(btnGuardarPedido)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnBuscarPedido)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnCancelarPedido))))))
                            .addComponent(jLabel52)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCategoriaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbProveedoresPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(18, 18, 18)
                                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(cbProveedoresPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(cbCategoriaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton6)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnAgregarAlPedido)
                                        .addGap(105, 105, 105)))))))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtValorPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarPedido)
                            .addComponent(btnBuscarPedido)
                            .addComponent(btnCancelarPedido))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificarPedido)
                            .addComponent(btnEliminarPedido))))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(596, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pedido", jPanel14);

        jPanel6.setBackground(new java.awt.Color(245, 245, 245));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtRFCProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Proveedora");

        txtCorreoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Direccion");

        txtWeb.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Telefono");

        txtContactoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Fax");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("RFC:");

        txtPuestoContacto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTelefonoContactoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTelefonoContactoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoContactoProveedorActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("Correo");

        txtMovilContactoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("Web");

        txtCorreoContactoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Contacto");

        txtTelefonoAlternativoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("Puesto Contacto");

        txtSegundoTelefonoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText("Telefono Contacto");

        txtCantidadCompradasProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setText("Movil Contacto");

        txtCantidadPesosProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText("Correo Contacto");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setText("Telefono Alternativo");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setText("Telefono Alternativo 2");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setText("Cantidades Compradas");

        jLabel32.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel32.setText("Cantidad Pesos");

        txtNombreProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtDireccionProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTelefonoProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtFax.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("ID");

        tfId_Proveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        ChckEditarProveedor.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ChckEditarProveedor.setText("Editar");
        ChckEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChckEditarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtRFCProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtMovilContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTelefonoAlternativoProveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(txtCantidadCompradasProveedor, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addComponent(txtPuestoContacto, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtWeb, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfId_Proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCorreoContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 2, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefonoContactoProveedor)
                                    .addComponent(txtContactoProveedor)
                                    .addComponent(txtCorreoProveedor)
                                    .addComponent(txtFax)
                                    .addComponent(txtDireccionProveedor)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCantidadPesosProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSegundoTelefonoProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ChckEditarProveedor)
                        .addGap(164, 164, 164))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfId_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtRFCProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtPuestoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtMovilContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtTelefonoAlternativoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtTelefonoContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtCorreoContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtSegundoTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(txtCantidadPesosProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(txtCantidadCompradasProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(ChckEditarProveedor)))
                .addGap(49, 49, 49))
        );

        btnGuardarProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarProveedor.setText("Guardar");
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnBuscarProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarProveedor.setText("Buscar");
        btnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedorActionPerformed(evt);
            }
        });

        btnModificarProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificarProveedor.setText("Modificar");
        btnModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminarProveedor.setText("Eliminar");
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });

        BtnListarProveedores.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BtnListarProveedores.setText("Listar");
        BtnListarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnListarProveedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnGuardarProveedor)
                .addGap(61, 61, 61)
                .addComponent(btnBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnModificarProveedor)
                .addGap(67, 67, 67)
                .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(BtnListarProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarProveedor)
                    .addComponent(btnBuscarProveedor)
                    .addComponent(btnModificarProveedor)
                    .addComponent(btnEliminarProveedor)
                    .addComponent(BtnListarProveedores))
                .addContainerGap())
        );

        tableProveedores2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tableProveedores2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProveedores2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProveedores2MouseClicked(evt);
            }
        });
        tableProveedores2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableProveedores2KeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tableProveedores2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1586, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(364, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Gestion Proveedores", jPanel5);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel16.setText("EMPRESA EAM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2003, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(42, 42, 42)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        try {
            String nombre = txtFiltro.getText();

            tablePedido1.setModel(ctlProducto.SolicitudBuscarPorNombre(nombre));

        } catch (HeadlessException | NumberFormatException e) {
            Logger.getLogger(DB.class
                .getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_txtFiltroKeyReleased

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void cbCategoriaPedidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaPedidoItemStateChanged
        try {
            String nombre = (String) cbCategoriaPedido.getSelectedItem();

            tablePedido1.setModel(ctlProducto.SolicitudBuscarPorCategoria(nombre));

        } catch (HeadlessException | NumberFormatException e) {
            Logger.getLogger(DB.class
                .getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_cbCategoriaPedidoItemStateChanged

    private void btnEliminarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPedidoActionPerformed
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id del pedido"));
        ctlPedido.solicitudEliminar(id);
        listarPedidos();
    }//GEN-LAST:event_btnEliminarPedidoActionPerformed

    private void btnBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPedidoActionPerformed
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id del pedido"));
        tablapedido.setModel(ctlPedido.solicituBuscar(id));
        listarPedidos(); 
    }//GEN-LAST:event_btnBuscarPedidoActionPerformed

    private void btnGuardarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPedidoActionPerformed
        int id_proveedor = ctlProveedor.solicitudId((String) cbProveedoresPedido.getSelectedItem());
        double precio = 0;

        java.util.Date fecha = new Date();
        java.sql.Date fecha2 = new java.sql.Date(fecha.getYear(), fecha.getMonth(), fecha.getDay());
        for (int i = 0; i < tablePedido2.getRowCount(); i++) {
            precio = precio + (Double.parseDouble((String) tablePedido2.getValueAt(i, 4)) * Double.parseDouble((String) tablePedido2.getValueAt(i, 5)));

        }
        if(precio==0){
        JOptionPane.showMessageDialog(null, "No hay productos seleccionados");
        }
        else{
        ctlPedido.solicitudGuardar(fecha2, precio);
        int id_pedido = ctlPedido.solicitudIdPedido(fecha2, precio);
        for (int i = 0; i < tablePedido2.getRowCount(); i++) {
            int cantidad = Integer.parseInt(tablePedido2.getValueAt(i, 5) + "");
            ctlPedido.solicitudGuardarDetalle(Integer.parseInt((String) tablePedido2.getValueAt(i, 0)), id_pedido, cantidad);
            txtValorPedido.setText(0 + "");
        }
        }
        listarPedidos(); 
        LimpiarPedidos();
    }//GEN-LAST:event_btnGuardarPedidoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablePedido2.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(tablePedido2.getSelectedRow());
        }

        tablePedido2.setModel(model);        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void BtnAgregarAlPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarAlPedidoActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablePedido2.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tablePedido1.getModel();
        String cantidad = JOptionPane.showInputDialog(null, "Cantidad");
        model.addRow(new Object[]{
            model2.getValueAt(tablePedido1.getSelectedRow(), 0) + "",
            model2.getValueAt(tablePedido1.getSelectedRow(), 1) + "",
            model2.getValueAt(tablePedido1.getSelectedRow(), 2) + "",
            model2.getValueAt(tablePedido1.getSelectedRow(), 3) + "",
            model2.getValueAt(tablePedido1.getSelectedRow(), 4) + "",
            cantidad+""
        });
        tablePedido2.setModel(model);
        
         double precio = 0;
        
        for (int i = 0; i < tablePedido2.getRowCount(); i++) {
            precio = precio + (Double.parseDouble((String) tablePedido2.getValueAt(i, 4)) * Integer.parseInt((String)tablePedido2.getValueAt(i, 5)));
        }
        txtValorPedido.setText(precio + "");
    }//GEN-LAST:event_BtnAgregarAlPedidoActionPerformed

    private void cbProveedoresPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedoresPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProveedoresPedidoActionPerformed

    private void cbProveedoresPedidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProveedoresPedidoItemStateChanged
        try {
            String nombre = (String) cbProveedoresPedido.getSelectedItem();

            tablePedido1.setModel(ctlProducto.SolicitudBuscarPorProveedor(nombre));

        } catch (HeadlessException | NumberFormatException e) {
            Logger.getLogger(DB.class
                .getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_cbProveedoresPedidoItemStateChanged

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del producto a eliminar"));
            ctlProducto.solicitudEliminar(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Id del producto a editar"));
        ClsProducto producto = ctlProducto.SolicitudBuscar(id);
        if (!producto.getNombre().equals("")) {
            txtNumProducto.setText(producto.getId_proroducto() + "");
            txtNumProducto.setEditable(false);
            txtNombreProducto.setText(producto.getNombre());
            cbNombreProveedorProducto.setSelectedItem(ctlProveedor.SolicitudNombreProveedor(producto.getId_proveedor()));
            cbCategoriaProducto.setSelectedItem(producto.getCategoria());
            txtCantidadUnidadProducto.setText(producto.getCantidad_unidad() + "");
            txtPrecioUnidad.setText(producto.getPrecio() + "");
            txtUnidadesExistencia.setText(producto.getUnidades_existencia() + "");
            txtUnidadPedido.setText(producto.getUnidades_pedido() + "");
            if (producto.isSuspendido()) {
                rbtnSiSuspendido.setSelected(true);
            } else {
                rbtnSiSuspendido.setSelected(false);
            }
            ChckEditarProducto.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }//GEN-LAST:event_btnModificarProductoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Id del producto a buscar"));
        ClsProducto producto = ctlProducto.SolicitudBuscar(id);

        if (!producto.getNombre().equals("")) {
            DefaultTableModel model = (DefaultTableModel) tableProductos2.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(new String[]{"Id", "Nombre", "Proveedor",
                "Categoria", "Cantidad Unidad",
                "Precio", "Unidades en Existencia",
                "Unidades en pedido", "Suspendido"});
        model.addRow(new Object[]{
            producto.getId_proroducto(),
            producto.getNombre(),
            ctlProveedor.SolicitudNombreProveedor(producto.getId_proveedor()),
            producto.getCategoria(),
            producto.getCantidad_unidad() + "",
            producto.getPrecio() + "",
            producto.getUnidades_existencia() + "",
            producto.getUnidades_pedido() + "",
            producto.isSuspendido() + ""
        });
        } else {
            JOptionPane.showMessageDialog(this, "No se encuentra");
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed
        try {
            int id = Integer.parseInt(txtNumProducto.getText());
            String nombre = txtNombreProducto.getText();
            int id_proveedor = ctlProveedor.solicitudId((String) cbNombreProveedorProducto.getSelectedItem());
            String categoria = (String) cbCategoriaProducto.getSelectedItem();
            int cantidad_unidad = Integer.parseInt(txtCantidadUnidadProducto.getText());
            double precio = Double.parseDouble(txtPrecioUnidad.getText());
            int unidad_existencia = Integer.parseInt(txtUnidadesExistencia.getText());
            int unidad_pedido = Integer.parseInt(txtUnidadPedido.getText());
            boolean suspendido = rbtnSiSuspendido.isSelected();
            if (ctlProducto.SolicitudGuardar(id, nombre, id_proveedor, categoria, cantidad_unidad, precio, unidad_existencia, unidad_pedido, suspendido)) {
                JOptionPane.showMessageDialog(null, "Producto guardado");
            } else {
                JOptionPane.showMessageDialog(null, "Producto No guardado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarProductoActionPerformed

    private void ChckEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChckEditarProductoActionPerformed
        int id = Integer.parseInt(txtNumProducto.getText());
        String nombre = txtNombreProducto.getText();
        int id_proveedor = ctlProveedor.solicitudId((String) cbNombreProveedorProducto.getSelectedItem());
        String categoria = (String) cbCategoriaProducto.getSelectedItem();
        int cantidad_unidad = Integer.parseInt(txtCantidadUnidadProducto.getText());
        double precio = Double.parseDouble(txtPrecioUnidad.getText());
        int unidad_existencia = Integer.parseInt(txtUnidadesExistencia.getText());
        int unidad_pedido = Integer.parseInt(txtUnidadPedido.getText());
        boolean suspendido = rbtnSiSuspendido.isSelected();
        if (ctlProducto.SolicitudModificar(id_proveedor, nombre, id_proveedor, categoria, cantidad_unidad, precio, unidad_existencia, unidad_pedido, suspendido)) {
            JOptionPane.showMessageDialog(null, "Producto editado");
            txtNumProducto.setEditable(false);
            ChckEditarProducto.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Producto No editado");
            ChckEditarProducto.setEnabled(true);
        }
    }//GEN-LAST:event_ChckEditarProductoActionPerformed

    private void cbNombreProveedorProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNombreProveedorProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNombreProveedorProductoActionPerformed

    private void tableProductos2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductos2MouseClicked
        int seleccion = tableProductos2.rowAtPoint(evt.getPoint());
        txtNumProducto.setText(String.valueOf(tableProductos2.getValueAt(seleccion, 0)));
        txtNombreProducto.setText(String.valueOf(tableProductos2.getValueAt(seleccion, 1)));
        cbNombreProveedorProducto.setSelectedItem(String.valueOf(tableProductos2.getValueAt(seleccion, 2)));
        cbCategoriaProducto.setSelectedItem(String.valueOf(tableProductos2.getValueAt(seleccion, 3)));
        txtCantidadUnidadProducto.setText(String.valueOf(tableProductos2.getValueAt(seleccion, 4)));
        txtPrecioUnidad.setText(String.valueOf(tableProductos2.getValueAt(seleccion, 5)));
        txtUnidadesExistencia.setText(String.valueOf(tableProductos2.getValueAt(seleccion, 6)));
        txtUnidadPedido.setText(String.valueOf(tableProductos2.getValueAt(seleccion, 7)));
        //        txtNivelNuevo.setText(String.valueOf(tableProductos2.getValueAt(seleccion, 8)));
        rbtnSiSuspendido.setSelected(Boolean.parseBoolean((String) tableProductos2.getValueAt(seleccion, 8)));
        if (Boolean.parseBoolean((String) tableProductos2.getValueAt(seleccion, 8)) == false) {
            rbtnNoSuspendido.setSelected(true);
        }
    }//GEN-LAST:event_tableProductos2MouseClicked

    private void tableProveedores2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProveedores2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableProveedores2KeyPressed

    private void tableProveedores2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedores2MouseClicked
        int seleccion = tableProveedores2.rowAtPoint(evt.getPoint());
        tfId_Proveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 0)));
        txtNombreProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 1)));
        txtDireccionProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 2)));
        txtTelefonoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 3)));
        txtFax.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 4)));
        txtRFCProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 5)));
        txtCorreoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 6)));
        txtWeb.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 7)));
        txtContactoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 8)));
        txtPuestoContacto.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 9)));
        txtTelefonoContactoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 10)));
        txtMovilContactoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 11)));
        txtCorreoContactoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 12)));
        txtTelefonoAlternativoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 13)));
        txtSegundoTelefonoProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 14)));
        txtCantidadCompradasProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 15)));
        txtCantidadPesosProveedor.setText(String.valueOf(tableProveedores2.getValueAt(seleccion, 16)));
    }//GEN-LAST:event_tableProveedores2MouseClicked

    private void BtnListarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnListarProveedoresActionPerformed
        listarProveedores();
    }//GEN-LAST:event_BtnListarProveedoresActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del proveedor a eliminar"));
            ctlProveedor.solicitudEliminar(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProveedorActionPerformed
        int idProveedor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del estudiante a buscar"));
        ClsProveedor proveedor = ctlProveedor.SolicitudBuscar(idProveedor);

        if (!proveedor.getNombre().equals("")) {
            tfId_Proveedor.setText(proveedor.getId_proveedor() + "");
            tfId_Proveedor.setEditable(false);
            txtNombreProveedor.setText(proveedor.getNombre());
            txtDireccionProveedor.setText(proveedor.getDireccion());
            txtTelefonoProveedor.setText(proveedor.getTelefono());
            txtFax.setText(proveedor.getFax());
            txtRFCProveedor.setText(proveedor.getRfc());
            txtWeb.setText(proveedor.getWeb());
            txtContactoProveedor.setText(proveedor.getContacto());
            txtPuestoContacto.setText(proveedor.getPuesto_contacto());
            txtTelefonoContactoProveedor.setText(proveedor.getTelefono_contacto());
            txtMovilContactoProveedor.setText(proveedor.getMovil_contacto());
            txtCorreoContactoProveedor.setText(proveedor.getCorreo_contacto());
            txtTelefonoAlternativoProveedor.setText(proveedor.getTelefono_alt1());
            txtSegundoTelefonoProveedor.setText(proveedor.getTelefono_alt2());
            txtCantidadCompradasProveedor.setText(proveedor.getCantidad_comprada() + "");
            txtCantidadPesosProveedor.setText(proveedor.getCantidad_$() + "");
            ChckEditarProveedor.setEnabled(true);
        }
    }//GEN-LAST:event_btnModificarProveedorActionPerformed

    private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
        // TODO add your handling code here:

        try {
            int idProveedor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del estudiante a buscar"));
            ClsProveedor proveedor = ctlProveedor.SolicitudBuscar(idProveedor);

            if (!proveedor.getNombre().equals("")) {
                DefaultTableModel model = (DefaultTableModel) tableProveedores2.getModel();
                model.setRowCount(0);
                model.setColumnIdentifiers(new String[]{"Id Proveedor", "Nombre", "Direccion", "Telefono", "Fax", "RFC",
                    "Web", "Contacto", "Puesto Contacto", "Telefono Contacto", "Movil Contacto",
                    "Correo Contacto", "Telefono Alterno 1", "Telefono Alterno 2", "Cantidades Compradas", "Cantidades en $"});
            model.addRow(new Object[]{
                proveedor.getId_proveedor(),
                proveedor.getNombre(),
                proveedor.getDireccion(),
                proveedor.getTelefono(),
                proveedor.getFax(),
                proveedor.getRfc(),
                proveedor.getWeb(),
                proveedor.getContacto(),
                proveedor.getPuesto_contacto(),
                proveedor.getTelefono_contacto(),
                proveedor.getMovil_contacto(),
                proveedor.getCorreo_contacto(),
                proveedor.getTelefono_alt1(),
                proveedor.getTelefono_alt2(),
                proveedor.getCantidad_comprada() + "",
                proveedor.getCantidad_$() + ""
            });
        }

        } catch (HeadlessException | NumberFormatException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnBuscarProveedorActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        try {
            if (txtNombreProveedor.getText().isEmpty() || txtDireccionProveedor.getText().isEmpty() || txtTelefonoProveedor.getText().isEmpty()
                || txtFax.getText().isEmpty() || txtRFCProveedor.getText().isEmpty() || txtCorreoProveedor.getText().isEmpty()
                || txtWeb.getText().isEmpty() || txtContactoProveedor.getText().isEmpty() || txtPuestoContacto.getText().isEmpty()
                || txtPuestoContacto.getText().isEmpty() || txtTelefonoContactoProveedor.getText().isEmpty() || txtPuestoContacto.getText().isEmpty()
                || txtMovilContactoProveedor.getText().isEmpty() || txtCorreoContactoProveedor.getText().isEmpty()
                || txtCantidadCompradasProveedor.getText().isEmpty() || txtCantidadPesosProveedor.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Llene todos los campos");

            } else {
                int id = Integer.parseInt(tfId_Proveedor.getText());
                String rcf = txtRFCProveedor.getText();
                String web = txtWeb.getText();
                String telefonoAlterno = txtTelefonoAlternativoProveedor.getText();
                String telefonoAlterno2 = txtSegundoTelefonoProveedor.getText();
                String nombreProveedor = txtNombreProveedor.getText();
                String direccion = txtDireccionProveedor.getText();
                String telefono = txtTelefonoProveedor.getText();
                String fax = txtFax.getText();
                String contacto = txtContactoProveedor.getText();
                String correo = txtCorreoProveedor.getText();
                String pusto = txtPuestoContacto.getText();
                String telefonoContacto = txtTelefonoContactoProveedor.getText();
                String puestoContacto = txtPuestoContacto.getText();
                String movilContacto = txtMovilContactoProveedor.getText();
                String correoContacto = txtCorreoContactoProveedor.getText();
                int cantidadCompradas = Integer.parseInt(txtCantidadCompradasProveedor.getText());
                double cantiadPesos = Double.parseDouble(txtCantidadPesosProveedor.getText());

                if (validarEmail(correo)) {
                    if (validarEmail(correoContacto)) {

                        if (ctlProveedor.SolicitudGuardar(id, correo, direccion, telefono, fax, rcf, correoContacto, web, contacto, puestoContacto, telefonoContacto, movilContacto, correoContacto, telefono, telefono, cantidadCompradas, cantiadPesos)) {
                            JOptionPane.showMessageDialog(null, "Proveedor Guardado");
                            limpiarProveedores();
                            listarProveedores();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "E-mail Invalido");
                        listarProveedores();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "E-mail Invalido");
                    listarProveedores();
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void ChckEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChckEditarProveedorActionPerformed
        try {
            if (txtNombreProveedor.getText().isEmpty() || txtDireccionProveedor.getText().isEmpty() || txtTelefonoProveedor.getText().isEmpty()
                || txtFax.getText().isEmpty() || txtRFCProveedor.getText().isEmpty() || txtCorreoProveedor.getText().isEmpty()
                || txtWeb.getText().isEmpty() || txtContactoProveedor.getText().isEmpty() || txtPuestoContacto.getText().isEmpty()
                || txtPuestoContacto.getText().isEmpty() || txtTelefonoContactoProveedor.getText().isEmpty() || txtPuestoContacto.getText().isEmpty()
                || txtMovilContactoProveedor.getText().isEmpty() || txtCorreoContactoProveedor.getText().isEmpty()
                || txtCantidadCompradasProveedor.getText().isEmpty() || txtCantidadPesosProveedor.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Llene todos los campos");

            } else {
                int id = Integer.parseInt(tfId_Proveedor.getText());
                String rcf = txtRFCProveedor.getText();
                String web = txtWeb.getText();
                String telefonoAlterno = txtTelefonoAlternativoProveedor.getText();
                String telefonoAlterno2 = txtSegundoTelefonoProveedor.getText();
                String nombreProveedor = txtNombreProveedor.getText();
                String direccion = txtDireccionProveedor.getText();
                String telefono = txtTelefonoProveedor.getText();
                String fax = txtFax.getText();
                String contacto = txtContactoProveedor.getText();
                String correo = txtCorreoProveedor.getText();
                String telefonoContacto = txtTelefonoContactoProveedor.getText();
                String puestoContacto = txtPuestoContacto.getText();
                String movilContacto = txtMovilContactoProveedor.getText();
                String correoContacto = txtCorreoContactoProveedor.getText();
                int cantidadCompradas = Integer.parseInt(txtCantidadCompradasProveedor.getText());
                double cantiadPesos = Double.parseDouble(txtCantidadPesosProveedor.getText());

                if (ctlProveedor.SolicitudModificarProveedor(id, nombreProveedor, direccion, telefono, fax, rcf, correo, web, contacto, puestoContacto, telefonoContacto, movilContacto, correoContacto, telefonoAlterno, telefonoAlterno2, cantidadCompradas, cantiadPesos)) {
                    JOptionPane.showMessageDialog(null, "Proveedor Editado");
                    tfId_Proveedor.setEditable(true);

                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ChckEditarProveedorActionPerformed

    private void txtTelefonoContactoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoContactoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoContactoProveedorActionPerformed

    private void ChckEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChckEditarClienteActionPerformed
        try {
            if (txtAliasCliente.getText().isEmpty() || txtapellidoCliente.getText().isEmpty() || txtDireccionCliente.getText().isEmpty() || txtnombreCliente.getText().isEmpty() || txtTelefonoCliente.getText().isEmpty() || txtMovilCliente.getText().isEmpty()
                || txtemailCliente.getText().isEmpty() || txtObservacionCliente.getText().isEmpty() || cbEstadocivil.getSelectedItem().equals("Seleccione el estado civil")
                || txtPoblacionCliente.getText().isEmpty() || ((JTextField) jdcFechaNacimientoCliente.getDateEditor().getUiComponent()).getText().isEmpty() || cbSexoCliente.getSelectedItem().equals("Seleccione el genero") || !rbtnNoDesempleado.isSelected() && !rbtnSiDesempleado.isSelected() || !rbtnNoFumador.isSelected() && !rbtnSiFumador.isSelected()) {
                JOptionPane.showMessageDialog(null, "Ingrese los campos");
            } else {
                int id = Integer.parseInt(txtCedula.getText());
                String alias = txtAliasCliente.getText();
                String nombre = txtnombreCliente.getText();
                String apellido = txtapellidoCliente.getText();
                String direccion = txtDireccionCliente.getText();
                String poblacion = txtPoblacionCliente.getText();
                String telefono = txtTelefonoCliente.getText();
                String movil = txtMovilCliente.getText();
                String email = txtemailCliente.getText();
                String observacion = txtObservacionCliente.getText();
                Date fechaNacimiento = jdcFechaNacimientoCliente.getDate();
                java.sql.Date fecha2 = new java.sql.Date(fechaNacimiento.getYear(), fechaNacimiento.getMonth(), fechaNacimiento.getDay());
                String genero = cbSexoCliente.getSelectedItem().toString();
                String estadocivil = cbEstadocivil.getSelectedItem().toString();
                boolean desempleado = ObtenerValorDesempleado();
                boolean fumador = false;
                if (rbtnSiFumador.isSelected()) {
                    fumador = true;
                } else if (rbtnNoFumador.isSelected()) {
                    fumador = false;
                }
                if (ctlCliente.SolicitudModificar(id, alias, nombre, apellido, direccion, poblacion, telefono, movil, email, observacion, fecha2, genero, estadocivil, desempleado, fumador)) {
                    if (validarEmail(email)) {
                        JOptionPane.showMessageDialog(this, "Editado exitosamente");
                        ChckEditarCliente.setEnabled(false);
                        listarClientes();
                        limpiarClientes();
                    } else {
                        JOptionPane.showMessageDialog(this, "Email invalido");
                        ChckEditarCliente.setSelected(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error al editar");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_ChckEditarClienteActionPerformed

    private void BtnListarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnListarClientesActionPerformed
        listarClientes();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnListarClientesActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del cliente a editar"));
            if (ctlCliente.solicitudEliminar(id)) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado");
                listarClientes();
                limpiarClientes();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente No eliminado");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del cliente a editar"));
        ClsCliente cliente = ctlCliente.SolicitudBuscar(id);
        System.out.println(cliente.getNombre());
        if (!cliente.getNombre().equals("")) {
            txtCedula.setText(cliente.getId_cliente() + "");
            txtCedula.setEnabled(false);
            txtAliasCliente.setText(cliente.getAlias());
            txtapellidoCliente.setText(cliente.getApellido());
            txtnombreCliente.setText(cliente.getNombre());
            txtemailCliente.setText(cliente.getEmail());
            txtDireccionCliente.setText(cliente.getDireccion());
            txtMovilCliente.setText(cliente.getMovil());
            txtTelefonoCliente.setText(cliente.getTelefono());
            txtPoblacionCliente.setText(cliente.getPoblacion());
            txtObservacionCliente.setText(cliente.getObservacion());
            cbSexoCliente.setSelectedItem(cliente.getSexo());
            cbEstadocivil.setSelectedItem(cliente.getEstado_civil());
            boolean resFuma = cliente.isFumador();
            if (resFuma) {
                rbtnSiFumador.setSelected(true);
            } else {
                rbtnNoFumador.setSelected(true);
            }
            boolean resDesemp = cliente.isDesempleado();
            if (resDesemp) {
                rbtnSiDesempleado.setSelected(true);
            } else {
                rbtnNoDesempleado.setSelected(true);
            }
            jdcFechaNacimientoCliente.setDate(cliente.getFecha_nacimiento());
            ChckEditarCliente.setEnabled(true);
        }
    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del cliente que desea buscar"));
        ClsCliente cliente = ctlCliente.SolicitudBuscar(id);

        if (!cliente.getNombre().equals("")) {
            DefaultTableModel model = (DefaultTableModel) tableClientes2.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(new String[]{"Id", "Alias", "Apellido", "Nombre", "Direccion", "Poblacion", "Telefono", "Movil", "Email", "Observacion", "Sexo", "Fecha Nacimiento", "Estado Civil", "Desempleado", "Fumador"});
            model.addRow(new Object[]{
                cliente.getId_cliente(),
                cliente.getAlias(),
                cliente.getApellido(),
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getPoblacion(),
                cliente.getTelefono(),
                cliente.getMovil(),
                cliente.getEmail(),
                cliente.getObservacion(),
                cliente.getSexo(),
                cliente.getFecha_nacimiento().toString(),
                cliente.getEstado_civil(),
                cliente.isDesempleado() + "",
                cliente.isFumador() + ""});
        } else {
            JOptionPane.showMessageDialog(this, "No se encuentra");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        try {
            if (txtAliasCliente.getText().isEmpty() || txtapellidoCliente.getText().isEmpty() || txtDireccionCliente.getText().isEmpty() || txtnombreCliente.getText().isEmpty() || txtTelefonoCliente.getText().isEmpty() || txtMovilCliente.getText().isEmpty()
                || txtemailCliente.getText().isEmpty() || txtObservacionCliente.getText().isEmpty() || cbEstadocivil.getSelectedItem().equals("Seleccione el estado civil")
                || txtPoblacionCliente.getText().isEmpty() || ((JTextField) jdcFechaNacimientoCliente.getDateEditor().getUiComponent()).getText().isEmpty() || cbSexoCliente.getSelectedItem().equals("Seleccione el genero") || !rbtnNoDesempleado.isSelected() && !rbtnSiDesempleado.isSelected() || !rbtnNoFumador.isSelected() && !rbtnSiFumador.isSelected()) {
                JOptionPane.showMessageDialog(null, "Ingrese los campos");
            } else {
                int id = Integer.parseInt(txtCedula.getText());
                String alias = txtAliasCliente.getText();
                String nombre = txtnombreCliente.getText();
                String apellido = txtapellidoCliente.getText();
                String direccion = txtDireccionCliente.getText();
                String poblacion = txtPoblacionCliente.getText();
                String telefono = txtTelefonoCliente.getText();
                String movil = txtMovilCliente.getText();
                String email = txtemailCliente.getText();
                String observacion = txtObservacionCliente.getText();
                Date fechaNacimiento = jdcFechaNacimientoCliente.getDate();
                java.sql.Date fecha2 = new java.sql.Date(fechaNacimiento.getYear(), fechaNacimiento.getMonth(), fechaNacimiento.getDay());
                String genero = cbSexoCliente.getSelectedItem().toString();
                String estadocivil = cbEstadocivil.getSelectedItem().toString();
                boolean desempleado = ObtenerValorDesempleado();
                boolean fumador = false;
                if (rbtnSiFumador.isSelected()) {
                    fumador = true;
                } else if (rbtnNoFumador.isSelected()) {
                    fumador = false;
                }
                if (validarEmail(email)) {
                    if (ctlCliente.SolicitudGuardar(id, alias, nombre, apellido, direccion, poblacion, telefono, movil, email, observacion, fecha2, genero, estadocivil, desempleado, fumador)) {

                        JOptionPane.showMessageDialog(this, "Guardado exitosamente");
                        listarClientes();
                        limpiarClientes();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Correo invalido ingreselo de esta manera: 'Programador@gmail.com'");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void tableClientes2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientes2MouseClicked
        int seleccion = tableClientes2.rowAtPoint(evt.getPoint());
        txtCedula.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 0)));
        txtAliasCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 1)));
        txtapellidoCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 2)));
        txtnombreCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 3)));
        txtDireccionCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 4)));
        txtPoblacionCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 5)));
        txtTelefonoCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 6)));
        txtMovilCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 7)));
        txtemailCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 8)));
        txtObservacionCliente.setText(String.valueOf(tableClientes2.getValueAt(seleccion, 9)));
        cbSexoCliente.setSelectedItem(String.valueOf(tableClientes2.getValueAt(seleccion, 10)));
        jdcFechaNacimientoCliente.setDateFormatString(String.valueOf(tableProveedores2.getValueAt(seleccion, 11)));
        cbEstadocivil.setSelectedItem(String.valueOf(tableProveedores2.getValueAt(seleccion, 12)));
        rbtnSiDesempleado.setSelected(Boolean.parseBoolean((String) tableProveedores2.getValueAt(seleccion, 13)));
        rbtnNoDesempleado.setSelected(Boolean.parseBoolean((String) tableProveedores2.getValueAt(seleccion, 14)));
        rbtnSiFumador.setSelected(Boolean.parseBoolean((String) tableProveedores2.getValueAt(seleccion, 15)));
        if (Boolean.parseBoolean((String) tableProveedores2.getValueAt(seleccion, 16)) == false) {
            rbtnNoFumador.setSelected(false);
        }
    }//GEN-LAST:event_tableClientes2MouseClicked

    private void BtnImportarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImportarProductosActionPerformed
        modeloProd.setRowCount(0);
        FileChooser.showDialog(null, "Importar Hoja ");
        File file = FileChooser.getSelectedFile();
        if (!file.getName().endsWith("xls")) {

            JOptionPane.showMessageDialog(null, "Seleccione un archivo excel...", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                CrearTabla(file);
                enviarProductos();
            } catch (IOException ex) {
                Logger.getLogger(DB.class
                    .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BtnImportarProductosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modeloProve.setRowCount(0);
        FileChooser.showDialog(null, "Importar Hoja ");
        File file = FileChooser.getSelectedFile();
        if (!file.getName().endsWith("xls")) {

            JOptionPane.showMessageDialog(null, "Seleccione un archivo excel...", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                jxl.Workbook workbook = null;
                try {
                    workbook = jxl.Workbook.getWorkbook(file);

                    jxl.Sheet sheet = workbook.getSheet(0);
                    columnaProve.clear();

                    for (int i = 0; i < sheet.getColumns(); i++) {
                        jxl.Cell cell1 = sheet.getCell(i, 0);
                        columnaProve.add(cell1.getContents());
                    }
                    filasProve.clear();
                    for (int j = 1; j < sheet.getRows(); j++) {

                        Vector d = new Vector();

                        for (int i = 0; i < sheet.getColumns(); i++) {

                            jxl.Cell cell = sheet.getCell(i, j);
                            d.add(cell.getContents());
                        }
                        d.add("\n");
                        modeloProve.addRow(d);
                    }
                    enviarProveedores();
                } catch (BiffException e) {
                    e.printStackTrace();

                }
            } catch (IOException ex) {
                Logger.getLogger(DB.class
                    .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BtnImportarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImportarClientesActionPerformed

        modeloCli.setRowCount(0);
        FileChooser.showDialog(null, "Importar Hoja ");
        File file = FileChooser.getSelectedFile();
        if (!file.getName().endsWith("xls")) {

            JOptionPane.showMessageDialog(null, "Seleccione un archivo excel...", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                jxl.Workbook workbook = null;
                try {
                    workbook = jxl.Workbook.getWorkbook(file);

                    jxl.Sheet sheet = workbook.getSheet(0);
                    columnaCli.clear();

                    for (int i = 0; i < sheet.getColumns(); i++) {
                        jxl.Cell cell1 = sheet.getCell(i, 0);
                        columnaCli.add(cell1.getContents());
                    }
                    filasCli.clear();
                    for (int j = 1; j < sheet.getRows(); j++) {

                        Vector d = new Vector();

                        for (int i = 0; i < sheet.getColumns(); i++) {

                            jxl.Cell cell = sheet.getCell(i, j);
                            d.add(cell.getContents());
                        }
                        d.add("\n");
                        //filas.add(d);
                        modeloCli.addRow(d);
                    }
                    enviarClientes();
                } catch (BiffException e) {
                    e.printStackTrace();

                }
            } catch (IOException ex) {
                Logger.getLogger(DB.class
                    .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BtnImportarClientesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listarProductos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnModificarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPedidoActionPerformed
       listarPedidos();
    }//GEN-LAST:event_btnModificarPedidoActionPerformed

    private void btnCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoActionPerformed
        LimpiarPedidos();
        txtValorPedido.setText(0 + "");
    }//GEN-LAST:event_btnCancelarPedidoActionPerformed

    private void listarClientes() {
        tableClientes2.setModel(ctlCliente.SolicitudListar());
    }

    private void listarProveedores() {
        tableProveedores2.setModel(ctlProveedor.SolicitudListar());
    }

    private void listarProductos() {
        tableProductos2.setModel(ctlProducto.SolicitudListar());
    }

    private void listarPedidos() {
        tablapedido.setModel(ctlPedido.solicitudListar());
    }
    
    private void LimpiarPedidos() {
    DefaultTableModel model = (DefaultTableModel) tablePedido2.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i-=1;
        }

        tablePedido2.setModel(model); 
    }
        

    public boolean ObtenerValorDesempleado() {
        boolean des = false;
        if (rbtnNoDesempleado.isSelected()) {
            des = false;
        } else if (rbtnSiDesempleado.isSelected()) {
            des = true;
        }
        return des;
    }

    private void enviarProductos() {
        DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int id_producto = Integer.parseInt(model.getValueAt(i, 0) + "");
            String nombre = model.getValueAt(i, 1) + "";
            int id_proveedor = Integer.parseInt(model.getValueAt(i, 2) + "");
            String categoria = model.getValueAt(i, 3) + "";
            int cantidad_unidad = Integer.parseInt(model.getValueAt(i, 4) + "");
            double precio = Double.parseDouble(model.getValueAt(i, 5) + "");
            int unidades_existencia = Integer.parseInt(model.getValueAt(i, 6) + "");
            int unidades_pedido = Integer.parseInt(model.getValueAt(i, 7) + "");
            boolean suspendido = Boolean.parseBoolean(model.getValueAt(i, 8) + "");
            ctlProducto.SolicitudGuardar(id_producto, nombre, id_proveedor, categoria, cantidad_unidad, precio, unidades_existencia, unidades_pedido, suspendido);
        }
    }

    private void enviarProveedores() {
        DefaultTableModel model = (DefaultTableModel) tableProveedores.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int id_proveedor = Integer.parseInt(model.getValueAt(i, 0) + "");
            String nombre = model.getValueAt(i, 1) + "";
            String direccion = model.getValueAt(i, 2) + "";
            String telefono = model.getValueAt(i, 3) + "";
            String fax = model.getValueAt(i, 4) + "";
            String rfc = model.getValueAt(i, 5) + "";
            String correo = model.getValueAt(i, 6) + "";
            String web = model.getValueAt(i, 7) + "";
            String contacto = model.getValueAt(i, 8) + "";
            String puesto_contacto = model.getValueAt(i, 9) + "";
            String telefono_contacto = model.getValueAt(i, 10) + "";
            String movil_contacto = model.getValueAt(i, 11) + "";
            String correo_contacto = model.getValueAt(i, 12) + "";
            String telefono_alt1 = model.getValueAt(i, 13) + "";
            String telefono_alt2 = model.getValueAt(i, 14) + "";
            int cantidad_comprada;
            if (!model.getValueAt(i, 15).equals("")) {
                String a = model.getValueAt(i, 15) + "";
                String replace = a.replace("-", "");
                String b = replace.replace("(", "");
                String c = b.replace(")", "");
                String d = c.replace("+", "");
                String e = d.replace(" ", "");
                cantidad_comprada = Integer.parseInt(e);
            } else {
                cantidad_comprada = 0;
            }
            double cantidad_$;
            if (!model.getValueAt(i, 16).equals("")) {
                String a = model.getValueAt(i, 16) + "";
                String replace = a.replace("-", "");
                String b = replace.replace("(", "");
                String c = b.replace(")", "");
                String d = c.replace("+", "");
                String e = d.replace(" ", "");
                cantidad_$ = Double.parseDouble(e);
            } else {
                cantidad_$ = 0;
            }
            ctlProveedor.SolicitudGuardar(id_proveedor, nombre, direccion, telefono, fax, rfc, correo, web, contacto, puesto_contacto, telefono_contacto, movil_contacto, correo_contacto, telefono_alt1, telefono_alt2, cantidad_comprada, cantidad_$);
        }
    }

    private void enviarClientes() {
        DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {

            try {
                int id_cliente = Integer.parseInt(model.getValueAt(i, 0) + "");
                String alias = model.getValueAt(i, 1) + "";
                String apellido = model.getValueAt(i, 2) + "";
                String nombre = model.getValueAt(i, 3) + "";
                String direccion = model.getValueAt(i, 4) + "";
                String poblacion = model.getValueAt(i, 5) + "";
                String telefono = model.getValueAt(i, 6) + "";
                String movil = model.getValueAt(i, 7) + "";
                String email = model.getValueAt(i, 8) + "";
                String observacion = model.getValueAt(i, 9) + "";
                String sexo = model.getValueAt(i, 10) + "";
                String fecha_nacimiento = model.getValueAt(i, 11) + "";
                String a = fecha_nacimiento.replace("/", "-");
                Date parsed = new SimpleDateFormat("dd-MM-yyyy").parse(fecha_nacimiento);
                java.sql.Date fecha = new java.sql.Date(parsed.getYear(), parsed.getMonth(), parsed.getDay());
                String estado_civil = model.getValueAt(i, 12) + "";
                boolean desempleado = Boolean.parseBoolean(model.getValueAt(i, 14) + "");
                boolean fumador = Boolean.parseBoolean(model.getValueAt(i, 21) + "");

                ctlCliente.SolicitudGuardar(i, alias, nombre, apellido, direccion, poblacion, telefono,
                        movil, email, observacion, fecha, sexo, estado_civil, desempleado, fumador);

            } catch (ParseException ex) {
                Logger.getLogger(DB.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }

        }
    }

    public void limpiarClientes() {
        txtCedula.setText("");
        txtapellidoCliente.setText("");
        txtDireccionCliente.setText("");
        txtTelefonoCliente.setText("");
        txtemailCliente.setText("");
        cbSexoCliente.setSelectedIndex(-1);
        cbEstadocivil.setSelectedIndex(-1);
        txtAliasCliente.setText("");
        txtnombreCliente.setText("");
        txtPoblacionCliente.setText("");
        txtMovilCliente.setText("");
        txtObservacionCliente.setText("");
        jdcFechaNacimientoCliente.setDate(null);
        rbtnSiDesempleado.setSelected(false);
        rbtnNoDesempleado.setSelected(false);
        rbtnSiFumador.setSelected(false);
        rbtnNoFumador.setSelected(false);
        ChckEditarCliente.setSelected(false);
    }

    public void limpiarProveedores() {
        tfId_Proveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtTelefonoCliente.setText("");
        txtRFCProveedor.setText("");
        txtWeb.setText("");
        txtPuestoContacto.setText("");
        txtMovilContactoProveedor.setText("");
        txtPoblacionCliente.setText("");
        txtTelefonoAlternativoProveedor.setText("");
        txtCantidadCompradasProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtFax.setText("");
        txtCorreoProveedor.setText("");
        txtContactoProveedor.setText("");
        txtTelefonoContactoProveedor.setText("");
        txtCorreoContactoProveedor.setText("");
        txtSegundoTelefonoProveedor.setText("");
        txtCantidadPesosProveedor.setText("");
        ChckEditarProveedor.setSelected(false);
    }

    public void limpiarProductos() {
        txtNumProducto.setText("");
        cbNombreProveedorProducto.setSelectedIndex(-1);
        txtCantidadUnidadProducto.setText("");
        txtUnidadesExistencia.setText("");
        txtNombreProducto.setText("");
        cbCategoriaProducto.setSelectedIndex(-1);
        cbCategoriaProducto.setSelectedIndex(-1);
        txtPrecioUnidad.setText("");
        txtUnidadPedido.setText("");
        rbtnSiSuspendido.setSelected(false);
        rbtnNoSuspendido.setSelected(false);
        ChckEditarProducto.setSelected(false);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DB.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarAlPedido;
    private javax.swing.JButton BtnImportarClientes;
    private javax.swing.JButton BtnImportarProductos;
    private javax.swing.JButton BtnListarClientes;
    private javax.swing.JButton BtnListarProveedores;
    private javax.swing.JCheckBox ChckEditarCliente;
    private javax.swing.JCheckBox ChckEditarProducto;
    private javax.swing.JCheckBox ChckEditarProveedor;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarPedido;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JButton btnCancelarPedido;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarPedido;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarPedido;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnModificarCliente;
    private javax.swing.JButton btnModificarPedido;
    private javax.swing.JButton btnModificarProducto;
    private javax.swing.JButton btnModificarProveedor;
    private javax.swing.JComboBox<String> cbCategoriaPedido;
    private javax.swing.JComboBox<String> cbCategoriaProducto;
    private javax.swing.JComboBox<String> cbEstadocivil;
    private javax.swing.JComboBox<String> cbNombreProveedorProducto;
    private javax.swing.JComboBox<String> cbProveedoresPedido;
    private javax.swing.JComboBox<String> cbSexoCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcFechaNacimientoCliente;
    private javax.swing.JRadioButton rbtnNoDesempleado;
    private javax.swing.JRadioButton rbtnNoFumador;
    private javax.swing.JRadioButton rbtnNoSuspendido;
    private javax.swing.JRadioButton rbtnSiDesempleado;
    private javax.swing.JRadioButton rbtnSiFumador;
    private javax.swing.JRadioButton rbtnSiSuspendido;
    private javax.swing.JTable tablapedido;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTable tableClientes2;
    private javax.swing.JTable tablePedido1;
    private javax.swing.JTable tablePedido2;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTable tableProductos2;
    private javax.swing.JTable tableProveedores;
    private javax.swing.JTable tableProveedores2;
    private javax.swing.JTextField tfId_Proveedor;
    private javax.swing.JTextField txtAliasCliente;
    private javax.swing.JTextField txtCantidadCompradasProveedor;
    private javax.swing.JTextField txtCantidadPesosProveedor;
    private javax.swing.JTextField txtCantidadUnidadProducto;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtContactoProveedor;
    private javax.swing.JTextField txtCorreoContactoProveedor;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtMovilCliente;
    private javax.swing.JTextField txtMovilContactoProveedor;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNumProducto;
    private javax.swing.JTextField txtObservacionCliente;
    private javax.swing.JTextField txtPoblacionCliente;
    private javax.swing.JTextField txtPrecioUnidad;
    private javax.swing.JTextField txtPuestoContacto;
    private javax.swing.JTextField txtRFCProveedor;
    private javax.swing.JTextField txtSegundoTelefonoProveedor;
    private javax.swing.JTextField txtTelefonoAlternativoProveedor;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoContactoProveedor;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtUnidadPedido;
    private javax.swing.JTextField txtUnidadesExistencia;
    private javax.swing.JTextField txtValorPedido;
    private javax.swing.JTextField txtWeb;
    private javax.swing.JTextField txtapellidoCliente;
    private javax.swing.JTextField txtemailCliente;
    private javax.swing.JTextField txtnombreCliente;
    // End of variables declaration//GEN-END:variables
}
