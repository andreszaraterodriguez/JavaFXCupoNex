/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex.pojos;


public class Foto {
    private int id;
    private String datos;

    public Foto() {
    }

    public Foto(int id, String foto) {
        this.id = id;
        this.datos = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
 
}