/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex.pojos;

public class Respuesta {
    private Boolean error;
    private String mensaje;
    private Integer id;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Respuesta(Boolean error, String message, Integer id) {
        this.error = error;
        this.mensaje = message;
        this.id = id;
    }

    public Respuesta() {
    }
}
