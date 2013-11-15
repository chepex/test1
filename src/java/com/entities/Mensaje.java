/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

/**
 *
 * @author mmixco
 */
public class Mensaje {
    public String titulo;
    public String Mensajes;
    public String Descripcion;

    public String getDescripcion() {
	return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
	this.Descripcion = Descripcion;
    }

    public String getTitulo() {
	return titulo;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public String getMensajes() {
	return Mensajes;
    }

    public void setMensajes(String Mensajes) {
	this.Mensajes = Mensajes;
    }
    
    
}
