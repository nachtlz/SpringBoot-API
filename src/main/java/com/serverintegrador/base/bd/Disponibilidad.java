package com.serverintegrador.base.bd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverintegrador.base.ArxiuLogs;

public class Disponibilidad {
	private int idHotel;
	private String codigo;
	private int habsDisponibles;
	private float precio;
	
	public Disponibilidad() {
	}

	public Disponibilidad(int idHotel, String codigo, int habsDisponibles, float precio) {
		super();
		this.idHotel = idHotel;
		this.codigo = codigo;
		this.habsDisponibles = habsDisponibles;
		this.precio = precio;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getHabsDisponibles() {
		return habsDisponibles;
	}

	public void setHabsDisponibles(int habsDisponibles) {
		this.habsDisponibles = habsDisponibles;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Disponibilidad [idHotel=" + idHotel + ", codigo=" + codigo + ", habsDisponibles=" + habsDisponibles
				+ ", precio=" + precio + "]";
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
