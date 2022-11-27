
package javafxcuponex.pojos;


public class Administrador {
    private Integer idAdmin;
    private String Nombre;
    private String apellidoM;
    private String apellidoP;
    private String correo;
    private String password;

    public Administrador() {
    }

    public Administrador(Integer idAdmin, String Nombre, String apellidoM, String apellidoP, String correo, String password) {
        this.idAdmin = idAdmin;
        this.Nombre = Nombre;
        this.apellidoM = apellidoM;
        this.apellidoP = apellidoP;
        this.correo = correo;
        this.password = password;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
