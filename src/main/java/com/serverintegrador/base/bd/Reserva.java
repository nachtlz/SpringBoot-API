package com.serverintegrador.base.bd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverintegrador.base.ArxiuLogs;

public class Reserva {
	private int idReserva;
	private String fechaInicio;
	private String fechaFin;
	private int adultos;
	private int menores;
	private int idCliente;
	private String codigo;
	private int idHotel;
	
	public Reserva() {
	}

	public Reserva(int idReserva, String fechaInicio, String fechaFin, int adultos, int menores, int idCliente,
			String codigo, int idHotel) {
		this.idReserva = idReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.adultos = adultos;
		this.menores = menores;
		this.idCliente = idCliente;
		this.codigo = codigo;
		this.idHotel = idHotel;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", adultos=" + adultos + ", menores=" + menores + ", idCliente=" + idCliente + ", codigo=" + codigo
				+ ", idHotel=" + idHotel + "]";
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
