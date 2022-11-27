
package javafxcuponex.pojos;

public class Empresas {
    private Integer idEmpresas;
    private String nombre;
    private String nombreC;
    private String nombreRL;
    private String email;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String paginaW;
    private String rfc;
    private Integer status;

    public Empresas() {
    }

    public Empresas(Integer idEmpresas ,String nombre, String nombreC, String nombreRL, String email, String direccion, String ciudad, String telefono, String paginaW, String rfc, Integer status) {
        this.idEmpresas = idEmpresas;
        this.nombre = nombre;
        this.nombreC = nombreC;
        this.nombreRL = nombreRL;
        this.email = email;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.paginaW = paginaW;
        this.rfc = rfc;
        this.status = status;
    }

    public Integer getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(Integer idEmpresas) {
        this.idEmpresas = idEmpresas;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getNombreRL() {
        return nombreRL;
    }

    public void setNombreRL(String nombreRL) {
        this.nombreRL = nombreRL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getPaginaW() {
        return paginaW;
    }

    public void setPaginaW(String paginaW) {
        this.paginaW = paginaW;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
   
}
