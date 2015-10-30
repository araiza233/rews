package org.apache.struts.publicidad.action;
import com.opensymphony.xwork2.ActionSupport;
import com.publicidad.entities.DetalleVentas;
import com.publicidad.entities.Producto;
import com.publicidad.entities.Ventas;
import com.publicidad.logic.NuevoProductoLogicInterface;
import com.publicidad.utilities.Utilities;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class NuevoProductoAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
    private String                          nombre;
    private String                          precio;
    private String                          descripcion;
    private String                          codigo;
    private Producto                        producto;
    private String                          resultado;
    private List                            productoList;
    private NuevoProductoLogicInterface     nuevoProductoLogicInterface;
    private String                          json;
    private String                          unidaddeventa;
    private String                          usuario;
    private String                          idProducto;
    //atributos de venta
    private String vendidoPor;
    private float totalVenta;
    private String productos;
    private Ventas ventas;
    private DetalleVentas detalleVentas;
    private String piezas;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String pago;
    private String cambio;
    public String guardar(){
        try{
            producto.setDescripcion(descripcion);
            producto.setNombre(nombre);
            producto.setPrecio(Utilities.String2Float(precio));
            if(Utilities.isAlpha(codigo)){
                codigo="";
            }
            producto.setCodigo(codigo.equalsIgnoreCase("")?"0000":codigo);
            producto.setUnidadDeVenta(unidaddeventa);
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
            producto.setFechaActualizacion(timestamp );
            producto.setActualizadoPor(usuario);
            producto.setFechaCreacion(timestamp);
            producto.setCreadoPor(usuario);
            producto.setPiezas(Float.parseFloat(piezas==null?"0":piezas));
            setResultado(nuevoProductoLogicInterface.guardar(producto));
            setCodigo(producto.getCodigo());
            request.getSession().setAttribute("mostrarGuardar", "si");
        }catch(Exception e){
            setResultado("Ocurrio el siguiente error al tratar de guardar en la base de datos: "+e.getMessage());
            request.getSession().setAttribute("mensajeError", ""+e.getMessage());
            request.getSession().setAttribute("mostrarGuardar", null);
            return "error";
        }
        return ActionSupport.SUCCESS;
    }
    public String actualizar(){
        try{
            setCodigo("");
            producto.setPiezas(Utilities.String2Float(piezas));
            producto.setNombre(nombre);
            producto.setPrecio(Utilities.String2Float(precio));
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
            producto.setFechaActualizacion(timestamp );
            producto.setActualizadoPor(usuario);
            producto.setIdProducto(Utilities.String2int(idProducto));//idProducto = $(this).find('td').eq(1).find('div').text();
            producto.setCodigo("");
            setResultado(nuevoProductoLogicInterface.editarVenta(producto));
            setCodigo(producto.getCodigo());
        }catch(Exception e){
            setResultado("Ocurrio el siguiente error al tratar de guardar en la base de datos: "+e.getMessage());
            request.getSession().setAttribute("mensajeError", ""+e.getMessage());
            request.getSession().setAttribute("mostrarGuardar", null);
        }
        return ActionSupport.SUCCESS;
    }
    public String buscarProducto(){
        try{
            List lista = null;
            if(Utilities.validarNumero(getNombre())){
                lista = nuevoProductoLogicInterface.buscarProducto(nombre,2);
            }else{
                lista = nuevoProductoLogicInterface.buscarProducto(nombre,1);
            }
            if(lista==null){
                setResultado("La version de prueba a terminado, por favor pongase en contacto con su proveedor del sistema");
            }else{
                setProductoList(lista);
                setResultado("Ok");
            }
        }catch(Exception e){
            setResultado("Ocurrio un error: "+e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }
    public String buscarProductoVenta(){
        try{
            setProductoList(nuevoProductoLogicInterface.buscarProducto(nombre,1));
            setResultado("Ok");
        }catch(Exception e){
            setResultado("Ocurrio un error: "+e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }
    public String editarProducto(){
        try{
            producto.setDescripcion(descripcion);
            producto.setNombre(nombre);
            producto.setPrecio(Utilities.String2Float(precio));
            producto.setCodigo(codigo);
            producto.setUnidadDeVenta(unidaddeventa);
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
            producto.setFechaActualizacion(timestamp );
            producto.setPiezas(Float.parseFloat(piezas));
            producto.setActualizadoPor(usuario);
            producto.setIdProducto(Utilities.String2int(idProducto));
            setResultado(nuevoProductoLogicInterface.editar(producto));
            request.getSession().setAttribute("mostrarGuardar", "si");
        }catch(Exception e){
            setResultado("Ocurrio el siguiente error al tratar de guardar en la base de datos: "+e.getMessage());
            request.getSession().setAttribute("mensajeError", ""+e.getMessage());
            request.getSession().setAttribute("mostrarGuardar", null);
            return "error";
        }
        return ActionSupport.SUCCESS;
    }
    
    public String guardarVenta(){
        try{
            //String user = (String)request.getSession().getAttribute("usuario");
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
            ventas.setFechaDeVenta(timestamp);
            ventas.setTotalVenta(totalVenta);
            ventas.setVendidoPor(usuario);
            ventas.setCambio(Utilities.String2Float(cambio));
            ventas.setPago(Utilities.String2Float(pago));
            setResultado(nuevoProductoLogicInterface.guardarVenta(ventas));
            nuevoProductoLogicInterface.guardarDetalleVentas(productos);
            String[] valores = productos.split("@");
            for(int i=0;i<valores.length;i++){
                String[] valores1 = valores[i].split(";");
                int idProducto = Utilities.String2int(valores1[0]);
                Float cantidadVendida = Utilities.String2Float(valores1[1]);
                /*Actualizar reduciendo la cantidad que se vende al producto*/
                Producto product = nuevoProductoLogicInterface.getProductoById(idProducto);
                Float nuevaCantidad = ((product.getPiezas()==null)?0:product.getPiezas())-cantidadVendida;
                product.setPiezas(nuevaCantidad);
                nuevoProductoLogicInterface.editar(product);
            }
            setResultado("Ok");
        }catch(Exception e){
            setResultado("Ocurrio el siguiente error al tratar de guardar en la base de datos: "+e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }
    public String buscarProductoCodigo(){
        try{
            List lista = nuevoProductoLogicInterface.buscarProducto(nombre,2);
            if(lista==null){
                setResultado("La version de prueba a terminado, por favor pongase en contacto con su proveedor del sistema");
            }else{
                setProductoList(lista);
                setResultado("Ok");
            }
        }catch(Exception e){
            setResultado("Ocurrio un error: "+e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NuevoProductoLogicInterface getNuevoProductoLogicInterface() {
        return nuevoProductoLogicInterface;
    }

    public void setNuevoProductoLogicInterface(NuevoProductoLogicInterface nuevoProductoLogicInterface) {
        this.nuevoProductoLogicInterface = nuevoProductoLogicInterface;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List getProductoList() {
        return productoList;
    }

    public void setProductoList(List productoList) {
        this.productoList = productoList;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getUnidaddeventa() {
        return unidaddeventa;
    }
    public void setUnidaddeventa(String unidaddeventa) {
        this.unidaddeventa = unidaddeventa;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    //getters and setters de venta

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }
    public DetalleVentas getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(DetalleVentas detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getVendidoPor() {
        return vendidoPor;
    }

    public void setVendidoPor(String vendidoPor) {
        this.vendidoPor = vendidoPor;
    }
    public String getPiezas() {
        return piezas;
    }
    public void setPiezas(String piezas) {
        this.piezas = piezas;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        response = hsr;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }
}