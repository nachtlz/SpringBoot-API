package com.serverintegrador.base.bd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverintegrador.base.ArxiuLogs;

public class Hotel {
	private int idHotel;
	private String nombre;
	private String descripcion;
	private String url;
	private int categoria;
	private String telefono;
	private String imagen;
	
	// Constructor empty
	public Hotel() {
	}
	
	public Hotel(int idHotel, String nombre, String descripcion, String url, int categoria, String telefono, String imagen) {
		super();
		this.idHotel = idHotel;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.categoria = categoria;
		this.telefono = telefono;
		this.imagen = imagen;
	}

	public int getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Hotel [idHotel=" + idHotel + ", nombre=" + nombre + ", descripcion=" + descripcion + ", url=" + url
				+ ", categoria=" + categoria + ", telefono=" + telefono + ", imagen=" + imagen + "]";
	}
	
	public String toJSON() {
		String res = "";
		ObjectMapper op = new ObjectMapper();
		try {
			res = op.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			ArxiuLogs.guardarExcepcio(e);
		}
		return res;
	}
}
