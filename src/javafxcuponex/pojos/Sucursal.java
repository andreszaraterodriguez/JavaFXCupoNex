
package javafxcuponex.pojos;

import java.util.List;

public class Sucursal {
    private Integer id;
    private Integer empresaId;
    private Empresa empresa;
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private String colonia;
    private String ciudad;
    private String telefono;
    private Double latitud;
    private Double longitd;
    private Integer encargadoId;
    private Usuario enacrgado;
    private List<Promocion> promociones;

    public Sucursal() {
    }

    public Sucursal(Integer id, Integer empresaId, Empresa empresa, String nombre, String direccion, String codigoPostal, String colonia, String ciudad, String telefono, Double latitud, Double longitd, Integer encargadoId, Usuario enacrgado, List<Promocion> promociones) {
        this.id = id;
        this.empresaId = empresaId;
        this.empresa = empresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitd = longitd;
        this.encargadoId = encargadoId;
        this.enacrgado = enacrgado;
        this.promociones = promociones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitd() {
        return longitd;
    }

    public void setLongitd(Double longitd) {
        this.longitd = longitd;
    }

    public Integer getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(Integer encargadoId) {
        this.encargadoId = encargadoId;
    }

    public Usuario getEnacrgado() {
        return enacrgado;
    }

    public void setEnacrgado(Usuario enacrgado) {
        this.enacrgado = enacrgado;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }
    
    public void addPromocion(Promocion promocion) {
        this.promociones.add(promocion);
    }

    @Override
    public String toString() {
        return "Sucursal{" + "id=" + id + ", empresaId=" + empresaId + ", empresa=" + empresa + ", nombre=" + nombre + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", colonia=" + colonia + ", ciudad=" + ciudad + ", telefono=" + telefono + ", latitud=" + latitud + ", longitd=" + longitd + ", encargadoId=" + encargadoId + ", enacrgado=" + enacrgado + '}';
    }
    
    
}
