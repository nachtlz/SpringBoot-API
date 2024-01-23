package com.serverintegrador.base.bd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverintegrador.base.ArxiuLogs;

public class TipoHabitacion {
	private String codigo;
	private String descripcion;
	private int adultos;
	private int menores;
	
	public TipoHabitacion() {
	}

	public TipoHabitacion(String codigo, String descripcion, int adultos, int menores) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.adultos = adultos;
		this.menores = menores;
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

	public int getAdultos() {
		return adultos;
	}

	public void setAdultos(int adultos) {
		this.adultos = adultos;
	}

	public int getMenores() {
		return menores;
	}

	public void setMenores(int menores) {
		this.menores = menores;
	}

	@Override
	public String toString() {
		return "TipoHabitacion [codigo=" + codigo + ", descripcion=" + descripcion + ", adultos=" + adultos
				+ ", menores=" + menores + "]";
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
